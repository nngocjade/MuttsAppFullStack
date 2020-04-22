package com.muttsapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserChats {
    String chat_name;
    int chat_id;
    String last_message;
    int sender_id;
    Date date_sent;
    String photo_url;

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getDate_sent() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd/MM/yyyy hh:mm:ss");
        String formatedDate = sdf.format(date_sent);
        //sdf.format(new date()) would return date for the current moment and time

        return formatedDate;
    }

    public void setDate_sent(Date date_sent) {

        this.date_sent = date_sent;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
