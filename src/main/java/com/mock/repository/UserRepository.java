package com.mock.repository;

import org.springframework.data.repository.CrudRepository;

import com.mock.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserName(String userName);

}
