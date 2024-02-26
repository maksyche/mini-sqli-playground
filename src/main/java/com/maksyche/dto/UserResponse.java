package com.maksyche.dto;

public class UserResponse {

    private long id;

    private String username;

    private boolean isAdmin;

    private String info;

    public UserResponse() {
    }

    public UserResponse(long id, String username, boolean isAdmin, String info) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
