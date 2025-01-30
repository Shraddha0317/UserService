package com.Innobyte.UserService.controller;

import com.Innobyte.UserService.model.UserRequestModel;
import com.Innobyte.UserService.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class UserController {

   @Autowired
    private UserServiceImpl userService;

    //sign-up
    @PostMapping("/signUp")
    public void userSignUp(@RequestBody UserRequestModel userRequestModel){
       if(null == userRequestModel.getEmail() && userRequestModel.getEmail().isEmpty()){
          // return ResponseEntity.notFound();
       }
       userService.performSignUpProcess(userRequestModel);


        //return null;
    }











    //Auth

    //log-in

    //reset password

    //forget password





}
