package com.jennisung.weshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.jennisung.weshare.Activities.SplashPageActivity;
import com.jennisung.weshare.Adapters.DonateRequestRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    public static final String REQUEST_NAME_EXTRA_TAG = "REQUEST";

    // TODO: step 2-3 make some data items
    List<AssistanceRequest> assistanceRequest = new ArrayList<>();


    DonateRequestRecyclerViewAdapter adapter;

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

        //product instances must be created before we hand the product instances
         fetchDataFromDatabase();

        setupRecyclerView(assistanceRequest);

    }




    private void fetchDataFromDatabase() {
        Amplify.API.query(
                ModelQuery.list(AssistanceRequest.class),
                response -> {
                    for (AssistanceRequest request : response.getData()) {
                        assistanceRequest.add(request);
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                },
                failure -> Log.e(TAG, "Could not query DataStore: " + failure)
        );
    }

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

    void setupRecyclerView(List<AssistanceRequest> assistanceRequest) {
//       TODO: step 1-2: Grab Recyclerview
        RecyclerView requestDonateRecyclerView = (RecyclerView) findViewById(R.id.MainActivityRecyclerView);

//        TODO:step 1-3: Set the layout manager for the recyclerview to a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        requestDonateRecyclerView.setLayoutManager(layoutManager);

//        TODO: step 1-5: create and attach recycler view adapater to recycler view
//        DonateRequestRecyclerViewAdapter adapter = new DonateRequestRecyclerViewAdapter();

        //TODO step 3-2 hand in activity context to the adapter
        adapter = new DonateRequestRecyclerViewAdapter(assistanceRequest, this);
//        TODO: step 2-3 hand some data to recycler view adatpr
        requestDonateRecyclerView.setAdapter(adapter);
    }




}


//     void fetchDataFromDatabase() {
//        Amplify.API.query(
//                ModelQuery.list(AssistanceRequest.class),
//                response -> {
//                    assistanceRequest.clear();
//                    for (AssistanceRequest request : response.getData()) {
//                        assistanceRequest.add(request);
//                    }
//                    if(adapter != null) {
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//    }


//     void setupRecyclerView(List<AssistanceRequest> assistanceRequest) {
//        RecyclerView recyclerView = findViewById(R.id.MainActivityRecyclerView);
//        adapter = new DonateRequestRecyclerViewAdapter(this.assistanceRequest);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }



//        setupShowPopupButton();


//    void setupShowPopupButton() {
//        Button showPopupButton = findViewById(R.id.showPopupButton);
//
//        showPopupButton.setOnClickListener(view -> {
//            PopupFormFragment popupFormFragment = new PopupFormFragment();
//            popupFormFragment.show(getSupportFragmentManager(), "popup_form_fragment");
//        });
//    }


