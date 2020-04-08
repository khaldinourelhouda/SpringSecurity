package com.demo.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.model.User;
import com.demo.spring.repository.UserRepository;
import com.demo.spring.util.PasswordUtil;




@Service
@Transactional
public class UserServiceIml implements UserService{
	
	@Autowired 
	UserRepository userRepository;

	@Override
	public User save(User user) {
		String password =PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailIgnoreCase(email);
		
	}
	
	@Override
	public User updateUser(User user) {
	
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(Long id) {
		User user = new User();
		user.setId(id);
		userRepository.delete(user);
		
	}

	@Override
	public User getUserById(Long idDepart) {
		User obj = userRepository.findById(idDepart).get();
		return obj;
	}

}
