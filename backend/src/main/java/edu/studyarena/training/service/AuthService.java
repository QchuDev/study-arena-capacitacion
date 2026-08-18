package edu.studyarena.training.service;

import edu.studyarena.training.dto.RegisterRequest;
import edu.studyarena.training.dto.LoginRequest;
import edu.studyarena.training.dto.AuthResponse;

import edu.studyarena.training.entity.User;
import edu.studyarena.training.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;      // <-- es la interfaz de BCryptPasswordEncoder

import edu.studyarena.training.security.JwtService;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  /*
   *  Registro con BCrypt
   *  Login con validacion
  */
  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthResponse register(RegisterRequest regReq) {

    //TODO:
    // validacion por existencia o validez de las credenciales.
    
    String hashPassword = passwordEncoder.encode(regReq.password());
    User user = new User(regReq.name(), regReq.email(), hashPassword);
    userRepository.save(user);
 
    String token = jwtService.generateToken(user.getEmail());
    return new AuthResponse(token, user.getName(), user.getEmail());

  }
  
  public AuthResponse login(LoginRequest logReq) {
    User user = userRepository.findByEmail(logReq.email()).orElseThrow(() -> new RuntimeException("User NOT FOUND"));
    
    if (!passwordEncoder.matches(logReq.password(), user.getPassword())) {
      throw new RuntimeException("Credenciales inválidas");
    }

    String token = jwtService.generateToken(user.getEmail());
    return new AuthResponse(token, user.getName(), user.getEmail());
  }

}
