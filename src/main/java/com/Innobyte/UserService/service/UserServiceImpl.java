package com.Innobyte.UserService.service;

import com.Innobyte.UserService.Repositories.UserDao;
import com.Innobyte.UserService.entities.User;
import com.Innobyte.UserService.model.UserRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;


    //perform sign-up process
    public void performSignUpProcess(UserRequestModel userRequestModel){
User user=new User();
convetUserRequestModelToTheUser(userRequestModel,user);
userDao.save(user);


    }


    private void convetUserRequestModelToTheUser(UserRequestModel userRequestModel,User user){
        user.setFirstName(userRequestModel.getFirstName());
        user.setMidddleName(userRequestModel.getMidddleName());
        user.setLastName(userRequestModel.getLastName());
        user.setEmail(userRequestModel.getEmail());
    }

}
