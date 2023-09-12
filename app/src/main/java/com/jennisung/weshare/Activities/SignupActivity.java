package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.R;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";

    Button submitButton;
    EditText name;
    EditText email;
    EditText address;
    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        submitButton = findViewById(R.id.SignUpActivitySubmitButton);

        name = findViewById(R.id.SignUpActivityNameInput);
        email = findViewById(R.id.SignUpActivityEmailInputText);
        address = findViewById(R.id.SignUpActivityAddressInputView);
        username = findViewById(R.id.SignUpActivityUsernameInputView);
        password = findViewById(R.id.SignUpActivityPasswordInputView);


        setupSubmitButton();

    }

    void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            Amplify.Auth.signUp(
                    email.getText().toString(),
                    password.getText().toString(),
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                            .userAttribute(AuthUserAttributeKey.nickname(), username.getText().toString())
                            .userAttribute(AuthUserAttributeKey.address(), address.getText().toString())
                            .userAttribute(AuthUserAttributeKey.name(), name.getText().toString())
                            // .userAttribute(AuthUserAttributeKey.custom(), "organization")
                            .build(),
                    successResponse -> {
                        Log.i(TAG, "Signup Succeeded:" + successResponse.toString());
                        Intent gotoVerificationActivity = new Intent(SignupActivity.this, VerificationActivity.class);
                        startActivity(gotoVerificationActivity);
                    },
                    failureResponse -> {
                        Log.i(TAG, "Signup failed with username: sung.jenni93@gmail.com with this " + failureResponse.toString());
                    }
            );
        });
    }



}

