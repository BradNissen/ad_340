package com.example.helloworld;


import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.models.MatchItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MatchItem} and makes a call to the
 * specified {@link MatchesItemFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class MyMatchesItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMatchesItemRecyclerViewAdapter.ViewHolder> {


    private final List<MatchItem> mValues;
    private final MatchesItemFragment.OnListFragmentInteractionListener mListener;
    public String gps_lat;
    public String gps_lng;
    public int search_distance;
    public View view;



    public MyMatchesItemRecyclerViewAdapter(List<MatchItem> items, MatchesItemFragment.OnListFragmentInteractionListener listener,String gps_lat, String gps_lng, int searchDistance) {
        mValues = items;
        mListener = listener;
        this.gps_lat = gps_lat;
        this.gps_lng = gps_lng;
        this.search_distance = searchDistance;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matches_fragment, parent, false);



        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mName.setText(mValues.get(position).name); // get name
        holder.stringImageUrl = mValues.get(position).imageUrl; //get image url
        holder.mFav = mValues.get(position).liked; // get the boolean

        String match_lat_str = mValues.get(position).lat;
        String match_lng_str = mValues.get(position).longitude;

//        holder.tv_lat.setText(match_lat_str);
//        holder.tv_longitude.setText(match_lng_str);
//        holder.tv_gps_lat.setText(gps_lat);
//        holder.tv_gps_lng.setText(gps_lng);

        double distance = geoToDistanceInMiles(match_lat_str,match_lng_str,gps_lat,gps_lng);
        Log.v("Distance..", String.valueOf(distance));

        //String distance_string = String.valueOf(distance);

        //String formatted = String.format("%.2f",distance);

        //holder.tv_distance.setText(formatted);

        Picasso.get().load(holder.stringImageUrl).into(holder.mImageUrl); // set image url into ImageView


        /** pulled from firebase.google.com and it's true it will change to RED */
        if (!holder.mFav){
            holder.likedButton.setColorFilter(Color.GRAY);
        } else {
            holder.likedButton.setColorFilter(Color.RED);
        }

        /** Will take the queried result from the room database and input it into the distance */
        if (distance > search_distance) {

//            holder.whole_card.setVisibility(View.GONE);
            holder.mImageUrl.setColorFilter(Color.WHITE);
            holder.mName.setTextColor(Color.WHITE);
            holder.likedButton.setColorFilter(Color.WHITE);

        }



        holder.likedButton.setOnClickListener(v -> {
            if (null != mListener) {

                // If its clicked and its true.
                if(!holder.mFav){
                    holder.likedButton.setColorFilter(Color.RED);
                    holder.mFav = !holder.mFav;
                    Toast.makeText(holder.mView.getContext(), "You liked " + mValues.get(position).name, Toast.LENGTH_SHORT).show();
                } else{
                    holder.likedButton.setColorFilter(Color.GRAY);
                    holder.mFav = !holder.mFav;
                    Toast.makeText(holder.mView.getContext(), "You unliked " + mValues.get(position).name, Toast.LENGTH_SHORT).show();
                }

                mListener.onListFragmentInteraction(holder.mItem);


            }});
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public double geoToDistanceInMiles(String lat, String lng, String gps_lat, String gps_lng){

        double match_lat = Double.valueOf(lat);
        double match_lng = Double.valueOf(lng);
        double current_lat = Double.valueOf(gps_lat);
        double current_lng = Double.valueOf(gps_lng);


        Location match_location = new Location("");
        match_location.setLatitude(match_lat);
        match_location.setLongitude(match_lng);

        Location current_location = new Location("");
        current_location.setLatitude(current_lat);
        current_location.setLongitude(current_lng);
        double dist = match_location.distanceTo(current_location);

        return dist * 0.000621371192;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public MatchItem mItem;
        public RelativeLayout whole_card;

        public ImageView mImageUrl;
        public ImageButton likedButton;
        public TextView mName;
//        public TextView tv_lat;
//        public TextView tv_longitude;
//        public TextView tv_gps_lat;
//        public TextView tv_gps_lng;
//        public TextView tv_distance;

        public String stringImageUrl;
        public boolean mFav;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.card_title);
            mImageUrl = view.findViewById(R.id.card_image);
            likedButton = view.findViewById(R.id.favorite_button);
//            tv_lat = view.findViewById(R.id.card_lat);
//            tv_longitude = view.findViewById(R.id.card_long);
//            tv_gps_lat = view.findViewById(R.id.gps_lat);
//            tv_gps_lng = view.findViewById(R.id.gps_long);
//            tv_distance = view.findViewById(R.id.distance_tv);
            whole_card = view.findViewById(R.id.whole_card); // <-- card to disappear?
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}