package com.company;
import com.company.User;

//Teacher class
public class Teacher extends User {
    int unreadQuestion = 0;
    public Teacher(String username, String password) {
        super(username, password, 2);
    }
}
//End of Teacher class