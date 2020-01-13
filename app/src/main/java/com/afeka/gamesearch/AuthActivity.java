package com.afeka.gamesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.afeka.gamesearch.Controller.UserRestIntegration;
import com.afeka.gamesearch.Model.USERS;
import com.afeka.gamesearch.Model.User;

public class AuthActivity extends AppCompatActivity implements UserRestIntegration.OnRestInteractionListener {

    private UserManager userManager;
    private EditText eUsername;
    private EditText ePassword;
    private Spinner spinner;
    private UserRestIntegration userRestIntegration;
    private User userSendedForValidation;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(getString(R.string.layout_bundle));
        AuthLayout al = AuthLayout.values()[bundle.getInt(getResources().getString(R.string.AuthLayout))];
        dialog = new ProgressDialog(this);
        if (al == AuthLayout.login){
            setContentView(R.layout.activity_auth);
            eUsername = findViewById(R.id.editTextLoginName);
            ePassword = findViewById(R.id.editTextLoginPassword);

        }

        else if (al == AuthLayout.signup){
            setContentView(R.layout.activity_auth2);
            eUsername = findViewById(R.id.editTextSignUpName);
            ePassword = findViewById(R.id.editTextSignUpPassword);
            spinner = findViewById(R.id.spinnerUser);
        }
        userManager = new UserManager(getApplicationContext());
        userSendedForValidation = new User();
        userRestIntegration = new UserRestIntegration(this,this);
    }

    public void onClickLogIn(View view) {

        userSendedForValidation.setUserName(eUsername.getText().toString());
        userSendedForValidation.setPassword(ePassword.getText().toString());
        userSendedForValidation.setRole(USERS.PLAYER);
        userRestIntegration.loginUser(userSendedForValidation);
    }

    public void onClickRegister(View view) {
        userSendedForValidation.setUserName(eUsername.getText().toString());
        userSendedForValidation.setPassword(ePassword.getText().toString());
        userSendedForValidation.setRole(USERS.values()[spinner.getSelectedItemPosition()]);
        userRestIntegration.registerUser(userSendedForValidation);
        dialog.setMessage("loading....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onRestRegisterComplete(User user) {
        userManager.updateFullUser(user);
        dialog.dismiss();
        finish();
    }

    @Override
    public void onRestLoginComplete(User user) {
        userManager.updateFullUser(user);
        finish();
    }

    @Override
    public void onRestLoginFail() {

    }
}
