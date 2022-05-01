package com.example.examserver.model;

public class JwtRequest {

    String username;
    String password;

    public JwtRequest() {
    }

    public JwtRequest( String userName,String password) {
        this.password = password;
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
