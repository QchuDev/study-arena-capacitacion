package edu.studyarena.training.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import edu.studyarena.training.dto.VideoConferenceAccess;
import edu.studyarena.training.entity.Meeting;
import edu.studyarena.training.entity.User;
import edu.studyarena.training.repository.MeetingRepository;
import edu.studyarena.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class VideoConferenceAccessService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final ResourceLoader resourceLoader;

    @Value("${jaas.app-id}")
    private String appId;

    @Value("${jaas.kid}")
    private String kid;

    @Value("${jaas.private-key-path}")
    private String privateKeyPath;

    @Value("${jaas.ttl-minutes}")
    private int ttlMinutes;

    private RSAPrivateKey privateKey;

    public VideoConferenceAccessService(
      MeetingRepository meetingRepository,
      UserRepository userRepository,
      ResourceLoader resourceLoader
    ) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    private void loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Resource resource = resourceLoader.getResource(privateKeyPath);
        String pem = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        // Quitar headers PEM y whitespace
        String base64Key = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.privateKey = (RSAPrivateKey) kf.generatePrivate(spec);
    }

    public VideoConferenceAccess generateAccess(String meetingId, String userEmail) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Reunión no encontrada"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Instant now = Instant.now();
        Instant expiration = now.plusSeconds((long) ttlMinutes * 60);

        String token = buildJitsiJwt(meeting.getJitsiRoomId(), user, now, expiration);

        return new VideoConferenceAccess(
                "8x8.vc",
                appId + "/" + meeting.getJitsiRoomId(),
                token,
                expiration
        );
    }

    private String buildJitsiJwt(String roomName, User user, Instant now, Instant expiration) {
        try {
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .keyID(kid)
                    .type(new com.nimbusds.jose.JOSEObjectType("JWT"))
                    .build();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .audience("jitsi")
                    .issuer("chat")
                    .subject(appId)
                    .claim("room", roomName)
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(expiration))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("context", Map.of(
                            "user", Map.of(
                                    "name", user.getName(),
                                    "email", user.getEmail(),
                                    "moderator", "false"
                            ),
                            "features", Map.of(
                                    "livestreaming", "false",
                                    "recording", "false",
                                    "transcription", "false",
                                    "outbound-call", "false"
                            )
                    ))
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claims);
            signedJWT.sign(new RSASSASigner(privateKey));

            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Error al firmar token Jitsi", e);
        }
    }
}
