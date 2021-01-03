package com.example.pksharma.healthmonitor;

public class ReceivedMessage implements Item {

    String message;
    public final int TYPE = 0;

    public ReceivedMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTYPE() {
        return TYPE;
    }

    @Override
    public int getItemType() {
        return TYPE;
    }
}
