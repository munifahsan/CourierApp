package com.tiunida.courierapp.ActivityLogin.events;

public class LoginEvents {
    public static final int onSignInError = 0;
    public static final int onSignInSuccess = 1;
    public static final int onUserLevelEqual = 2;
    public static final int onUserLevelUnEqual = 3;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
