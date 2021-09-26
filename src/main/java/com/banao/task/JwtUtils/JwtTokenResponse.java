package com.banao.task.JwtUtils;

public class JwtTokenResponse {
    private int userId;
    private String username;
    private String usertype;
    private String email;
    private String jwtToken;
    private String createdAt;

    public JwtTokenResponse() {
    }

    public JwtTokenResponse(int userId, String username, String usertype, String email, String jwtToken, String createdAt) {
        this.userId = userId;
        this.username = username;
        this.usertype = usertype;
        this.email = email;
        this.jwtToken = jwtToken;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
