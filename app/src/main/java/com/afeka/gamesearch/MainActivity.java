package com.afeka.gamesearch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afeka.gamesearch.Model.USERS;

public class MainActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userManager = new UserManager(getApplicationContext());
        validUser();
    }



    public void onClickLogIn(View view) {
        startAuthActivity(AuthLayout.login);
    }

    public void onClickSignUp(View view) {
        startAuthActivity(AuthLayout.signup);
    }

    private void startAuthActivity(AuthLayout layout){
        Intent intent = new Intent(this,AuthActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(getResources().getString(R.string.AuthLayout),layout.ordinal());
        intent.putExtra(getResources().getString(R.string.layout_bundle),bundle);
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String toastText="";
        if (resultCode == RESULT_OK){
            toastText = getString(R.string.login_success);
        }else if (resultCode ==RESULT_CANCELED){
            toastText = getString(R.string.login_fail);
        }
        Toast.makeText(getBaseContext(),toastText,Toast.LENGTH_SHORT).show();
        validUser();
    }

    private void validUser(){
        if (!userManager.getUserType().equals(USERS.INVALID)){
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
        }
    }
}
