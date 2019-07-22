package com.tiunida.courierapp.ActivityLogin;

import com.tiunida.courierapp.ActivityLogin.events.LoginEvents;
import com.tiunida.courierapp.ActivityLogin.ui.LoginViewMvp;
import com.tiunida.courierapp.EventBus.EventBus;
import com.tiunida.courierapp.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter implements LoginPresenterMvp{

    private EventBus mEventBus;
    private LoginViewMvp mLoginMvpView;
    private LoginInteractorMvp mLoginInteractorMvp;


    public LoginPresenter(LoginViewMvp loginMvpView) {
        mLoginMvpView = loginMvpView;
        mLoginInteractorMvp = new LoginInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mLoginMvpView = null;
        mEventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvents event) {
        switch (event.getEventType()) {
            case LoginEvents.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvents.onSignInError:
                onSignInError();
                break;
        }
    }

    public void onUserLevelUnEqual(){
        if (mLoginMvpView != null){
            mLoginMvpView.navigateToLoginScreen();
            mLoginMvpView.showMessage("akun anda tidak bisa di pakai");
        }
    }

    public void onSignInSuccess() {
        if (mLoginMvpView != null) {
            mLoginMvpView.navigateToMainScreen();
        }
    }

    public void onSignInError() {
        if (mLoginMvpView != null) {
            mLoginMvpView.hideProgress();
            mLoginMvpView.enableInputs();
            mLoginMvpView.showMessage("Email dan password tidak sesuai");
        }
    }

    @Override
    public boolean isValifForm(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            //String errorMessage = task.getException().getMessage();
            isValid = false;
            mLoginMvpView.showMessage("Email kosong");
        }
        if (!isEmailValid(email)) {
            isValid = false;
            mLoginMvpView.showMessage("Email tidak falid");
        }
        if (password.isEmpty()) {
            isValid = false;
            mLoginMvpView.showMessage("Password kosong");
        }
        return isValid;
    }

    @Override
    public void validateLogin(String email, String password) {
        if (mLoginMvpView != null) {
            mLoginMvpView.disableInputs();
            mLoginMvpView.showProgress();
        }
        mLoginInteractorMvp.doSignIn(email, password);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
