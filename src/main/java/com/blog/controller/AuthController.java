package com.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.DTO.LoginDTO;
import com.blog.DTO.SignUpDTO;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		System.out.println("piyush" + authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("User signed in successfully.", HttpStatus.OK);
		// return null;

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {

		
		// check if the username exist in db
		if (userRepository.existsByUsername(signUpDTO.getUserName())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// check if the username exist in db
		if (userRepository.existsByEmail(signUpDTO.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		User user = new User();

		user.setName(signUpDTO.getName());
		user.setUsername(signUpDTO.getUserName());
		user.setEmail(signUpDTO.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
		
		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		
		user.setRoles(Collections.singleton(roles));
		
		userRepository.save(user);
		
		return new ResponseEntity<>("User Registered Successfully!", HttpStatus.CREATED);
		

	}

}
