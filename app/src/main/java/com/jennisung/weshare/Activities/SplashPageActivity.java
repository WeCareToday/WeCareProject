package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jennisung.weshare.R;

public class SplashPageActivity extends AppCompatActivity {
    public static String TAG = "SplashActivity";

    Button loginButton;

    Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        loginButton = findViewById(R.id.SplashActivityLoginSubmitButton);
        signupButton = findViewById(R.id.SplashActivitySignupSubmitButton);

        setupLoginButton();
        setupSignupButton();
    }

    void setupSignupButton() {
        signupButton.setOnClickListener(view -> {
            Intent goToSignupActivityIntent = new Intent(SplashPageActivity.this, SignupActivity.class);
            startActivity(goToSignupActivityIntent);
        });
    }


    void setupLoginButton() {
        loginButton.setOnClickListener(view -> {
            Intent goToLoginActivityIntent = new Intent(SplashPageActivity.this, LoginActivity.class);
            startActivity(goToLoginActivityIntent);
        });
    }




}