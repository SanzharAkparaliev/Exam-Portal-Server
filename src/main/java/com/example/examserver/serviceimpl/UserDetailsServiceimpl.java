package com.example.examserver.serviceimpl;

import com.example.examserver.model.User;
import com.example.examserver.reposirory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {
    Logger logger = Logger.getLogger(String.valueOf(UserDetailsServiceimpl.class));
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
