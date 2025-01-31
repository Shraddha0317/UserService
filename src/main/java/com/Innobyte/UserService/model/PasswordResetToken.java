package com.Innobyte.UserService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    private String id;
    private String email;
    private String token;
    private Date expirationDate;

    // Getters and Setters
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//    public String getToken() { return token; }
//    public void setToken(String token) { this.token = token; }
//    public Date getExpirationDate() { return expirationDate; }
//    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
}
