package com.jennisung.weshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.Activities.LoginActivity;
import com.jennisung.weshare.Activities.SignupActivity;
import com.jennisung.weshare.Activities.SplashPageActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    Button splashpageButton;
    Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashpageButton = findViewById(R.id.MainActivityGoToSplashPageButton);

        logoutButton = findViewById(R.id.MainActivityLogoutButton);


        setupGoToSplashPageButton();
        setupLogoutButton();

    };

    void setupGoToSplashPageButton() {
        splashpageButton.setOnClickListener(view -> {
            Intent goToSplashPageActivityIntent = new Intent(MainActivity.this, SplashPageActivity.class);
            startActivity(goToSplashPageActivityIntent);
        });

    }
    void setupLogoutButton() {
            logoutButton.setOnClickListener(view -> {
        AuthSignOutOptions signOutOptions = AuthSignOutOptions.builder()
                .globalSignOut(true)
                .build();

        Amplify.Auth.signOut(signOutOptions,
                signOutResult -> {
                    if(signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                        Log.i(TAG, "Global sign out successful!");
                        Intent goToSplashPageActivityIntent = new Intent(MainActivity.this, SplashPageActivity.class);
                        startActivity(goToSplashPageActivityIntent);
                    } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                        Log.i(TAG, "Partial sign out successful!");
                    } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                        Log.i(TAG, "Logout failed: " + signOutResult.toString());
                    }
                });
            });


        }


    }





//Cognito Signup logic
//                Amplify.Auth.signUp("sung.jenni93@gmail.com",
//                  "password1234",
//                        AuthSignUpOptions.builder()
//                                .userAttribute(AuthUserAttributeKey.email(), "sung.jenni93@gmail.com")
//                                .userAttribute(AuthUserAttributeKey.nickname(), "jen")
//                                .userAttribute(AuthUserAttributeKey.address(), "1123")
//                                .userAttribute(AuthUserAttributeKey.name(), "jennifer sung")
//                                .userAttribute(AuthUserAttributeKey.custom(), "organization")
//                                .build(),
//                        successResponse -> Log.i(TAG, "Signup Succeeded:" + successResponse.toString()),
//                        failureResponse -> Log.i(TAG, "Signup failed with username:" + "sung.jenni93@gmail.com" + "with this" + failureResponse.toString())
//
//                    );


// Cognito Confirm Signup
//                Amplify.Auth.confirmSignUp("sung.jenni93@gmail.com",
//                        "236557",
//                        success -> {
//                            Log.i(TAG, "Verification Succeeded:" + success.toString());
//                        },
//
//                        failure -> {Log.i(TAG, "Verification failed:" + failure.toString());
//                        });


//Cognito Signin
//                Amplify.Auth.signIn("sung.jenni93@gmail.com",
//                "password1234",
//                        success -> Log.i(TAG, "Sign in Succeeded:" + success.toString()),
//                        failure -> Log.i(TAG, "Sign in failed:" + failure.toString()));



//Cognito Log out
//        AuthSignOutOptions signOutOptions = AuthSignOutOptions.builder()
//                .globalSignOut(true)
//                .build();
//
//        Amplify.Auth.signOut(signOutOptions,
//                signOutResult -> {
//                    if(signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
//                        Log.i(TAG, "Global sign out successful!");
//                    } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
//                        Log.i(TAG, "Partial sign out successful!");
//                    } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
//                        Log.i(TAG, "Logout failed: " + signOutResult.toString());
//                    }
//                });
