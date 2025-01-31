package com.Innobyte.UserService.Repositories;

import com.Innobyte.UserService.entities.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileDao extends MongoRepository<UserProfile, String> {
    UserProfile findByEmail(String email);
}
