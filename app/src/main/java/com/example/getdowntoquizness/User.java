package com.example.getdowntoquizness;

public class User {
    // Fields
    int userID; //TODO: add to constructor, add getter, and add setter?
    String username;
    String name;
    String password;
    String retypePassword;
    String email;
    Boolean isAdmin;
    //TODO: add phone number possibly + getters/setters/constructor
    //TODO: add the quiz and grade info + getters/setters/constructor

    // Constructors
    public User() {

    }

    public User(String username, String name, String password, String retypePassword, String email,
                Boolean isAdmin) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.retypePassword = retypePassword;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public String getRetypePassword() {
        return this.retypePassword;
    }
    public String getEmail() {
        return this.email;
    }
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
}
