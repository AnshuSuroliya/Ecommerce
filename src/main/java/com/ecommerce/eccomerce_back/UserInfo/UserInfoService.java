package com.ecommerce.eccomerce_back.UserInfo;

import com.ecommerce.eccomerce_back.repository.UserRepository;
import com.ecommerce.eccomerce_back.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User userDetail = userRepository.findByEmail(email);

       UserInfoDetails u = new UserInfoDetails(userDetail);

        return u;
    }

}
