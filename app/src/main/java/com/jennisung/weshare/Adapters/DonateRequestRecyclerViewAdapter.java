package com.jennisung.weshare.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.jennisung.weshare.Activities.DonateFoodActivity;
import com.jennisung.weshare.Activities.InformationActivity;
import com.jennisung.weshare.Activities.RequestActivity;
import com.jennisung.weshare.MainActivity;
import com.jennisung.weshare.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


import java.util.List;

//TODO:step 1-4: Make a custom recyclerview adapter that extends
public class DonateRequestRecyclerViewAdapter extends RecyclerView.Adapter<DonateRequestRecyclerViewAdapter.requestDonateViewHolder>  {

    private final String TAG = "Recycler";



    // TODO step 2-3: create product list vairable and constructor within adapter
    public List<AssistanceRequest> assistanceRequest;
    public Context context;

    public String loggedInUserId;
    public String requestorUserId;


    //TODO : step 3-2cont: create a context variable and update constructor
    Context callingActivity;
    public DonateRequestRecyclerViewAdapter(List<AssistanceRequest> assistanceRequest, Context callingActivity) {
        this.assistanceRequest = assistanceRequest;
        this.callingActivity = callingActivity;
    }

    public DonateRequestRecyclerViewAdapter(List<AssistanceRequest> assistanceRequest) {

    }


    @NonNull
    @Override
    public requestDonateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        TODO: step 1-7: inflating the fragment(add the fragment to the viewholder)
        View requestDonateFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_donate_request, parent, false);
//          TODO STEP 1-9 attach fragment to the viewholder
        return new requestDonateViewHolder(requestDonateFragment);
    }


    @Override
    public void onBindViewHolder(@NonNull requestDonateViewHolder holder, int position) {

        TextView requestDonateFragmentTextView = holder.itemView.findViewById(R.id.requestFragmentTextView);
        Button donateButton = holder.itemView.findViewById(R.id.FragmentDonateButton);
        Button requestButton = holder.itemView.findViewById(R.id.FragmentRequestButton);
//        Button informationButton = holder.itemView.findViewById(R.id.InformationActivityMainButton);



        String dateString = formatDateString(assistanceRequest.get(position));

        StringBuilder requestFragmentText = new StringBuilder();
        requestFragmentText.append(position + 1).append(". ").append(assistanceRequest.get(position).getTitle()).append("\n");
        requestFragmentText.append("Organization: ").append(assistanceRequest.get(position).getIsForOrganization() ? "Yes" : "No").append("\n");
        requestFragmentText.append("Willing to Meeting: ").append(assistanceRequest.get(position).getIsWillingToMeet() ? "Yes" : "No").append("\n");
        requestFragmentText.append("Date: ").append(dateString);

        requestDonateFragmentTextView.setText(requestFragmentText.toString());

        donateButton.setOnClickListener(v -> {
            Intent donateIntent = new Intent(callingActivity, DonateFoodActivity.class);
            donateIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getId());
            callingActivity.startActivity(donateIntent);
        });

//        informationButton.setOnClickListener(v -> {
//            Intent informationIntent = new Intent(callingActivity, InformationActivity.class);
////            informationIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getTitle());
//            callingActivity.startActivity(informationIntent);
//        });

        requestButton.setOnClickListener(v -> {
            Intent requestIntent = new Intent(callingActivity, InformationActivity.class);
            requestIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getTitle());
            callingActivity.startActivity(requestIntent);
        });


//        holder.deleteButton.setOnClickListener(view -> {
//            AssistanceRequest toDelete = assistanceRequest.get(position);
//            // Delete from backend
//            Amplify.API.mutate(
//                    ModelMutation.delete(toDelete),
//                    response -> {
//                        Log.i("MyAmplifyApp", "Deleted Item: " + toDelete.getId());
//                        // Remove from local list and notify adapter
//                        assistanceRequest.remove(position);
//                        notifyItemRemoved(position);
//                    },
//                    error -> Log.e("MyAmplifyApp", "Delete failed", error)
//            );

        holder.deleteButton.setOnClickListener(view -> {

            AssistanceRequest toDelete = assistanceRequest.get(position);


                // Delete from backend
                Amplify.API.mutate(
                        ModelMutation.delete(toDelete),
                        response -> {
                            Log.i("MyAmplifyApp", "Deleted Item: " + toDelete.getId());
                            // Remove from local list and notify adapter
                            assistanceRequest.remove(position);
                            notifyItemRemoved(position);

                            // Navigate to MainActivity
                            Intent intent = new Intent(callingActivity, MainActivity.class);
                            callingActivity.startActivity(intent);
                        },
                        error -> Log.e("MyAmplifyApp", "Delete failed", error)
                );




        });
    }








    @Override
    public int getItemCount() {
        //TODO step 2-5 make size of the list dynamic based on size of product list
        return assistanceRequest.size();
//        return 5;
    }


//    public static class requestDonateViewHolder extends RecyclerView.ViewHolder {
//
//        //TODO: step 1-8 make a viewholder class to hold our fragmenets
//        public requestDonateViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }

    public static class requestDonateViewHolder extends RecyclerView.ViewHolder {
        ImageButton deleteButton;

        public requestDonateViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteButton = itemView.findViewById(R.id.FragmentDeleteButton); // Find the delete button
        }
    }



    private String formatDateString(AssistanceRequest assistanceRequest) {
        DateFormat dateCreatedIso8601InputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        dateCreatedIso8601InputFormat.setTimeZone(TimeZone.getTimeZone(("America/New_York")));
        DateFormat dateCreatedOutputFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        dateCreatedOutputFormat.setTimeZone(TimeZone.getDefault());
        String dateCreateString = "";

        try {
            {
                Date dateCreateJavaDate = dateCreatedIso8601InputFormat.parse(assistanceRequest.getCreatedAt().format());
                if(dateCreateJavaDate != null) {
                    dateCreateString = dateCreatedOutputFormat.format(dateCreateJavaDate);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateCreateString;
    }


}



//TODO step 1-8 make a viewholder class to hold our fragment(nested within product list recycler handler)
//    public static class requestDonateViewHolder extends RecyclerView.ViewHolder {
//        public requestDonateViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }



//    @Override
//    public void onBindViewHolder(@NonNull requestDonateViewHolder holder, int position) {
//        // TODO: Step 2-4: Bind data items to Fragments inside of ViewHolders
//        TextView requestDonateFragmentTextView = (TextView) holder.itemView.findViewById(R.id.requestDonateFragmentTextView);
//        String dateString = formatDateString(assistanceRequest.get(position));
//        String requestFragmentText = (position + 1) + ". " + assistanceRequest.get(position).getTitle()
//                + "\n" + dateString;
//        requestDonateFragmentTextView.setText(requestFragmentText);
//
//        // TODO: Step 3-3: Create an onClickListener, make an intent inside of it, and call this intent with an extra to go to a new activity
//        View requestViewHolder = holder.itemView;
//        requestViewHolder.setOnClickListener(v -> {
//            Intent goToTaskInfoIntent = new Intent(callingActivity, RequestActivity.class);
//
//            goToTaskInfoIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getTitle());
//
//            callingActivity.startActivity(goToTaskInfoIntent);
//        });
//
//    }


//@Override
//public void onBindViewHolder(@NonNull requestDonateViewHolder holder, int position) {
//    TextView requestDonateFragmentTextView = holder.itemView.findViewById(R.id.requestFragmentTextView);
//    Button donateButton = holder.itemView.findViewById(R.id.FragmentDonateButton);
//    Button requestButton = holder.itemView.findViewById(R.id.FragmentRequestButton);
//
//    String dateString = formatDateString(assistanceRequest.get(position));
//    String requestFragmentText = (position + 1) + ". " + assistanceRequest.get(position).getTitle() + "\n" + dateString;
//
//    requestDonateFragmentTextView.setText(requestFragmentText);
//
//    donateButton.setOnClickListener(v -> {
//        Intent donateIntent = new Intent(callingActivity, DonateFoodActivity.class);
//        donateIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getTitle());
//        callingActivity.startActivity(donateIntent);
//    });
//
//    requestButton.setOnClickListener(v -> {
//        Intent requestIntent = new Intent(callingActivity, RequestActivity.class);
//        requestIntent.putExtra(MainActivity.REQUEST_NAME_EXTRA_TAG, assistanceRequest.get(position).getTitle());
//        callingActivity.startActivity(requestIntent);
//    });
//
//}