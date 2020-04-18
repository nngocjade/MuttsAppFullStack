package com.muttsapp.model;

public class Message {

    int id;
    String message;
    int date_sent;
    int chat_id;
    int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(int date_sent) {
        this.date_sent = date_sent;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
