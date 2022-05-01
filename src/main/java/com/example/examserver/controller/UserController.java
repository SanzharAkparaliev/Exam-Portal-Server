package com.example.examserver.controller;

import com.example.examserver.model.Role;
import com.example.examserver.model.User;
import com.example.examserver.model.UserRole;
import com.example.examserver.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User newuser) throws Exception {
       User user = new User();
        user.setUsername(newuser.getUsername());
        user.setFirstName(newuser.getFirstName());
        user.setEmail(newuser.getEmail());
        user.setPassword(newuser.getPassword());
        user.setLastName(newuser.getLastName());
        user.setProfile("default.png");

        Role role = new Role();
        role.setRoleId(24L);
        role.setRoleName("NORMAL");

        Set<UserRole> userRoleSet = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoleSet.add(userRole);

        User user1 = userService.createUser(user,userRoleSet);

        return user1;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String userName){
        return userService.getUserByUserName(userName);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers(){
      return   userService.getAllUsers();
    }
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex)
    {
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
