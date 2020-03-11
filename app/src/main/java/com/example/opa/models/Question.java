package com.example.opa.models;

public class Question {

    private String question;
    private String response;

    public Question(String question, String response) {
        this.question = question;
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
