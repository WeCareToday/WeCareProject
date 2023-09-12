package com.jennisung.weshare.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.R;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LoginActivity";

    Button submitButton;

    //note: possibly switch to email
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.LoginActivityUsernameEditText);
        passwordEditText = findViewById(R.id.LoginActivityPasswordEditText);
        submitButton = findViewById(R.id.LoginActivitySubmitButton);

        setupSubmitButton();
    }

    void  setupSubmitButton() {
        submitButton.setOnClickListener(view -> {
            Amplify.Auth.signIn(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    success -> Log.i(TAG, "Sign in Succeeded:" + success.toString()),
                    failure -> Log.i(TAG, "Sign in failed:" + failure.toString()));
                });


    }
}
