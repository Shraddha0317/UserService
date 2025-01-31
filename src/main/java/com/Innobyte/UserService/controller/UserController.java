package com.Innobyte.UserService.controller;

import com.Innobyte.UserService.entities.User;
import com.Innobyte.UserService.model.UserRequestModel;
import com.Innobyte.UserService.service.UserServiceImpl;
import com.Innobyte.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
@RequestMapping("/v1")
public class UserController {

   @Autowired
    private UserServiceImpl userService;

   @Autowired
    private JwtUtil jwtUtil;

    //sign-up
    @PostMapping("/signUp")
    public ResponseEntity<String> userSignUp(@RequestBody UserRequestModel userRequestModel){
       if(null == userRequestModel.getEmail() && userRequestModel.getEmail().isEmpty()){
          // return ResponseEntity.notFound();
       }
       //for FirstName
        if(null == userRequestModel.getFirstName() && userRequestModel.getFirstName().isEmpty()){
            return ResponseEntity.badRequest().body("firstname cannot be null or empty");
        }

        if(null == userRequestModel.getLastName() && userRequestModel.getLastName().isEmpty()){
            return ResponseEntity.badRequest().body("lastname cannot be null or empty");
        }

        if(null == userRequestModel.getPhoneNumber() && userRequestModel.getPhoneNumber().isEmpty() && userRequestModel.getPhoneNumber().length() == 10){
            return ResponseEntity.badRequest().body("Phone number cannot be null or empty and should be 10 character only");
        }


        if(null == userRequestModel.getPassword() && userRequestModel.getPassword().isEmpty()){
            return ResponseEntity.badRequest().body("Password cannot be null or empty");
        }

        String password = userRequestModel.getPassword();
        if (password.length() < 5 && !password.matches(".*[A-Z].*") && !password.matches(".*[@#$%^&+=].*")) {
            return ResponseEntity.badRequest().body("Password must be at least 5 characters,At least 1 uppercase letter,At least 1 special character");
        }




       userService.performSignUpProcess(userRequestModel);


        //return null;
        return null;
    }




//1)email,firstname,lastname not null& empty.
//2) phone number should be 10 digits & not null& empty.
//3)password not null& empty also at least 5 characters,At least 1 uppercase letter,At least 1 special character






    //Auth

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserRequestModel loginRequest) {
        boolean isAuthenticated = userService.performUserloginProcess(loginRequest);

        if (!isAuthenticated) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        String jwtToken = jwtUtil.generateToken(loginRequest.getEmail()); // Assuming email is the username
        return ResponseEntity.ok("JWT Token: " + jwtToken);
    }


    //log-in

    //reset password

    //forget password





}
