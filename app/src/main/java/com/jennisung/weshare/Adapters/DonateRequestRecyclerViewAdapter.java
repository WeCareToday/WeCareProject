package com.jennisung.weshare.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.AssistanceRequest;
import com.jennisung.weshare.R;

//TODO:step 1-4: Make a custom recyclerview adapter that extends
public class DonateRequestRecyclerViewAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        TODO: step 1-7: inflating the fragment(add the fragment to the viewholder)
        View requestDonateFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_donate_request, parent, false);
//          TODO STEP 1-9 attach fragment to the viewholder
        return new requestDonateViewHolder(requestDonateFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        return AssistanceRequest.size();
        return 5;
    }


    public static class requestDonateViewHolder extends RecyclerView.ViewHolder {

        //TODO: step 1-8 make a viewholder class to hold our fragmenets
        public requestDonateViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
