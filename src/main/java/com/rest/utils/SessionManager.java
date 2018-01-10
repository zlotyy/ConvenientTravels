package com.rest.utils;


import java.util.ArrayList;

/**
 * klasa sluzy do sprawdzania czy istnieje jeszcze sesja dla aplikacji restowej
 */
public class SessionManager {

    public static boolean CheckIfUserLogged(String token, ArrayList<String> session_tokensList){
        if(session_tokensList.contains(token)){
            return true;
        } else {
            return false;
        }
    }
}
