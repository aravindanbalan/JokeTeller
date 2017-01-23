package com.udacity.joketeller.application.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeOutputBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}