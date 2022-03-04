package com.company;
import com.company.User;

import java.util.HashMap;

//Student class
public class Student extends User {
    public HashMap<String , Boolean> hasAnswer = new HashMap<>();
    public HashMap<String , Boolean> hasQuestion = new HashMap<>();

    public Student(String username, String password) {
        super(username, password, 3);
    }
    private HashMap<String,String[]> exams = new HashMap<>();

    public HashMap<String, String[]> getExams() {
        return exams;
    }

    public void setExams(String code , String[] answers) {

    }

}
//End of Student class