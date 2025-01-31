package com.Innobyte.UserService.Repositories;

import com.Innobyte.UserService.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDao extends MongoRepository<User,String> {
   // User findByEmail(String email);



}
