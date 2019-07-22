package com.tiunida.courierapp.ActivityLogin.ui;

public interface LoginViewMvp {
    void showProgress();
    void hideProgress();
    void enableInputs();
    void disableInputs();
    void setInputs(boolean enabeled);
    void navigateToMainScreen();
    void navigateToLoginScreen();
    void showMessage(String message);
}
