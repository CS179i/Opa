package com.example.opa.models;

import java.util.ArrayList;

public class Question {

    private String question;
    private String response;
    private ArrayList<Response> responses;

    public Question(String question, String response, ArrayList<Response> responses) {
        this.question = question;
        this.response = response;
        this.responses = responses;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setResponseList(ArrayList<Response> response) {
        this.responses = response;
    }

    public ArrayList<Response> getResponseList() {
        return responses;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
