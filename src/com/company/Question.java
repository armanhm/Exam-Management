package com.company;

//Question class
public class Question {
    private String data;
    private String answer;
    private Student questioner;
    private Teacher responder;
    private boolean isResponded;
    private boolean isSeen;

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Student getQuestioner() {
        return questioner;
    }

    public void setQuestioner(Student questioner) {
        this.questioner = questioner;
    }

    public Teacher getResponder() {
        return responder;
    }

    public void setResponder(Teacher responder) {
        this.responder = responder;
    }

    public boolean isResponded() {
        return isResponded;
    }

    public void setResponded(boolean responded) {
        isResponded = responded;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
//End of question class