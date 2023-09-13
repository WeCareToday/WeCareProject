package com.jennisung.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.Activities.SplashPageActivity;
import com.jennisung.weshare.Adapters.DonateRequestRecyclerViewAdapter;

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
//        setupShowPopupButton();
        setupRecyclerView();

    }


//    void setupShowPopupButton() {
//        Button showPopupButton = findViewById(R.id.showPopupButton);
//
//        showPopupButton.setOnClickListener(view -> {
//            PopupFormFragment popupFormFragment = new PopupFormFragment();
//            popupFormFragment.show(getSupportFragmentManager(), "popup_form_fragment");
//        });
//    }

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

    void setupRecyclerView() {
//       TODO: step 1-2: Grab Recyclerview
        RecyclerView requestDonateRecyclerView = (RecyclerView) findViewById(R.id.MainActivityRecyclerView);

//        TODO:step 1-3: Set the layout manager for the recyclerview to a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        requestDonateRecyclerView.setLayoutManager(layoutManager);

//        TODO: step 1-5: create and attach recycler view adapater to recycler view
        DonateRequestRecyclerViewAdapter adapter = new DonateRequestRecyclerViewAdapter();
        requestDonateRecyclerView.setAdapter(adapter);
    }

    }



