package com.jennisung.weshare;

import static com.amplifyframework.core.Amplify.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import com.jennisung.weshare.Activities.ProfileActivity;

import com.jennisung.weshare.Activities.SplashPageActivity;
import com.jennisung.weshare.Adapters.DonateRequestRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    List<UsableLocation> userUsableZipcodes;
    private String currentUserID = "";
    private String userZipCode = "";


    public static final String REQUEST_NAME_EXTRA_TAG = "REQUEST";

    // TODO: step 2-3 make some data items
    List<AssistanceRequest> assistanceRequest = new ArrayList<>();


    DonateRequestRecyclerViewAdapter adapter;

    Button profileButton;
    Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = findViewById(R.id.MainActivityProfileButton);
        logoutButton = findViewById(R.id.MainActivityLogoutButton);

        setupGoToProfilePageButton();
        setupLogoutButton();

        //product instances must be created before we hand the product instances
         fetchDataFromDatabase();

        setupRecyclerView(assistanceRequest);

        // API impl section

        setCurrentUserID();
        // Call fetchUserZipcode with a callback
        fetchUserZipcode(() -> {
            // This code will be executed when fetchUserZipcode completes
            getData();
        });


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

    void setupGoToProfilePageButton() {
        profileButton.setOnClickListener(view -> {
            Intent goToSplashPageActivityIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(goToSplashPageActivityIntent);
        });

    }

    void setupLogoutButton() {
            logoutButton.setOnClickListener(view -> {
        AuthSignOutOptions signOutOptions = AuthSignOutOptions.builder()
                .globalSignOut(true)
                .build();

        Auth.signOut(signOutOptions,
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

    //------------------------------API Call section --------------------------------------------------------------

    interface OnFetchUserZipcodeCompleted {
        void onCompleted();
    }

    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);


        String uriForLoggedInUser = modifyUriString(userZipCode);
        Log.i("MyInputAPI", "uri passed in: " + uriForLoggedInUser);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, uriForLoggedInUser, new Response.Listener<String>() {

            public void onResponse(String response) {

                Gson gson = new Gson();
                MyAPIObject myAPIObject = gson.fromJson(response.toString(), MyAPIObject.class);
                userUsableZipcodes = generateListOfLocations(myAPIObject);
                displayUsableZipCodes(userUsableZipcodes);
                Log.i("JSON-Response", "Raw JSON response: " + response);


            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    List<UsableLocation> generateListOfLocations(MyAPIObject myAPIObject){
        List<UsableLocation> myUsableZipCodeObjects = new ArrayList<>();
        MyAPIObject.ResultObject[] resultsArray = myAPIObject.getResults();
        for (MyAPIObject.ResultObject result : resultsArray) {
            myUsableZipCodeObjects.add(new UsableLocation(result.code, result.city, result.distance));
        }
        return  myUsableZipCodeObjects;
    }

    void displayUsableZipCodes(List<UsableLocation> inputList){
        for (UsableLocation location : inputList){
            Log.i("ZIP-CODE", "usable zipcode is: " + location.code);
        }
    }

    String modifyUriString(String localZipCode){
        Log.i("Local-ZipCode", "local zipcode: " + localZipCode);
        String modifiedUri = String.format("https://app.zipcodebase.com/api/v1/radius?apikey=2a3f9070-52ad-11ee-bda8-a7ae75877259&code=%s&radius=6&country=us&unit=miles",localZipCode);
        return modifiedUri;
    }

    //------------------------amplifyUser setup---------------------------------------
    void setCurrentUserID(){
        Auth.getCurrentUser(
                success -> {
                    currentUserID = success.getUserId().toString();
                    Log.i(TAG, "Authenticated user is "+ success.getUserId().toString());
                },
                failure -> {
                    Log.i(TAG, "Could not find authenticated");
                }
        );
    }
    void fetchUserZipcode(OnFetchUserZipcodeCompleted callback){
        Auth.fetchUserAttributes(
                attributes -> {
                    for (AuthUserAttribute attribute: attributes){
                        String key = attribute.getKey().getKeyString();
                        String value = attribute.getValue();
                        if (key.equals("custom:zipCode")){
                            userZipCode = value.trim();
                            Log.i("ZIPCODE-LOCAL-VALUE", "inside for loop zip "+ userZipCode);
                        }
                    }
                    Log.i("AuthDemo", "User attributes = " + attributes.toString());
                    Log.i("LOCAL-ZIP", "The local zipcode is: " + userZipCode);

                    callback.onCompleted();
                },
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );
    }

}

//Needed helper classes
class MyAPIObject {
    QueryObject query;
    ResultObject[] results;

    static class QueryObject {
        String code;
        String unit;
        String radius;
        String country;
    }

    static class ResultObject {
        String code;
        String city;
        String state;
        String city_en;
        String state_en;
        double distance;
    }

    public void displayResults() {
        for(ResultObject item : results){
            Log.i("QueryObject", "Code "+ item.code);
            Log.i("QueryObject", "City "+ item.city);
            Log.i("QueryObject", "State "+ item.state);
            Log.i("QueryObject", "city_en "+ item.city_en);
            Log.i("QueryObject", "state En "+ item.state_en);
            Log.i("QueryObject", "distance "+ item.distance);
        };
    }

    public ResultObject[] getResults() {
        return results;
    }
}

class UsableLocation {
    String code;
    String city;
    double distance;

    public UsableLocation(String code, String city, double distance) {
        this.code = code;
        this.city = city;
        this.distance = distance;
    }

    public String getCode() {
        return this.code;
    }

    public String getCity() {
        return this.city;
    }

    public double getDistance() {
        return this.distance;
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


