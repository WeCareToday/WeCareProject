package com.jennisung.weshare.Activities;

import static com.amplifyframework.core.Amplify.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;
import com.jennisung.weshare.R;

public class UpdateProfileActivity extends AppCompatActivity {

    public static String TAG = "UpdateProfileActivity ";
    private String currentUserID = "";
    private String currentUserZipcode = "";

    TextInputEditText inputUsernameEditText;
    TextInputEditText inputZipCodeEditText;
    Button updateProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        inputUsernameEditText = findViewById(R.id.newUserNameEditText);
        inputZipCodeEditText = findViewById(R.id.zipcodeEditTextInput);
        updateProfileButton = findViewById(R.id.updateProfileButton);

        setCurrentUserID();
        setupUpdateButton();
    }

    void setupUpdateButton(){
        updateProfileButton.setOnClickListener(v ->{
            String userNameText = inputUsernameEditText.getText().toString();
            String zipCodeText = inputZipCodeEditText.getText().toString();
           AuthUserAttribute newUserName =
                   new AuthUserAttribute(AuthUserAttributeKey.custom("custom:userName"),userNameText );
           Amplify.Auth.updateUserAttribute(newUserName,
                   success -> Log.i(TAG, "updated username successfully"),
                   failure -> Log.i(TAG, "Failed to update username attribute " + failure)
           );

           AuthUserAttribute newZipCode =
                   new AuthUserAttribute(AuthUserAttributeKey.custom("custom:zipCode"), zipCodeText);
           Amplify.Auth.updateUserAttribute(newZipCode,
                   success -> Log.i(TAG, "updated zipcode successfully"),
                   failure -> Log.i(TAG, "Failed to update zipcode attribute " + failure)
                   );

            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
            Intent backToProfileActivity = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
            startActivity(backToProfileActivity);

        });
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