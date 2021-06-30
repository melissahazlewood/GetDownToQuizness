package com.example.getdowntoquizness;

public class User {
    // Fields
    int userID; //TODO: add to constructor, add getter, and add setter?
    String name;
    String username;
    String password;
    String retypePassword;
    String email;
    Boolean isAdmin;
    Boolean isSelected;
    //TODO: add phone number possibly + getters/setters/constructor
    //TODO: add the quiz and grade info + getters/setters/constructor

    // Constructors
    public User() {

    }

    public User(String name, String username, String password, String retypePassword, String email,
                Boolean isAdmin, Boolean isSelected)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isSelected = isSelected;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }

    // Getters
    public String getName() {
        return this.name;
    }
    public String getUsername() {
        return this.username;
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
    public Boolean getIsSelected() {
        return isSelected;
    }
}