package com.example.matthewlocker1.bizme;

/**
 * Created by Matt on 12/17/16.
 */

public class Company {

    String user;
    String password;
    String companyName;
    String bizLat;
    String bizLong;
    String description;

    public Company () {};

    public Company (String user, String password, String companyName, String bizLat,
                    String bizLong, String description){
        this.user = user;
        this.password = password;
        this.companyName = companyName;
        this.bizLat = bizLat;
        this.bizLong = bizLong;
        this.description = description;
    }
}
