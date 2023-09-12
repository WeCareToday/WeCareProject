package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.R;

public class VerificationActivity extends AppCompatActivity {
    public static String TAG = "VerificationActivity";

    Button submitButton;
    EditText email;
    EditText verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        submitButton = findViewById(R.id.VerificationActivitySubmitButton);
        email = findViewById(R.id.VerificationActivityEmailInput);
        verificationCode = findViewById(R.id.VerificationActivityVerifyCodeInput);

        setupSubmitButton();


    }

//    void setupSubmitButton() {
//        Amplify.Auth.confirmSignUp(
//                email.getText().toString(),
//                verificationCode.getText().toString(),
//                success -> {
//                    Log.i(TAG, "Verification Succeeded:" + success.toString());
//                    Intent gotoLoginPageIntent = new Intent(VerificationActivity.this, LoginActivity.class);
//                    startActivity(gotoLoginPageIntent);
//                },
//                failure -> {
//                    Log.i(TAG, "Verification failed:" + failure.toString());
//                }
//        );
//    }

    void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String verificationCodeText = verificationCode.getText().toString();

            if (emailText.isEmpty() || verificationCodeText.isEmpty()) {
                return;
            }

            Amplify.Auth.confirmSignUp(
                    emailText,
                    verificationCodeText,
                    success -> {
                        Log.i(TAG, "Verification Succeeded:" + success.toString());
                        Intent gotoLoginPageIntent = new Intent(VerificationActivity.this, LoginActivity.class);
                        startActivity(gotoLoginPageIntent);
                    },
                    failure -> {
                        Log.i(TAG, "Verification failed:" + failure.toString());
                    }
            );
        });

    }

}