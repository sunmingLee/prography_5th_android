package com.example.sunmin_project;

public class HttpStore {

    private static HttpStore instance;
    private String result;

    HttpStore(){

    }

    public String getResult() {
        return result;
    }

    public void setResult(String s){
        this.result = s;
    }
    public static synchronized HttpStore getInstance() {
        if (instance == null) {
            instance = new HttpStore();
        }
        return instance;
    }

}
