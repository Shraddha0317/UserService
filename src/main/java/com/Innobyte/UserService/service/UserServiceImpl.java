package com.Innobyte.UserService.service;

import com.Innobyte.UserService.Repositories.PasswordResetTokenRepository;
import com.Innobyte.UserService.Repositories.UserDao;
import com.Innobyte.UserService.Repositories.UserProfileDao;
import com.Innobyte.UserService.entities.User;
import com.Innobyte.UserService.entities.UserProfile;
import com.Innobyte.UserService.model.PasswordResetToken;
import com.Innobyte.UserService.model.UserRequestModel;
import com.Innobyte.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserProfileDao userProfileDao;
     //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

        //forgot
        public String generateForgotPasswordToken(String email) {
            // Check if user exists
            UserProfile userProfile = userProfileDao.findByEmail(email);
            if (userProfile == null) {
                throw new IllegalArgumentException("User with this email does not exist");
            }

            PasswordResetToken token = new PasswordResetToken();
            token.setEmail(email);
            token.setToken(UUID.randomUUID().toString()); // Generate a random UUID token
            token.setExpirationDate(new Date(System.currentTimeMillis() + 1000 * 60 * 30)); // Expires in 30 minutes

            // Save the token to the database
            passwordResetTokenRepository.save(token);

            // Return the token to be sent to the user's email
            return token.getToken();
        }


    //reset
    public boolean resetPassword(String token, String newPassword) {
        // Validate the reset token
        Optional<PasswordResetToken> optionalToken = passwordResetTokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }

        PasswordResetToken resetToken = optionalToken.get();

        // Check if the token has expired
        if (resetToken.getExpirationDate().before(new Date())) {
            throw new IllegalArgumentException("Token has expired");
        }

        // Retrieve the user and update the password
        UserProfile userProfile = userProfileDao.findByEmail(resetToken.getEmail());
        if (userProfile == null) {
            throw new IllegalArgumentException("User not found");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userProfile.setPassword(encoder.encode(newPassword));
        userProfileDao.save(userProfile);

        // Optionally delete the token after successful password reset
        passwordResetTokenRepository.delete(resetToken);

        return true;
    }
}
