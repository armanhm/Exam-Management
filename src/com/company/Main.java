package com.company;

import java.time.LocalTime;
import java.util.*;

//Main class
public class Main {
    static int n = 0, qs = 0, delete = 0;
    static boolean test = false;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();
    static ArrayList<DirectorOfEducation> direcetors = new ArrayList<>();
    static ArrayList<Exam> exams = new ArrayList<>();
    static ArrayList<Integer> types = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();
    static HashMap<String, String> users = new HashMap<>();
    static HashMap<String, Integer> examCodes = new HashMap<>();
    static HashMap<String, Integer> numsS = new HashMap<>();
    static HashMap<String, Integer> numsD = new HashMap<>();
    static HashMap<String, Integer> numsT = new HashMap<>();
    static HashMap<String, Integer> temp = new HashMap<>();

    public static void main(String[] args) {
        introduction();
    }

    public static void introduction() {
        System.out.println("\n<<Welcome to the exam helper system>>\n");
        System.out.println("Enter a number:");
        System.out.println("1:register");
        System.out.println("2:sign in");
        System.out.println("3.Exit");
        String choice = scanner.next();
        switch (choice) {
            case "1": {
                register();
                break;
            }
            case "2": {
                signIn();
                break;
            }
            case "3": {
                System.out.println("\nGoodBye!");
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Please enter 1 or 2 or 3");
                introduction();
                break;
            }
        }
    }

    public static void register() {
        System.out.println("Enter username: ");
        String username = scanner.next();
        while (!validate(username,"username")){
            System.out.println("This username is already exists!");
            navigator();
            System.out.println("Enter another username : ");
            username = scanner.next();
        }
        System.out.println("Enter password: ");
        String password = scanner.next();
        while (!validate(password,"password")){
            System.out.println("password should be at least 4 characters!");
            navigator();
            System.out.println("Enter another password : ");
            password = scanner.next();
        }
        users.put(username, password);
        System.out.println("Enter your type:");
        System.out.println("1: Director of eduction");
        System.out.println("2: Teacher");
        System.out.println("3: student");
        int type = typeChecker(scanner.nextInt());
        System.out.println("\nNow you are registered!\n");
        temp.put(username, type);
        types.add(type);
        if (type == 1) {
            DirectorOfEducation director =
                    new DirectorOfEducation(username, password);
            direcetors.add(director);
            int m = numsD.size();
            numsD.put(username, m);

        } else if (type == 2) {
            Teacher teacher = new Teacher(username, password);
            teachers.add(teacher);
            int m = numsT.size();
            numsT.put(username, m);
        } else if (type == 3) {
            Student student = new Student(username, password);
            students.add(student);
            int m = numsS.size();
            numsS.put(username, m);
        }
        introduction();

    }

    public static void signIn() {
        System.out.println("Enter username: ");
        String username = scanner.next();
        if (!users.containsKey(username)) {
            System.out.println("Username not found!");
            navigator();
            signIn();
        }
        System.out.println("Enter password: ");
        String password = scanner.next();
        if (!users.containsKey(username)) {
            System.out.println("\nThe username you entered was not found!");
            navigator();
        } else if (users.get(username).equals(password)) {
            switch (temp.get(username)) {
                case 1: {
                    directorProfile(direcetors.get(numsD.get(username)));
                    break;
                }
                case 2: {
                    teacherProfile(teachers.get(numsT.get(username)));
                    break;
                }
                case 3: {
                    studentProfile(students.get(numsS.get(username)));
                    break;
                }
            }

        } else {
            System.out.println("Wrong password!\n");
            navigator();
            signIn();
        }
    }

    public static boolean validate(String data, String type) {
        if (type.equals("password")) {
            return (data.length() >= 4);
        }
        else if (type.equals("username")) {
            return (!users.containsKey(data));
        }
        else {
            return false;
        }

    }


    public static boolean validate(String data) {
        if (!examCodes.containsKey(data)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean codeChecker(String code) {
        return examCodes.containsKey(code);
    }

    public static String testChecker(String code) {
        if (exams.get(examCodes.get(code)).getType().equals("test")) {
            return code;
        }
        if (!test) {
            System.out.println("There is no test in database");
            return "-1";
        } else {
            System.out.println("This code doesn't belong to test!");
            navigator();
            System.out.println("please try another code:");
            testChecker(code);
        }
        return null;
    }

    public static boolean usernameChecker(String username, String type) {
        switch (type) {
            case "director":
            {
                if (!numsD.containsKey(username)) {
                    System.out.println("There is no director of education with this username!");
                    return false;
                } else {
                    return true;
                }
            }
            case "teacher": {
                if (!numsT.containsKey(username)) {
                    System.out.println("There is no teacher with this username!");
                    return false;
                } else {
                    return true;
                }
            }
            case "student": {
                if (!numsS.containsKey(username)) {
                    System.out.println("There is no student with this username!");
                    return false;
                } else {
                    return true;
                }
            }
            default:{
                return true;
            }
        }
    }

    public static int typeChecker(int type) {
        if (type == 1 || type == 2 || type == 3) {
            return type;
        } else {
            System.out.println("Wrong type entered!");
            System.out.println("Enter your type :");
            System.out.println("1: Director of eduction");
            System.out.println("2: Teacher");
            System.out.println("3: student");
            typeChecker(scanner.nextInt());
        }
        return 0;
    }

    public static boolean examVerifier(Exam exam, String username) {
        if (!exam.students.containsKey(username)) {
            System.out.println("This username doesn't belong to exam!");
            return false;
        } else {
            return true;
        }
    }

    public static boolean dateChecker(String date) {
        if (!date.contains("/") || date.length() != 8 ||
                date.charAt(2) != '/' || date.charAt(5) != '/') {
            System.out.println("Wrong date");
            return false;
        } else {
            return true;
        }

    }

    public static void directorProfile(DirectorOfEducation director) {
        int choice = 0;
        System.out.println("\nwelcome to your profile ((" + director.getUsername() + "))");
        System.out.println("**You logged in as a director of education**");
        while (choice != 9) {
            System.out.println("Menu (please  enter you choice)");
            System.out.println("1.Create new exam");
            System.out.println("2.specify students of exam");
            System.out.println("3.Modify the teacher of exam");
            System.out.println("4.Edit exam's data");
            System.out.println("5.Change the students of exam");
            System.out.println("6.Change the teacher of exam");
            System.out.println("7.Delete the exam");
            System.out.println("8.Print the scores of exam");
            System.out.println("9.Sign Out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    Exam exam = new Exam();
                    createExam(director, exam);
                    break;
                }
                case 2: {
                    System.out.println("\nYou want to add student to exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("No match found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    Exam exam = exams.get(examCodes.get(code));
                    editExam(exam, "addStudent");
                    break;
                }
                case 3: {
                    System.out.println("\nYou want to modify the teacher of exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found!");
                        navigator();
                        System.out.println("Enter your code again: ");
                        code = scanner.next();
                    }
                    System.out.println("Enter the username of teacher:");
                    String username = scanner.next();
                    while (!usernameChecker(username , "teacher"))
                    {
                        System.out.println("There is no teacher with this username!");
                        navigator();
                        System.out.println("Enter the username of teacher again: ");
                    }
                    exams.get(examCodes.get(code)).setTeacher(teachers.get(numsT.get(username)));
                    break;
                }
                case 4: {
                    System.out.println("\nYou want to edit exam's data!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("code not found!! ");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "changeExam");
                    break;
                }
                case 5: {
                    System.out.println("\nYou want to change the student of exam\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println(" Code not found!! ");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "changeStudent");

                    break;
                }
                case 6: {
                    System.out.println("\nYou want to change the teacher of exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code of exam not found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    System.out.println("Enter the username of teacher:");
                    String username = scanner.next();
                    while (!usernameChecker(username , "teacher"))
                    {
                        System.out.println("There is no student with this username!");
                        navigator();
                        System.out.println("Enter the username of student again:");
                        username = scanner.next();
                    }
                    exams.get(examCodes.get(code)).setTeacher(teachers.get(numsT.get(username)));
                    break;
                }
                case 7: {
                    System.out.println("\nYou want to delete the exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found!");
                        navigator();
                        System.out.println("enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "delete");
                    break;
                }
                case 8: {
                    System.out.println("\nYou want to print the score of exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found! ");
                        navigator();
                        System.out.println("Enter your code again: ");
                        code = scanner.next();
                    }
                    printScores(exams.get(examCodes.get(code)));
                    break;
                }
                case 9: {
                    continue;
                }
                default: {
                    System.out.println("\nWrong input(Enter a number between 1 to 9)\n");
                }
            }
        }
        introduction();
    }

    public static void teacherProfile(Teacher teacher) {
        int choice = 0;
        while (choice != 10) {
            System.out.println("\nWelcome to your profile ((" + teacher.getUsername() + "))");
            System.out.println("**You logged in as a teacher**");
            System.out.println("Menu (please  enter you choice)");
            System.out.println("1.Specify the type of exam");
            System.out.println("2.Add question to exam");
            System.out.println("3.Edit the questions of exam");
            System.out.println("4.Delete question from exam");
            System.out.println("5.Add answer sheet for test");
            System.out.println("6.Correct the exam");
            System.out.println("7.Submit the score of student");
            System.out.println("8.Edit the score of student");
            System.out.println("9.Answer the student's questions");
            System.out.println("10.Sign out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("\nYou want to specify the type of exam\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("No match , sorry");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();

                    }
                    editExam(exams.get(examCodes.get(code)), "specify");
                    break;
                }
                case 2: {
                    System.out.println("\nYou want to add a question to exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found!!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "addQuestion");
                    break;
                }
                case 3: {
                    System.out.println("\nYou want to edit the questions of exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not Found!!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "editQuestion");
                    break;
                }
                case 4: {
                    System.out.println("\nYou want to delete a question from exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not Found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editExam(exams.get(examCodes.get(code)), "deleteQuestion");
                    break;
                }
                case 5: {
                    System.out.println("\nYou want to add answer sheet for test!\n");
                    System.out.println("Enter the code of test:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println(" Code not found!!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    code = testChecker(code);
                    if (Objects.requireNonNull(code).equals("-1")) {
                        continue;
                    }
                    answerSheet(exams.get(examCodes.get(code)));
                    break;
                }
                case 6: {
                    System.out.println("\nYou want to correct the exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found!! ");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editScore(exams.get(examCodes.get(code)), "Correct");
                    break;
                }
                case 7: {
                    System.out.println("\nYou want to submit the score of student!\n");
                    System.out.println("Enter the code of exam");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println(" Code not found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editScore(exams.get(examCodes.get(code)), "addScore");
                    break;
                }
                case 8: {
                    System.out.println("\nYou want to edit the score of student!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println(" Code not found! ");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    editScore(exams.get(examCodes.get(code)), "editScore");
                    break;
                }
                case 9: {
                    System.out.println("\n\n You want to answer the student's questions!\n");
                    inbox(teacher);
                    break;
                }
                case 10: {
                    continue;
                }
                default: {
                    System.out.println("\nWrong input(Enter a number between 1 to 9)\n");
                }
            }
        }
        introduction();
    }

    public static void studentProfile(Student student) {
        System.out.println("\nWelcome to your profile ((" + student.getUsername() + "))");
        System.out.println("**You logged in as a student**");
        int choice = 0;
        while (choice != 4) {
            inbox(student);
            System.out.println("Menu (please  enter you choice)");
            System.out.println("1.Take part in exam");
            System.out.println("2.See the result of exam");
            System.out.println("3.Ask question from teacher");
            System.out.println("4.Sign out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    boolean a = true;
                    System.out.println("\nYou want to take part in an exam!\n");
                    System.out.println("Enter the code of exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("Code not found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    Exam exam = exams.get(examCodes.get(code));
                    if (exam.participants.contains(student)) {
                        exam.addToExam(student);
                        System.out.println("The type of exam is: " + exam.getType());
                        System.out.printf("The duration of exam is %d minutes\n", exam.getDuration());
                        System.out.println("The number of whole questions is: " + exam.getNumberOfQuestions());
                        System.out.println("During the exam you can enter ((c!)) to see the remaining time!");
                        LocalTime time = LocalTime.now();
                        System.out.printf("The start time is : %.8s\n", time);
                        System.out.println("Good Luck!!!");
                        takeExam(exams.get(examCodes.get(code)), student);
                        while (a) {
                            if (exam.scores.containsKey(student.getUsername())) {
                                System.out.println("Your result is : " + exam.scores.get(student.getUsername()));
                                a = false;
                            } else {
                                System.out.println("\nYou are not allowed to take part to this exam anymore!\n");
                                System.out.println("Do you want to enter another code?(Enter y or n)");
                                char c = scanner.next().charAt(0);
                                if (c == 'n') {
                                    a = false;
                                }
                            }
                        }
                    } else {
                        System.out.println("\nYou already took part in this exam!\n");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\nYou want to see the result of exam!\n");
                    System.out.println("Enter the code of exam:");
                    boolean a = true;
                    String code = scanner.next();
                    while (!codeChecker(code)) {
                        System.out.println("no match found!");
                        navigator();
                        System.out.println("Enter your code again:");
                        code = scanner.next();
                    }
                    Exam exam = exams.get(examCodes.get(code));
                    while (a) {
                        if (exam.scores.containsKey(student.getUsername())) {
                            System.out.println("Your result is : " + exam.scores.get(student.getUsername()));
                            a = false;
                        } else {
                            System.out.println("\nYou are not allowed to take part to this exam!\n");
                            System.out.println("Do you want to enter another code?(Enter y or n)");
                            char c = scanner.next().charAt(0);
                            if (c == 'n') {
                                a = false;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nYou want to ask question from teacher!\n");
                    System.out.println("Enter the code of your exam:");
                    String code = scanner.next();
                    while (!codeChecker(code)){
                        System.out.println("The code is incorrect!");
                        navigator();
                        System.out.println("Enter the code again:");
                        code = scanner.next();
                    }
                    System.out.println("Enter your question:");
                    String question = scanner.next();
                    student.hasQuestion.put(code, true);
                    student.hasAnswer.put(code, false);
                    Question question1 = new Question();
                    question1.setData(question);
                    question1.setQuestioner(student);
                    question1.setResponder(exams.get(examCodes.get(code)).getTeacher());
                    question1.setResponded(false);
                    questions.add(question1);
                    exams.get(examCodes.get(code)).setStudentQuestions(question);
                    System.out.println("Your question will be answered soon!\n");
                    exams.get(examCodes.get(code)).cq++;
                    qs++;
                    break;
                }
                case 4: {
                    continue;
                }
                default: {
                    System.out.println("Invalid input!");
                    break;
                }
            }
        }
        introduction();
    }

    public static void createExam(DirectorOfEducation director, Exam exam) {
        System.out.println("Enter the username of teacher:");
        String username = scanner.next();
        while (!usernameChecker(username , "teacher"))
        {
            System.out.println("There is no teacher with this username");
            navigator();
            System.out.println("Enter the username of teacher again: ");
            username = scanner.next();
        }
        System.out.println("Enter the name of lesson:");
        exam.setLesson(scanner.next());
        System.out.println("Enter the code of exam(must be unique):");
        String code = scanner.next();
        while (!validate(code)){
            System.out.println("This code is already exists!");
            navigator();
            System.out.println("Enter another exam code:");
            code = scanner.next();
        }
        exam.setCode(code);
        examCodes.put(code, n);
        n++;
        exams.add(exam);
        System.out.println("Enter the date of exam:(( format: yy/mm/dd ))");
        String date = scanner.next();
        while (!dateChecker(date)){
            System.out.println("Wrong input");
            System.out.println("The example of correct date is : 19 / 04 / 22 ");
            navigator();
            System.out.println("Enter the date again:");
            date = scanner.next();
        }
        exam.setDate(date);
        System.out.println("Enter the duration of exam:(minutes)");
        exam.setDuration(scanner.nextLong());
        System.out.println("Enter the number of questions:");
        exam.setNumberOfQuestions(scanner.nextLong());
        System.out.println("\nThe exam created successfully!\n");
    }

    public static void editExam(Exam exam, String type) {
        switch (type) {
            case "delete": {
                int t = examCodes.get(exam.getCode());
                exams.remove(exam);
                examCodes.remove(exam.getCode());
                break;
            }
            case "specify": {
                System.out.printf("\nEnter the type of exam %s:(Enter 1 or 2 or 3)\n", exam.getCode());
                System.out.println("1.Test");
                System.out.println("2.Descriptive");
                System.out.println("3.Mixed(test and descriptive)");
                System.out.println("(( Enter 1 or 2 or 3 ))");
                int choice = scanner.nextInt();
                exam.setType(choice);
                if (choice == 1) {
                    exam.setType(1);
                    test = true;
                } else if (choice == 2) {
                    exam.setType(2);
                } else if (choice == 3) {
                    exam.setType(3);
                }
                System.out.println("The type specified successfully!");
                break;
            }
            case "addStudent": {
                System.out.println("How many students do you want to add to exam?");
                int n = scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    System.out.printf("\nEnter the username of student number %d:\n", (i + 1));
                    String username = scanner.next();
                    while (!usernameChecker(username , "student"))
                    {
                        navigator();
                        System.out.println("Enter the username of student again:");
                        username = scanner.next();
                    }
                    exam.addToExam(students.get(numsS.get(username)));
                    System.out.printf("Student %s successfully added to exam %s!\n", username, exam.getCode());
                }
                if(n != 1) {
                    System.out.printf("\n%d students successfully added to exam!\n", n);
                }
                break;
            }
            case "addQuestion": {
                System.out.println("How many questions do you want to add to exam?");
                int n = scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    System.out.println("Enter your question");
                    String question = scanner.next();
                    System.out.println("Enter the answer of question");
                    String answer = scanner.next();
                    exam.questions.put(exam.questions.size(), question);
                    exam.answers.put(exam.answers.size(), answer);
                    System.out.printf("The number of question is : %d\n", (i+1));
                }
                System.out.println("\nYour questions successfully added to exam!\n");
                break;
            }
            case "changeQuestion": {
                System.out.println("Enter the question number:");
                System.out.printf(" (The number should be between 1 to %d!\n", (exam.questions.size()+1));
                int num = scanner.nextInt();
                if (num >= exam.questions.size() || num < 1) {
                    System.out.println("!ERROR!");
                    editExam(exam, "changeQuestion");
                }
                System.out.println("Enter new question:");
                String question = scanner.next();
                exam.questions.put(num, question);
                System.out.println("Enter new answer:");
                String answer = scanner.next();
                exam.answers.put(num, answer);
                System.out.printf("\nThe question number %d successfully edited!\n", num);
                break;
            }
            case "deleteQuestion": {
                System.out.println(" Enter the question number:");
                System.out.printf("(The number should be between 1 to %d!\n", (exam.questions.size()+1));
                int number = scanner.nextInt();
                if (number >= exam.questions.size() || number < 1) {
                    System.out.println("ERROR");
                    editExam(exam, "changeQuestion");
                }
                exam.questions.put(number, "deleted");
                exam.answers.put(number, "deleted");
                delete++;
                exam.delete++;
                System.out.println("\nYour question deleted successfully");
                System.out.println("But the unique code of question is still unreachable!\n");
                break;
            }
            case "changeExam": {
                exam.changeData();
                break;
            }
            case "changeStudent": {
                System.out.println("Enter your action:");
                System.out.println("1.Add student to exam");
                System.out.println("2.remove student from exam");
                System.out.println("3.Back to the profile");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("You want to add student to exam!");
                    System.out.println("Enter the username of student:");
                    String username = scanner.next();
                    while (!usernameChecker(username , "teacher"))
                    {
                        System.out.println("There is no student with this username ");
                        navigator();
                        System.out.println("Enter the username of student again:");
                        username = scanner.next();
                    }
                    exam.addToExam(students.get(numsS.get(username)));
                    System.out.printf("Student %s successfully added to exam %s!\n", username, exam.getCode());
                } else if (choice == 2) {
                    System.out.println("You want to remove student from exam!");
                    System.out.println("Enter the username of student:");
                    String username = scanner.next();
                    while (!usernameChecker(username , "teacher"))
                    {
                        System.out.println("There is no student with this username ");
                        navigator();
                        System.out.println("Enter the username of student again: ");
                        username = scanner.next();
                    }
                    for (int i = exam.students.get(username) + 1; i < exam.students.size(); i++) {
                        exam.students.remove(exam.participants.get(i).getUsername(), i - 1);
                    }
                    exam.students.remove(username);
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Wrong input!");
                    editExam(exam, "changeStudent");
                }
                break;
            }
        }
    }

    public static void printScores(Exam exam) {
        System.out.printf("\nYou want to print the scores of exam %s!\n", exam.getCode());
        System.out.printf("The number of students in this exam :%d\n", exam.scores.size());
        double sum = 0;
        for (int i = 0; i < exam.scores.size(); i++) {
            System.out.printf("< student:%s , score:%.3f >\n"
                    , exam.list.get(i), exam.scores.get(exam.list.get(i)));
            sum += exam.scores.get(exam.list.get(i));
        }
        System.out.printf("\nThe average of score is : %.2f\n", (sum / exam.scores.size()));
    }

    public static void answerSheet(Exam exam) {
        long size = exam.getNumberOfQuestions();
        HashMap<Integer, Character> answers = new HashMap<>();
        System.out.println("Enter a or b or c or d for each question");
        for (int i = 0; i < size; i++) {
            System.out.printf("Enter answer for question %d: ", i);
            answers.put(i, scanner.next().charAt(0));
            System.out.println();
        }
        exam.setAnswerSheet(answers);
        System.out.println("Your answer sheet successfully added to exam!\n");
    }

    public static void editScore(Exam exam, String type) {
        if (type.equals("addScore") || type.equals("editScore")) {
            System.out.println("Enter the username of student:");
            String username = scanner.next();
            while (!examVerifier(exam,username)){
                navigator();
                System.out.println("Enter the username again:");
                username = scanner.next();
            }
            System.out.println("Enter the score of student:");
            double score = scanner.nextDouble();
            exam.scores.put(username, score);
        } else if (type.equals("correct")) {
            for (int i = 0; i < exam.students.size(); i++) {
                String username = exam.participants.get(i).getUsername();
                Student student = students.get(numsS.get(username));
                String[] answers = student.getExams().get(exam.getCode());
                double score = 0;
                System.out.printf("\nStudent with username ((%s))\n", username);
                for (int j = 0; j < exam.questions.size(); j++) {
                    if (!answers[j].equals("deleted")) {
                        System.out.println("Answer for question number " + j + " :");
                        System.out.println(answers[j]);
                        System.out.println("Enter the score of question:");
                        score += scanner.nextDouble();
                    }
                }
                exam.setScores(username, score);
                System.out.println("The final score of student is : " + score + " !");
            }
        }
    }

    public static void takeExam(Exam exam, Student student) {
        if (exam.questions.size() == 0) {
            System.out.println("The exam is empty!");
        } else {
            String[] answers = new String[exam.questions.size()];
            if (exam.getType().equals("test")) {
                System.out.println("Enter  a  or  b  or  c  or  d");
            }
            long startTime = System.currentTimeMillis() / 1000;
            for (int i = 0; i < exam.questions.size(); i++) {
                long currentTime = System.currentTimeMillis() / 1000;
                String ans;
                if ((currentTime - startTime) >= (exam.getDuration() * 60)) {
                    System.out.println("Time is up!!!");
                    break;
                }
                System.out.println("\nQuestion number " + i);
                System.out.println(exam.questions.get(i));
                System.out.println("Enter your answer:");
                ans = scanner.next();
                if (ans.equals("c!")) {
                    long remainingTime = (exam.getDuration() * 60) - (currentTime - startTime);
                    LocalTime time2 = LocalTime.now();
                    System.out.printf("The current time is :%.8s\n", time2);
                    System.out.printf("The remaining time is: %d:%d\n", (remainingTime / 60), (remainingTime % 60));
                    System.out.println("Enter the answer of question:");
                    ans = scanner.next();
                }
                answers[i] = ans;
            }
            System.out.println("The exam had been finished!");
            student.setExams(exam.getCode(), answers);
            exam.students.put(student.getUsername(), exam.students.size());
            if (exam.getType().equals("test")) {
                double score = 0, correctAnswer = 0, wrongAnswer = 0;
                for (int i = 0; i < exam.questions.size(); i++) {
                    if (answers[i].equals(exam.answerSheet.get(i))) {
                        correctAnswer++;
                    } else {
                        wrongAnswer++;
                    }
                }
                score = ((((3 * correctAnswer) - wrongAnswer) / exam.questions.size()) * 100);
                exam.scores.put(student.getUsername(), score);
            }
        }
    }

    public static void navigator() {
        System.out.println("Enter  m  for main menu");
        System.out.println("Enter  c  for continue trying");
        char a = scanner.next().charAt(0);
        boolean check = true;
        while (check) {
            if (a == 'm') {
                introduction();
                check = false;
            }
            if (a == 'c') {
                check = false;
            } else {
                System.out.println("Wrong input entered!");
                System.out.println("Enter  m  for main menu");
                System.out.println("Enter  c  for continue trying");
                a = scanner.next().charAt(0);
            }
        }
    }

    public static void inbox(Teacher teacher) {
        int j = 0;
        for (int i = 0; i < questions.size(); i++) {
            if(questions.get(i).getResponder().getUsername().equals(teacher.getUsername())
                    && !questions.get(i).isResponded()){
                System.out.print("The Question: ");
                System.out.print(questions.get(i).getData());
                System.out.println("\nEnter your answer: ");
                String answer = scanner.next();
                questions.get(i).setAnswer(answer);
                questions.get(i).setResponded(true);
                questions.get(i).setSeen(false);
                qs--;
                j++;
            }
        }
        if(j == 0){
            System.out.println("\nYou have no new massages!\n");
        }
    }

    public static void inbox(Student student) {
        for (Question question : questions) {
            if (question.getQuestioner().equals(student) && question.isResponded() && !question.isSeen()) {
                System.out.println("\nYour Question: ");
                System.out.println(question.getData());
                System.out.println("The teacher's answer: ");
                System.out.println(question.getAnswer() + "\n");
                question.setSeen(true);
            }
        }
    }
}
//end of Main class






