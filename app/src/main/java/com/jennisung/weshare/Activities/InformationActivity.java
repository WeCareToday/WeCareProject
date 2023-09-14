//package com.jennisung.weshare.Activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//
//import com.jennisung.weshare.MainActivity;
//import com.jennisung.weshare.R;
//
//public class InformationActivity extends AppCompatActivity {
//    public static String TAG = "Information-Activity";
//
//
//    Button mainButton;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_information);
//
//
//        mainButton = findViewById(R.id.InformationActivityMainButton);
//
//
//        setupGoBackButton();
//    }
//
//    void setupGoBackButton() {
//        mainButton.setOnClickListener(v -> {
//            Intent goToDonatePageIntent = new Intent(InformationActivity.this, MainActivity.class);
//            startActivity(goToDonatePageIntent);
//        });
//    }
//
//
//}

package com.jennisung.weshare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.jennisung.weshare.MainActivity;
import com.jennisung.weshare.R;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    public static String TAG = "Information-Activity";
    Button mainButton;
    TextView infoTextView;

    List<AssistanceRequest> assistanceRequest = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mainButton = findViewById(R.id.InformationActivityMainButton);
        setupGoBackButton();

        infoTextView = findViewById(R.id.infoTextView);

        String title = getIntent().getStringExtra(MainActivity.REQUEST_NAME_EXTRA_TAG);

        if (title != null) {
            fetchDataFromDatabase(title);
        }
    }

    void setupGoBackButton() {
        mainButton.setOnClickListener(v -> {
            Intent goToDonatePageIntent = new Intent(InformationActivity.this, MainActivity.class);
            startActivity(goToDonatePageIntent);
        });
    }

    private void fetchDataFromDatabase(String title) {
        Amplify.API.query(
                ModelQuery.list(AssistanceRequest.class, AssistanceRequest.TITLE.eq(title)),
                response -> {
                    for (AssistanceRequest request : response.getData()) {
                        assistanceRequest.add(request);
                    }
                    runOnUiThread(() -> {
                        if (!assistanceRequest.isEmpty()) {
                            AssistanceRequest request = assistanceRequest.get(0); // Assuming only one matching record

                            StringBuilder stringBuilder = new StringBuilder();
//                            stringBuilder.append("ID: ").append(request.getId()).append("\n");
                            stringBuilder.append("Title: ").append(request.getTitle()).append("\n");
                            stringBuilder.append("Description: ").append(request.getDescription()).append("\n");
                            stringBuilder.append("Is For Organization: ").append(request.getIsForOrganization()).append("\n");
                            stringBuilder.append("Is Willing To Meet: ").append(request.getIsWillingToMeet()).append("\n");
                            stringBuilder.append("Family Size: ").append(request.getFamilySize()).append("\n");
                            stringBuilder.append("Diet Restrictions: ").append(request.getDietRestrictions()).append("\n");
                            stringBuilder.append("Need Date: ").append(request.getNeedDate()).append("\n");
                            stringBuilder.append("Is Request Anonymous: ").append(request.getIsRequestAnonymous()).append("\n");
                            stringBuilder.append("Is Need Satisfied: ").append(request.getIsNeedSatisfied()).append("\n");
                            stringBuilder.append("Contact Email: ").append(request.getContactEmail()).append("\n");
                            stringBuilder.append("Zipcode: ").append(request.getZipcode()).append("\n");

                            infoTextView.setText(stringBuilder.toString());
                        }
                    });
                },
                failure -> Log.e(TAG, "Could not query DataStore: " + failure)
        );
    }

}

