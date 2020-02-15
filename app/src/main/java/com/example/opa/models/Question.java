package com.example.opa.models;

public class Question {

    private String question;
    private String response;
    private int position;

    public Question(String question, String response, int position) {
        this.question = question;
        this.response = response;
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
