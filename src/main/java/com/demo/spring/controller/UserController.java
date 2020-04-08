package com.demo.spring.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.model.User;
import com.demo.spring.service.UserService;



@RestController
public class UserController {
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	
	@Autowired private UserService userService;
	
	@GetMapping(value="/users")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>>getAllUsers(){
		
		List<User> users=userService.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value="/getuser")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User>getUser(Principal principal){
		
		
		User user=userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public User updateUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return 	userService.updateUser(user);
	
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Long id ) {
		userService.deleteUser(id);
		  System.out.println("user deleted");
	}
	
	@GetMapping("/user/{idDepart}")
	public User getUtilisateurById(@PathVariable Long idDepart){
		
		/*return userRepository.getUsers();*/
	return userService.getUserById(idDepart);
	
	}

}
