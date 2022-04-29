package com.example.examserver.service;

import com.example.examserver.model.User;
import com.example.examserver.model.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> roles) throws Exception;
}
