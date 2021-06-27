package com.example.getdowntoquizness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable {

    ArrayList <HashMap<String, String>> usersList;


    public Data(){
        usersList = new ArrayList<>();
        HashMap<String, String> hmCredentials = new HashMap<>();

        // Adding some items into the hashmap table
        hmCredentials.put("username", "test");
        hmCredentials.put("password", "1234");
        hmCredentials.put("role", "admin");
        usersList.add(hmCredentials);
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // This method adds a new username and password to the hashmap
    public void AddCredential(String username, String password, String user){
        HashMap<String, String> hmCredentials = new HashMap<>();
        hmCredentials.put("username", username);
        hmCredentials.put("password", password);
        if (user.toLowerCase().equals("admin")) {

            hmCredentials.put("role", "admin");
        }
        else{
            hmCredentials.put("role", "student");
        }
        usersList.add(hmCredentials);
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // CheckUsername method checks if a given username is already stored in the hashmap.
    public Boolean CheckUsername(String username){
        int index = 0;
        boolean nameFound = false;
        do{
            if(usersList.get(index).get("username").equals(username) ){
                nameFound = true;
            }
            index++;
        }while(index < usersList.size() && !nameFound);

        return nameFound;
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //CheckCredentials method checks the given username and password credentials
    public Boolean CheckCredentials(String username, String Password){
        Boolean  retval = true;
        int index = getIndex(username);
        System.out.println("Index: " + index);
        System.out.println("username:" + usersList.get(index).get("username"));
        System.out.println("password:" + usersList.get(index).get("password"));
        if (!CheckUsername(username) || !usersList.get(index).get("password").equals(Password)){
            retval = false;
        }

        return retval;
    }
    public int getIndex(String userName){
        int index = -1;
        //System.out.println("usersList size: " + usersList.size());
        for(int i = 0; i < usersList.size(); i++){
            //System.out.println("HIHIHIHIHIHIHIHIHIH");
            if(usersList.get(i).get("username").equals(userName) ){
                index = i;
            }
        }
        return index;
    }
    public boolean isStudent(String name){
        int index = getIndex(name);
        boolean student = false;
        if(usersList.get(index).get("role").equals("student")){
            student = true;
        }
        return student;
    }
    public boolean isAdmin(String name){
        int index = getIndex(name);
        boolean admin = false;
        if(usersList.get(index).get("role").equals("admin") ){
            admin = true;
        }
        return admin;
    }
    public String getAllUserNames(){
        String allNames = "";
        for(int i = 0; i < usersList.size(); i++){
            allNames += usersList.get(i).get("username");
        }
        return allNames;
    }
}

