package com.example.examserver.controller;

import com.example.examserver.config.JwtUtils;
import com.example.examserver.helper.UserNotFoundException;
import com.example.examserver.model.JwtRequest;
import com.example.examserver.model.JwtResponse;
import com.example.examserver.model.User;
import com.example.examserver.serviceimpl.UserDetailsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceimpl userDetailsServiceimpl;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/generate-token")
    public ResponseEntity<?> geneteToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("UserNAme "+jwtRequest.getUsername());
        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }
        //authenticate
        UserDetails userDetails = this.userDetailsServiceimpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username,String password) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            }catch (DisabledException e){
                throw new Exception("USER DISABLED "+e.getMessage());
            }catch (BadCredentialsException e){
                throw new Exception("Invalid Credentials " + e.getMessage());
            }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        System.out.println("User Name :" + principal.getName());
        return ((User) this.userDetailsServiceimpl.loadUserByUsername(principal.getName()));
    }
}
