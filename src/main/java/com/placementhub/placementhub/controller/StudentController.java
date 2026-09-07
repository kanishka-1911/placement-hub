package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.StudentProfileRequest;
import com.placementhub.placementhub.dto.StudentProfileResponse;
import com.placementhub.placementhub.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/profile")
    public ResponseEntity<StudentProfileResponse> createProfile(
            @RequestBody StudentProfileRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        StudentProfileResponse response =
                studentService.createProfile(email, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<StudentProfileResponse> getMyProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                studentService.getMyProfile(email)
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<StudentProfileResponse> updateProfile(
            @RequestBody StudentProfileRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                studentService.updateProfile(
                        email,
                        request
                )
        );
    }
}
