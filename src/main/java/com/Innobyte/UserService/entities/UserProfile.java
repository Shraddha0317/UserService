package com.Innobyte.UserService.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "UserProfile")
public class UserProfile {
    @Id
    private String id;
    private String userProfileID;
    private String email;
    private String password;
    private String phoneNumber;

}
