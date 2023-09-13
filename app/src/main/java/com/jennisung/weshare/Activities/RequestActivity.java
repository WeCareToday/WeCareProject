package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.jennisung.weshare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RequestActivity extends AppCompatActivity {

    public static String TAG = "Request Activity";
    private String currentUserID = "";



    EditText donationDescription;
    Switch isForOrganization;
    Switch isCapableOfMeeting;
    SeekBar houseHoldSize;
    EditText needDate;
    EditText dietRestrictions;
    Switch isAnonymousRequest;
    Button submitFormButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        donationDescription = findViewById(R.id.requestActivityDescriptionEditText);
        isForOrganization = findViewById(R.id.requestActivityOrganizationSwitch);
        isCapableOfMeeting = findViewById(R.id.requestActivityMeetingSwitch);
        houseHoldSize = findViewById(R.id.requestActivityHouseholdSizeseekBar);
        needDate = findViewById(R.id.requestActivityDateFieldEditText);
        dietRestrictions = findViewById(R.id.requestActivityDietaryFieldEditText);
        isAnonymousRequest = findViewById(R.id.requestActivityAnonymousSwitch);
        submitFormButton = findViewById(R.id.requestActivitySubmitButton);

        setCurrentUserID();

        setupSubmitForm();


    }



    void setupSubmitForm(){
        submitFormButton.setOnClickListener(v ->{
            setCurrentUserID();
            String description = donationDescription.getText().toString();
            boolean forOrganization = isForOrganization.isChecked();
            boolean capableOfMeeting = isCapableOfMeeting.isChecked();
            int householdSize = houseHoldSize.getProgress();
            String date = needDate.getText().toString();
            String dietaryRestrictions = dietRestrictions.getText().toString();
            boolean anonymousRequest = isAnonymousRequest.isChecked();
            String isoDate = convertToISODate(date);
            Log.i(TAG, "ISO Date equals: "+ isoDate);
            Temporal.DateTime dateTime = convertToTemporalDateTime(isoDate);
            Log.i(TAG, "DateTime to be filled in is"+ dateTime);


            AssistanceRequest requestToSave = AssistanceRequest.builder()
                    .title(description)
                    .description("need description component")
                    .needDate(dateTime)
                    .userId(currentUserID)
                    .contactEmail("need email component")
                    .isRequestAnonymous(anonymousRequest)
                    .isWillingToMeet(capableOfMeeting)
                    .familySize(householdSize)
                    .dietRestrictions(dietaryRestrictions)
                    .isForOrganization(forOrganization)
                    .build();

            saveToDynamo(requestToSave);

            Intent backToProfileActivity = new Intent(RequestActivity.this, ProfileActivity.class);
            startActivity(backToProfileActivity);

        });
    }

    void saveToDynamo(AssistanceRequest itemToSave){
        Amplify.API.mutate(
                ModelMutation.create(itemToSave),
                success -> Log.i(TAG, "able to upload the form to database"),
                failure -> Log.i(TAG, "something wrong happened" + failure)
        );
    }

    void setCurrentUserID(){
        Amplify.Auth.getCurrentUser(
                success -> {
                    currentUserID = success.getUserId().toString();
                    Log.i(TAG, "Authenticated user is "+ success.getUserId().toString());
                },
                failure -> {
                    Log.i(TAG, "Could not find authenticated");
                }
        );
    }

    private String convertToISODate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            Log.i(TAG, "Date was not able to be parsed");
            e.printStackTrace();
            return null;
        }
    }


    //Generated using ChatGPT
    private Temporal.DateTime convertToTemporalDateTime(String isoDate) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = isoFormat.parse(isoDate);

            // Convert the Date object to a Temporal.DateTime-compatible string format
            SimpleDateFormat temporalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            temporalFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String temporalDateString = temporalFormat.format(date);

            // Create a Temporal.DateTime object from the formatted string
            Temporal.DateTime dateTime = new Temporal.DateTime(temporalDateString);

            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

//    void logValues(){
//        // Retrieve values from UI elements
//        String description = donationDescription.getText().toString();
//        boolean forOrganization = isForOrganization.isChecked();
//        boolean capableOfMeeting = isCapableOfMeeting.isChecked();
//        int householdSize = houseHoldSize.getProgress();
//        String date = needDate.getText().toString();
//        String dietaryRestrictions = dietRestrictions.getText().toString();
//        boolean anonymousRequest = isAnonymousRequest.isChecked();
//
//
//        // Log the values
//        Log.i(TAG, "Description: " + description);
//        Log.i(TAG, "For Organization: " + forOrganization);
//        Log.i(TAG, "Capable of Meeting: " + capableOfMeeting);
//        Log.i(TAG, "Household Size: " + householdSize);
//        Log.i(TAG, "Date: " + date);
//        Log.i(TAG, "Dietary Restrictions: " + dietaryRestrictions);
//        Log.i(TAG, "Anonymous Request: " + anonymousRequest);
//        Log.i(TAG, "UserID: "+ currentUserID);
//    }

//    void renderButtons() {
//        if(authUser == null) {
//            logoutButton.setVisibility(View.INVISIBLE);
//            loginButton.setVisibility(View.VISIBLE);
//            signUpButton.setVisibility(View.VISIBLE);
//        } else {
//            logoutButton.setVisibility(View.VISIBLE);
//            loginButton.setVisibility(View.INVISIBLE);
//            signUpButton.setVisibility(View.INVISIBLE);
//        }
//    }
}