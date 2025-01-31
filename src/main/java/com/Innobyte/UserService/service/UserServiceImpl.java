package com.Innobyte.UserService.service;

import com.Innobyte.UserService.Repositories.UserDao;
import com.Innobyte.UserService.Repositories.UserProfileDao;
import com.Innobyte.UserService.entities.User;
import com.Innobyte.UserService.entities.UserProfile;
import com.Innobyte.UserService.model.UserRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserProfileDao userProfileDao;

    private BCryptPasswordEncoder passwordEncoder;


    //perform sign-up process
    public void performSignUpProcess(UserRequestModel userRequestModel){
User user=new User();
convetUserRequestModelToTheUser(userRequestModel,user);
userDao.save(user);




UserProfile userProfile = new UserProfile();
convetUserRequestModelToTheUserProfile(userRequestModel,userProfile);
userProfileDao.save(userProfile);

    }



    private void convetUserRequestModelToTheUser(UserRequestModel userRequestModel,User user){
        user.setFirstName(userRequestModel.getFirstName());
        user.setMidddleName(userRequestModel.getMidddleName());
        user.setLastName(userRequestModel.getLastName());
        user.setDob(userRequestModel.getDob());
    }

    private void convetUserRequestModelToTheUserProfile(UserRequestModel userRequestModel, UserProfile userProfile) {
        //userProfile.setUserProfileID(userRequestModel.get);
        userProfile.setEmail(userRequestModel.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userRequestModel.getPassword());
        userProfile.setPassword(hashedPassword);

        //userProfile.setPassword(userRequestModel.getPassword());
        userProfile.setPhoneNumber(userRequestModel.getPhoneNumber());
        userProfile.setCountry(userRequestModel.getCountry());
        userProfile.setCity(userRequestModel.getCity());
        userProfile.setPincode(userRequestModel.getPincode());


    }



    //Auth
    public boolean performUserloginProcess(UserRequestModel loginRequest) {
        // Find the user profile by email
        UserProfile userProfile = userProfileDao.findByEmail(loginRequest.getEmail());

        if (userProfile == null) {
            return false; // User not found
        }

        // Validate password using BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(loginRequest.getPassword(), userProfile.getPassword());

        return passwordMatches;
    }


}
