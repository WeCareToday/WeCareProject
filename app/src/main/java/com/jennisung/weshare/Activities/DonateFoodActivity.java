package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.amplifyframework.datastore.generated.model.FoodListing;
import com.jennisung.weshare.MainActivity;
import com.jennisung.weshare.R;

public class DonateFoodActivity extends AppCompatActivity {

    public static String TAG = "Donate-Activity";
    private String currentUserID = "";

    private EditText availableFoodItemsEditText;
    private CheckBox isWillingToDeliverCheckBox;
    private CheckBox isAvailableForPickupCheckBox;
    private EditText contactEmailEditText;
    private Button submitButton;

    private String assistanceRequestId;
    private AssistanceRequest selectedAssistanceRequest;

    private EditText zipcodeDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        zipcodeDonation = findViewById(R.id.donateZipcode);
        availableFoodItemsEditText = findViewById(R.id.availableFoodItemsEditText);
        isWillingToDeliverCheckBox = findViewById(R.id.isWillingToDeliverCheckBox);
        isAvailableForPickupCheckBox = findViewById(R.id.isAvailableForPickupCheckBox);
        contactEmailEditText = findViewById(R.id.contactEmailEditText);
        submitButton = findViewById(R.id.submitButton);



        Intent intent = getIntent();
        if (intent != null){
            assistanceRequestId = intent.getStringExtra(MainActivity.REQUEST_NAME_EXTRA_TAG).toString().trim();
            Log.i("MyUserId", "AssistanceRequestID = "+ assistanceRequestId);
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFoodListing();
            }
        });
    }

    private void submitFoodListing() {
        setCurrentUserID();

        String availableFoodItems = availableFoodItemsEditText.getText().toString().trim();
        boolean isWillingToDeliver = isWillingToDeliverCheckBox.isChecked();
        boolean isAvailableForPickup = isAvailableForPickupCheckBox.isChecked();
        String contactEmail = contactEmailEditText.getText().toString().trim();
        String zipcodeCollect = zipcodeDonation.getText().toString().trim();

        if (availableFoodItems.isEmpty() || contactEmail.isEmpty()) {
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
            return;
        }



        // Here, create a FoodListing object and use AWS Amplify or another method to store this data in your backend
        // FoodListing newListing = new FoodListing(...);
        // ...
        FoodListing foodListingToSave = FoodListing.builder()
                .availableFoodItems(availableFoodItems)
                .userId(currentUserID)
                .forUserId(assistanceRequestId)
                .isWillingToDeliver(isWillingToDeliver)
                .isAvailableForPickup(isAvailableForPickup)
                .contactEmail(contactEmail)
                .zipcode(zipcodeCollect)

                .build();

        saveToDynamo(foodListingToSave);
        Toast.makeText(this, "Food listing submitted!", Toast.LENGTH_SHORT).show();
        Intent backToProfileActivity = new Intent(DonateFoodActivity.this, ProfileActivity.class);
        startActivity(backToProfileActivity);
    }

    private void saveToDynamo(FoodListing foodListingToSave){
        Amplify.API.mutate(
                ModelMutation.create(foodListingToSave),
                success -> Log.i(TAG, "able to upload to database"),
                failure -> Log.i(TAG, "Could upload to database error: " + failure)

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

}
