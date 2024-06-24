package com.abdo.braintumordetection.models;

public class ModelChat {

    private String Sender  ;
    private String Message ;

    public ModelChat(String sender, String message) {
        Sender = sender;
        Message = message;
    }

    public ModelChat() {
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
