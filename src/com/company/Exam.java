package com.company;

import com.company.DirectorOfEducation;
import com.company.Main;
import com.company.Student;
import com.company.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Exam class
public class Exam {
    private DirectorOfEducation director;
    private Teacher teacher;
    private String lesson = "noLesson";
    private String code = "noCode";
    private String date = "noDate";
    private long duration = 0;
    private long numberOfQuestions = 0;
    private long currentQuestion = 0;
    private int numberOfStudents = 0;
    private String type = "noType";
    ArrayList<Student> participants = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    HashMap<Integer,Character> answerSheet = new HashMap<>();
    public HashMap<Integer,String> questions = new HashMap<>();
    public HashMap<Integer,String> answers = new HashMap<>();
    public HashMap<String,Integer> students = new HashMap<>();
    public HashMap<String,Double> scores = new HashMap<>();
    public HashMap<Integer,String> studentQuestions = new HashMap<>();
    public HashMap<Integer,String> response = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    int delete = 0 , cq = 0;


    public void setScores(String s , double d){

    }

    public void setStudentQuestions(String s){
        studentQuestions.put(studentQuestions.size(),s);
    }

    public String getType() { return type; }

    public void setType(int type) {
        if(type == 1){
            this.type = "test";
        }
        if(type == 2){
            this.type = "Descriptive";
        }
        if(type == 3){
            this.type = "mixed";
        }
    }

    public DirectorOfEducation getDirector() {
        return director;
    }

    public void setDirector(DirectorOfEducation director) {
        this.director = director;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getNumberOfQuestions() {
        return (numberOfQuestions-delete);
    }

    public void setNumberOfQuestions(long numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public long getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(long currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public HashMap<Integer, Character> getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(HashMap<Integer, Character> answerSheet) {
        this.answerSheet = answerSheet;
    }

    public void addToExam(Student student)
    {
        int n = participants.size();
        participants.add(student);
        students.put(student.getUsername(),n);
    }

    public void removeFromExam(Student student){
        students.remove(student.getUsername());
        numberOfStudents--;
        participants.remove(student);
    }

    public void changeData()
    {
        boolean a = true;
        System.out.println("\nWelcome to the setting's page of exam");
        System.out.println("The exam code is : " + getCode()+"\n");
        System.out.println("Change menu:(Enter the number of your choice)");
        while (a)
        {
            System.out.println("1.Change the director of educator");
            System.out.println("2.Change the teacher");
            System.out.println("3.Change the lesson");
            System.out.println("4.Change the date");
            System.out.println("5.Change the duration");
            System.out.println("6.Change the number of numberOfQuestions");
            System.out.println("7.Back to your profile");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("\nYou want to Change the director of Exam");
                    System.out.println("Currently, you are the director of exam!\n");
                    System.out.println("Enter the username of new director for exam:");
                    String username = scanner.next();
                    while (!Main.usernameChecker(username,"director")){

                    }
                    setDirector(Main.direcetors.get(Main.numsD.get(username)));
                    System.out.printf("\nThe director successfully change to %s !\n", getDirector());
                    break;
                }
                case 2:{
                    System.out.println("\nYou want to change the teacher of exam");
                    System.out.printf("Currently %s is the teacher of exam!\n",getTeacher());
                    System.out.println("Enter the username of new teacher for exam:");
                    String username = scanner.next();
                    while (!Main.usernameChecker(username,"teacher")){

                    }
                    setTeacher(Main.teachers.get(Main.numsT.get(username)));
                    System.out.printf("\nThe teacher successfully change to %s !\n",getTeacher());
                    break;
                }
                case 3:{
                    System.out.println("\nYou want to change the lesson of exam");
                    System.out.println("The current lesson is : " + getLesson() + "\n");
                    System.out.println("Enter the new lesson for exam:");
                    setLesson(scanner.next());
                    System.out.printf("The lesson successfully changed to %s !\n",getLesson());
                    break;
                }
                case 4:{
                    System.out.println("\nYou want to change the date of exam");
                    System.out.println("The current date is : " + getDate() + "\n");
                    System.out.println("Enter the new date for exam:(( format : yy/mm/dd ))");
                    String date = scanner.next();
                    while (!Main.dateChecker(date)){
                        System.out.println("Please Enter the correct date:(( format: yy/mm/dd ))");
                        date = scanner.next();
                    }
                    setDate(date);
                    System.out.printf("\nThe date successfully changed to %s !\n",getDate());
                    break;
                }
                case 5:{
                    System.out.println("\nYou want to change the duration of exam");
                    System.out.println("The current duration is : " + getDuration() + "\n");
                    System.out.println("Enter the new duration for exam:");
                    setDuration(scanner.nextLong());
                    System.out.printf("The duration successfully changed to %s !\n",getDuration());
                    break;
                }
                case 6:{
                    System.out.println("\nYou want to change the number of numberOfQuestions");
                    System.out.println("The current number for numberOfQuestions is : " + getNumberOfQuestions() + "\n");
                    System.out.println("Enter the new duration for exam:");
                    setNumberOfQuestions(scanner.nextLong());
                    System.out.printf("The number of numberOfQuestions successfully changed to %s !\n", getNumberOfQuestions());
                    break;
                }
                case 7:{
                    a = false;
                    continue;
                }
                default:{
                    System.out.println("\nWrong input!\n");
                    break;
                }
            }
        }
    }
}
//End of Exam class