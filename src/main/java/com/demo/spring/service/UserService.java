package com.demo.spring.service;

import java.util.List;

import com.demo.spring.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);

	User updateUser(User user);

	void deleteUser(Long id);

	User getUserById(Long idDepart);

}
