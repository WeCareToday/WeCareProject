package com.jennisung.weshare.Activities;

import static com.amplifyframework.core.Amplify.Auth;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.core.Amplify;
import com.jennisung.weshare.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    public static String TAG = "PROFILE-ACTIVITY";
    SharedPreferences preferences;


    private String s3ImageKey = "";
    ActivityResultLauncher<Intent> activityResultLauncher;

    TextView userNameTextView;
    ImageView profileImageView;
    TextView nameTextView;
    TextView userNameTextViewView;
    TextView addressViewText;
    TextView zipcodeViewText;
    Button donateButton;
    Button requestButton;
    ImageButton updateProfileButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activityResultLauncher = getImagePickActivityResultLauncher();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        userNameTextView = findViewById(R.id.usernameTextView);
        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameViewText);
        userNameTextViewView = findViewById(R.id.usernameViewTextText);
        addressViewText = findViewById(R.id.addressViewText);
        zipcodeViewText = findViewById(R.id.zipcodeEditText);
        updateProfileButton = findViewById(R.id.updateProfileImageButton);
        donateButton = findViewById(R.id.FragmentDonateButton);
        requestButton = findViewById(R.id.FragmentRequestButton);
        updateProfileButton = findViewById(R.id.updateProfileImageButton);

        fetchLoggedInUser();
        setupDonateButton();
        setupRequestDonationButton();
        setupProfileImageView();
        setupUpdateImageButton();



    }

    void fetchLoggedInUser(){
        Auth.fetchUserAttributes(
                success ->{

                    for(AuthUserAttribute userAttribute: success){
                        String key = userAttribute.getKey().getKeyString();
                        String value = userAttribute.getValue();
                        if(key.equals("username")){
                            userNameTextView.setText(value);
                        }
                        if(key.equals("email")){
                            addressViewText.setText(value);
                        }
                        if(key.equals("name")){
                            nameTextView.setText(value);
                        }
                        if(key.equals("custom:s3ProfileImageKey")){
                            s3ImageKey = value.toString();
                            if (s3ImageKey != null){
                                int cut = s3ImageKey.lastIndexOf('/');
                                if (cut != -1){
                                    s3ImageKey = s3ImageKey.substring(cut+1);
                                    Log.i("S3ImageString", s3ImageKey);
                                }
                            }
                            Amplify.Storage.downloadFile(
                                    s3ImageKey,
                                    new File(getApplication().getFilesDir(), s3ImageKey),
                                    successful -> {

                                        profileImageView.setImageBitmap(BitmapFactory.decodeFile(successful.getFile().getPath()));
                                        Log.i(TAG, "able to set image from s3");
                                    },
                                    failure -> {
                                        Log.i(TAG, " Unable to get s3 Image for s3 key:"+ s3ImageKey+ " reason: "+ failure.getStackTrace());

                                    }
                            );

                        }
                    }
                },
                error -> Log.e(TAG, "Error getting user attributes ")
        );
    }

    void setupDonateButton(){
        donateButton.setOnClickListener(v -> {
            Intent goToDonatePageIntent = new Intent(ProfileActivity.this, DonateFoodActivity.class);
            startActivity(goToDonatePageIntent);
        });
    }

    void setupRequestDonationButton(){
        requestButton.setOnClickListener(v -> {
            Intent goToRequestPageIntent = new Intent(ProfileActivity.this, RequestActivity.class);
            startActivity(goToRequestPageIntent);
        });
    }

    void setupUpdateImageButton(){
        updateProfileButton.setOnClickListener(v ->{
            Intent goToUpdateProfileActivity = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
            startActivity(goToUpdateProfileActivity);
        });
    }

    void setupProfileImageView(){
        profileImageView.setOnClickListener(v -> {
            launchImageSelectionIntent();
        });
    }

    void launchImageSelectionIntent(){

        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT); //one of several picking actions in android
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});

        activityResultLauncher.launch(imageFilePickingIntent);
    }

    ActivityResultLauncher<Intent> getImagePickActivityResultLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            // Part 2: Android calls your callback with the picked image
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                Uri pickedImageFileUri = result.getData().getData();
                                try {
                                    InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                                    String pickedImageFilename = getFileNameFromUri(pickedImageFileUri); // nicer way of extracting filename from a Uri
                                    Log.i(TAG, "Succeeded in getting input stream from file on phone! Filename is: " + pickedImageFilename);
                                    uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
                                } catch (FileNotFoundException e) {
                                    Log.e(TAG, "Could not get file form file picker! " + e.getMessage(), e);
                                }
                            }
                        }
                );

        return imagePickingActivityResultLauncher;
    }

    void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFilename, Uri pickedImageFileUri) {


        Amplify.Storage.uploadInputStream(
                pickedImageFilename, // S3 key
                pickedImageInputStream,
                success -> {

                    Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
                    s3ImageKey = success.getKey(); // non-empty s3ImageKey indicates an image was picked in this activity
                    // TODO: update our saveProduct to include the s3 key
                    updateLocalUserProfileImage(s3ImageKey);

                    InputStream pickedImageInputStreamCopy = null; // need to make a copy because InputStreams cannot be reused!
                    try {
                        pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                    } catch (FileNotFoundException e) {
                        Log.e(TAG, "Could not get file stream from URI! " + e.getMessage(), e);
                    }

                    profileImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                },
                failure -> {
                    Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFilename + " with error: " + failure.getMessage());
                }
        );
    }


    // Taken from https://stackoverflow.com/a/25005243/16889809
    @SuppressLint("Range")
    public String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    void updateLocalUserProfileImage(String s3ImageGeneratedKey){

        AuthUserAttribute userProfileImage =
                new AuthUserAttribute(AuthUserAttributeKey.custom("custom:s3ProfileImageKey"), s3ImageGeneratedKey);
        Amplify.Auth.updateUserAttribute(userProfileImage,
                result -> Log.i("AuthDemo", "Updated user attribute = " + result.toString()),
                error -> Log.e("AuthDemo", "Failed to update user attribute.", error)
        );
    }



}












//package com.jennisung.weshare.Activities;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.os.Bundle;
//
//        import com.jennisung.weshare.R;
//
//public class ProfileActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//    }
//}