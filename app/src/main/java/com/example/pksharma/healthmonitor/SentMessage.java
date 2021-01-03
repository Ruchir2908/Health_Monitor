package com.example.pksharma.healthmonitor;

public class SentMessage implements Item {

    String message;
    public final int TYPE = 1;

    public SentMessage(String message) {
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
        return 1;
    }
}
