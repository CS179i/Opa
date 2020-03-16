package com.example.opa.models;

public class Response {

    private String position;
    private String response;

    public Response(String position, String response) {
        this.position = position;
        this.response = response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
