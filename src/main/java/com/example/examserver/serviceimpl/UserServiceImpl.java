package com.example.examserver.serviceimpl;

import com.example.examserver.model.User;
import com.example.examserver.model.UserRole;
import com.example.examserver.reposirory.RoleRepository;
import com.example.examserver.reposirory.UserRepository;
import com.example.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = userRepository.findByUsername(user.getUsername());

        if(local!=null){
            System.out.println("User is already there !!");
            throw new Exception("User already present !!");
        }else {
            //user create
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
           local = userRepository.save(user);

        }
        return local;
    }
}
