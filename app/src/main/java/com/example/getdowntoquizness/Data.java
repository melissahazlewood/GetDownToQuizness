package com.example.getdowntoquizness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable {
    // A hashmap data structure for holding usernames and passwords pair
    //HashMap<String, String> hmCredentials;
    ArrayList <HashMap<String, String>> usersList;
    //boolean student;
    //boolean admin;
    //int userID;

    public Data(){
        usersList = new ArrayList<>();
        HashMap<String, String> hmCredentials = new HashMap<>();

        // Adding some items into the hashmap table
        hmCredentials.put("username", "test");
        hmCredentials.put("password", "1234");
        hmCredentials.put("role", "admin");
        usersList.add(hmCredentials);
        //this.student = false;
        //this.admin = true;
        //this.userID = 0;
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
        //Boolean  retval = true;
        int index = 0;
        boolean nameFound = false;
        do{
            if(usersList.get(index).get("username") == username){
                nameFound = true;
            }
            index++;
        }while(index < usersList.size() && !nameFound);
        // Write your code here
        //if (!(hmCredentials.containsKey(username)))
            //retval = false;
        // End of my code

        return nameFound;
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //CheckCredentials method checks the given username and password credentials
    public Boolean CheckCredentials(String username, String Password){
        Boolean  retval = true;
        int index = getIndex(username);

        if (!CheckUsername(username) || !usersList.get(index).get("password").equals(Password)){
            retval = false;
        }
        // Write your code here
        //if (!CheckUsername(username) || !(hmCredentials.get(username).equals(Password)))
            //retval = false;
        // End of my code

        return retval;
    }
    public int getIndex(String userName){
        int index = -1;
        for(int i = 0; i < usersList.size(); i++){
            if(usersList.get(i).get("username")== userName){
                index = i;
            }
        }
        return index;
    }
    public boolean isStudent(String name){
        int index = getIndex(name);
        boolean student = false;
        if(usersList.get(index).get("role") == "student"){
            student = true;
        }
        return student;
    }
    public boolean isAdmin(String name){
        int index = getIndex(name);
        boolean admin = false;
        if(usersList.get(index).get("role") == "admin"){
            admin = true;
        }
        return admin;
    }
}

