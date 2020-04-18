package com.muttsapp.model;

public class Chat {
    int id;
    String Chat_title;
    int created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChat_title() {
        return Chat_title;
    }

    public void setChat_title(String chat_title) {
        Chat_title = chat_title;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
}
