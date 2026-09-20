package com.placementhub.placementhub.service;

import com.placementhub.placementhub.dto.StudentProfileRequest;
import com.placementhub.placementhub.dto.StudentProfileResponse;
import com.placementhub.placementhub.entity.StudentProfile;
import com.placementhub.placementhub.entity.User;
import com.placementhub.placementhub.repository.StudentProfileRepository;
import com.placementhub.placementhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;
@Service
public class StudentService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentService(
            StudentProfileRepository studentProfileRepository,
            UserRepository userRepository) {

        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    public StudentProfileResponse createProfile(
            String email,
            StudentProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getRole().equals("STUDENT")) {
            throw new RuntimeException(
                    "Only students can create student profiles");
        }

        if (studentProfileRepository.findByUser(user).isPresent()) {
            throw new RuntimeException(
                    "Student profile already exists");
        }

        if (studentProfileRepository
                .existsByRegisterNumber(request.getRegisterNumber())) {

            throw new RuntimeException(
                    "Register number already exists");
        }

        StudentProfile profile = new StudentProfile();

        profile.setFullName(request.getFullName());
        profile.setRegisterNumber(request.getRegisterNumber());
        profile.setDepartment(request.getDepartment());
        profile.setCgpa(request.getCgpa());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setActiveBacklogs(request.getActiveBacklogs());
        profile.setSkills(request.getSkills());
        profile.setUser(user);

        StudentProfile saved =
                studentProfileRepository.save(profile);

        return convertToResponse(saved);
    }

    public StudentProfileResponse getMyProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile =
                studentProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        return convertToResponse(profile);
    }

    public StudentProfileResponse updateProfile(
            String email,
            StudentProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile =
                studentProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        if (!Objects.equals(
                profile.getRegisterNumber(),
                request.getRegisterNumber())) {

            if (request.getRegisterNumber() != null &&
                    studentProfileRepository.existsByRegisterNumber(
                            request.getRegisterNumber())) {

                throw new RuntimeException("Register number already exists");
            }
        }

        profile.setFullName(request.getFullName());
        profile.setRegisterNumber(request.getRegisterNumber());
        profile.setDepartment(request.getDepartment());
        profile.setCgpa(request.getCgpa());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setActiveBacklogs(request.getActiveBacklogs());
        profile.setSkills(request.getSkills());
        StudentProfile updated =
                studentProfileRepository.save(profile);

        return convertToResponse(updated);
    }

    private StudentProfileResponse convertToResponse(
            StudentProfile profile) {

        return new StudentProfileResponse(
                profile.getId(),
                profile.getUser().getEmail(),
                profile.getFullName(),
                profile.getRegisterNumber(),
                profile.getDepartment(),
                profile.getCgpa(),
                profile.getGraduationYear(),
                profile.getActiveBacklogs(),
                profile.getSkills()
        );
    }
}
