package edu.studyarena.training.service;

import edu.studyarena.training.dto.CreateMeetingRequest;
import edu.studyarena.training.dto.MeetingResponse;
import edu.studyarena.training.entity.Meeting;
import edu.studyarena.training.entity.User;
import edu.studyarena.training.repository.MeetingRepository;
import edu.studyarena.training.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import edu.studyarena.training.exception.UserNotFoundException;
import edu.studyarena.training.exception.MeetingNotFoundException;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    public MeetingService(MeetingRepository meetingRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
    }

    public MeetingResponse create(CreateMeetingRequest request, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado: " + ownerEmail));

        String jitsiRoomId = UUID.randomUUID().toString();

        Meeting meeting = new Meeting(
                request.name(),
                request.description(),
                request.dateTime(),
                jitsiRoomId,
                owner
        );

        Meeting saved = meetingRepository.save(meeting);
        return toResponse(saved);
    }

    public List<MeetingResponse> findAll() {
        return meetingRepository.findAllByOrderByDateTimeAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MeetingResponse findById(String id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException("Reunión no encontrada: " + id));
        return toResponse(meeting);
    }

    private MeetingResponse toResponse(Meeting meeting) {
        return new MeetingResponse(
                meeting.getId(),
                meeting.getName(),
                meeting.getDescription(),
                meeting.getDateTime(),
                meeting.getJitsiRoomId(),
                meeting.getOwner().getName(),
                meeting.getOwner().getEmail(),
                meeting.getCreatedAt()
        );
    }
}
