package com.tiunida.courierapp.ActivityLogin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tiunida.courierapp.ActivityLogin.LoginPresenter;
import com.tiunida.courierapp.ActivityLogin.LoginPresenterMvp;
import com.tiunida.courierapp.ActivityMain.MainActivity;
import com.tiunida.courierapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginViewMvp{

    @BindView(R.id.login_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.emailEdtTxt)
    EditText mEdtTxtEmail;
    @BindView(R.id.passEdtTxt)
    EditText mEdtTxtPass;
    @BindView(R.id.loginBtn)
    Button mLoginButton;


    private LoginPresenterMvp mLoginPresenterMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mLoginPresenterMvp = new LoginPresenter(this);
        mLoginPresenterMvp.onCreate();
    }

    @Override
    protected void onDestroy() {
        mLoginPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.loginBtn)
    public void handleSignIn() {
        if (mLoginPresenterMvp.isValifForm(mEdtTxtEmail.getText().toString(), mEdtTxtPass.getText().toString()))
            mLoginPresenterMvp.validateLogin(mEdtTxtEmail.getText().toString(), mEdtTxtPass.getText().toString());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    public void setInputs(boolean enabeled) {
        mEdtTxtEmail.setEnabled(enabeled);
        mEdtTxtPass.setEnabled(enabeled);
        mLoginButton.setEnabled(enabeled);
    }

    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void navigateToLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
