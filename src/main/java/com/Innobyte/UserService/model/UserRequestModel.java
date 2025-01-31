package com.Innobyte.UserService.model;

import jdk.jfr.Name;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

@Data

public class UserRequestModel {


  // @NonNull
    private String firstName;
    private String lastName;
    private String midddleName;
    private String dob;
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String city;
    private String pincode;




//  "firstName"
//   "lastName"
//  "midddleName"
//   "dob"
//  "email"
//  "password"
//   "phoneNumber"
//  "country"
//  "city"
//   "pincode"


}




