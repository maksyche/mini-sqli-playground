package com.maksyche.dto;

public class UpdateRequest {

    private String username;
    private String password;
    private String info;

    public UpdateRequest() {
    }

    public UpdateRequest(String username, String password, String info) {
        this.username = username;
        this.password = password;
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
