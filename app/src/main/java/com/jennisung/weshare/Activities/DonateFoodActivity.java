package com.jennisung.weshare.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jennisung.weshare.R;

public class DonateFoodActivity extends AppCompatActivity {

    private EditText donorName;
    private EditText foodDescription;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        donorName = findViewById(R.id.donorName);
        foodDescription = findViewById(R.id.foodDescription);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDonation();
            }
        });
    }

    private void submitDonation() {
        String name = donorName.getText().toString();
        String description = foodDescription.getText().toString();

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
