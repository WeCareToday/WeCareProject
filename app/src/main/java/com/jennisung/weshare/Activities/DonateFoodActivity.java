package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jennisung.weshare.R;

public class DonateFoodActivity extends AppCompatActivity {

    private EditText availableFoodItemsEditText;
    private CheckBox isWillingToDeliverCheckBox;
    private CheckBox isAvailableForPickupCheckBox;
    private EditText contactEmailEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        availableFoodItemsEditText = findViewById(R.id.availableFoodItemsEditText);
        isWillingToDeliverCheckBox = findViewById(R.id.isWillingToDeliverCheckBox);
        isAvailableForPickupCheckBox = findViewById(R.id.isAvailableForPickupCheckBox);
        contactEmailEditText = findViewById(R.id.contactEmailEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFoodListing();
            }
        });
    }

    private void submitFoodListing() {
        String availableFoodItems = availableFoodItemsEditText.getText().toString().trim();
        boolean isWillingToDeliver = isWillingToDeliverCheckBox.isChecked();
        boolean isAvailableForPickup = isAvailableForPickupCheckBox.isChecked();
        String contactEmail = contactEmailEditText.getText().toString().trim();

        if (availableFoodItems.isEmpty() || contactEmail.isEmpty()) {
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here, create a FoodListing object and use AWS Amplify or another method to store this data in your backend
        // FoodListing newListing = new FoodListing(...);
        // ...

        Toast.makeText(this, "Food listing submitted!", Toast.LENGTH_SHORT).show();
    }
}
