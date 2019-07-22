package com.tiunida.courierapp.ActivityLogin;

import com.tiunida.courierapp.ActivityLogin.events.LoginEvents;

public interface LoginPresenterMvp {
    boolean isValifForm(String email, String password);
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvents event);
    void onSignInSuccess();
    void onSignInError();
    void onCreate();
    void onDestroy();
}
