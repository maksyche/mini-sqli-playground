package com.maksyche.dto;

public class UserResponse {

    private long id;

    private String username;

    private String info;

    public UserResponse() {
    }

    public UserResponse(long id, String username, String info) {
        this.id = id;
        this.username = username;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
