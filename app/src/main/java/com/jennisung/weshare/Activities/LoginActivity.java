package com.jennisung.weshare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.MainActivity;
import com.jennisung.weshare.R;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LoginActivity";

    Button submitButton;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        passwordEditText = findViewById(R.id.LoginActivityPasswordEditText);
        submitButton = findViewById(R.id.LoginActivitySubmitButton);

        emailEditText = findViewById(R.id.LoginActivityEmailEditText);

        setupSubmitButton();
    }

    void setupSubmitButton() {
        submitButton.setOnClickListener(view -> {
            Amplify.Auth.signIn(
                    emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    success -> {
                        Log.i(TAG, "Sign in Succeeded:" + success.toString());
                        Intent gotoMainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(gotoMainActivityIntent);
                    },
                    failure -> {
                        Log.i(TAG, "Sign in failed:" + failure.toString());
                    }
            );
        });


        }
}
