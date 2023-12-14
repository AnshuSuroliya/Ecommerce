package com.ecommerce.eccomerce_back.controller;

import com.ecommerce.eccomerce_back.UserInfo.UserInfoService;
import com.ecommerce.eccomerce_back.entity.Cart;
import com.ecommerce.eccomerce_back.repository.CartRepository;
import com.ecommerce.eccomerce_back.repository.UserRepository;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.UserException;
import com.ecommerce.eccomerce_back.request.*;
import com.ecommerce.eccomerce_back.response.LoginResponse;
import com.ecommerce.eccomerce_back.service.JwtService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping(value = "/auth")
public class UserRestController {
    @Autowired
UserRepository userRepository;
    @Autowired
    UserInfoService service;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CartRepository cartRepository;

    @PostMapping("/signup")
public @ResponseBody String addUser(@RequestBody SignUp signUp) throws UserException {

    User isEmailExist=userRepository.findByEmail(signUp.getEmail());
    if(isEmailExist!=null){
        throw new UserException("Email is already used with another account");
    }
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    User s=new User();
    s.setFirstName(signUp.getFirstName());
    s.setLastName(signUp.getLastName());
    s.setEmail(signUp.getEmail());
    s.setGender(signUp.getGender());
    s.setRole(signUp.getRole());
    s.setPassword(bCryptPasswordEncoder.encode(signUp.getPassword()));
    s.setContactNumber(signUp.getContactNumber());
    userRepository.save(s);
    return "Registered Successfully";

}

    @PostMapping("/signin")
    public @ResponseBody LoginResponse authenticateAndGetToken(@RequestBody Login login) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            User user=userRepository.findByEmail(login.getEmail());
            if (authentication.isAuthenticated()) {
                String jwt=(jwtService.generateToken(login.getEmail()));
                String role=user.getRole();
                Long id=user.getId();
                LoginResponse loginResponse=new LoginResponse(jwt,role,id);
                return loginResponse;
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }

    }

    @GetMapping("/findUsers")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public @ResponseBody Iterable<User> findAll(){
    return userRepository.findAllUsers();
}

@PostMapping("/findProfile")
    public @ResponseBody User findByEmail(@RequestBody CreateCartRequest createCartRequest){
        User user=userRepository.findByEmail(createCartRequest.getEmail());
        //System.out.println(email);
        return user;
    }
    @PutMapping("/update")
    public @ResponseBody String update(@RequestBody UpdateRequest updateRequest){
        User user1=userRepository.findByEmail(updateRequest.getOldEmail());
        if(user1.getEmail()==null) {
            return "User Does not exist";
        }
        user1.setEmail(updateRequest.getEmail());
        user1.setContactNumber(updateRequest.getContactNumber());
        userRepository.save(user1);
        return "Email and Contact Number Updated Successfully";
    }
    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public @ResponseBody String delete(@RequestBody GetRequest getRequest){
        User user=userRepository.findByUserId(getRequest.getUserId());
        Cart cart=cartRepository.findByUserId(getRequest.getUserId());
        cartRepository.delete(cart);
        userRepository.delete(user);
        return "User Deleted";
    }
}

