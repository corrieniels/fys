/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ezfinder3;

/**
 *
 * @author corni
 */
public class User {
    
    private static int userID;
    private static int userRole;
    
    public User(){
        userID = 0;
        userRole = 0;
    }
    
    public static void setUserID(int id){
        userID = id;
    }
    
    public static int getUserID(){
        return userID;
    }
    
    public static void setUserRole(int role){
        userRole = 0;
    }
    
    public static int getUserRole(){
        return userRole;
    }
   
}
