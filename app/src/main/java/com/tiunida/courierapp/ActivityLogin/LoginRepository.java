package com.tiunida.courierapp.ActivityLogin;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.courierapp.ActivityLogin.events.LoginEvents;
import com.tiunida.courierapp.EventBus.EventBus;
import com.tiunida.courierapp.EventBus.GreenRobotEventBus;

public class LoginRepository implements LoginRepositoryMvp {
    private FirebaseAuth mAuth;
    private String TAG = "LoginPresenter";

    public LoginRepository() {
        mAuth = FirebaseAuth.getInstance();
        //mLoginPresenterMvp = new LoginPresenter();
    }

    @Override
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            postEvent(LoginEvents.onSignInSuccess);

                        } else {

                            postEvent(LoginEvents.onSignInError);
                        }

                    }
                });
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvents loginEvent = new LoginEvents();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
