package com.placementhub.placementhub.service;
import com.placementhub.placementhub.dto.UserRequest;
import com.placementhub.placementhub.dto.UserResponse;
import com.placementhub.placementhub.entity.User;
import com.placementhub.placementhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
	   private final UserRepository userRepository;

	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    public UserResponse createUser(UserRequest request) {

	        User user = new User();

	        user.setEmail(request.getEmail());
	        user.setPassword(request.getPassword());
	        user.setRole(request.getRole());

	        User savedUser = userRepository.save(user);

	        return new UserResponse(
	                savedUser.getId(),
	                savedUser.getEmail(),
	                savedUser.getRole()
	        );
	    }

	    public List<UserResponse> getAllUsers() {

	        return userRepository.findAll()
	                .stream()
	                .map(user -> new UserResponse(
	                        user.getId(),
	                        user.getEmail(),
	                        user.getRole()
	                ))
	                .toList();
	    }

}
