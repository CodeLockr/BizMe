package com.example.matthewlocker1.bizme;

/**
 * Created by Matt on 12/18/16.
 *
 * User class is a way to keep track of each unique user and the amount of points they have earned
 * by completing missions for different companies
 *
 * User class includes and array of object CompanyProgress, which includes the company name and the
 * total points the user earned for that company
 */

public class User {

    String username;
    CompanyProgress[] companies;
    int companyIndex;

    int userTotal;

    User (String username, CompanyProgress[] companies, int companyIndex){
        this.username = username;
        this.companies = companies;
        this.companyIndex = companyIndex;
        this.userTotal = 0;
    }

}
