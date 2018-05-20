package com.example.helloworld;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.models.MatchItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MatchItem} and makes a call to the
 * specified {@link MatchesItemFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMatchesItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMatchesItemRecyclerViewAdapter.ViewHolder> {

    private final List<MatchItem> mValues;
    private final MatchesItemFragment.OnListFragmentInteractionListener mListener;

    public MyMatchesItemRecyclerViewAdapter(List<MatchItem> items, MatchesItemFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matches_fragment, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mName.setText(mValues.get(position).name);
        holder.sImageUrl = mValues.get(position).imageUrl;

        holder.sLike = mValues.get(position).liked;


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public MatchItem mItem;

        public ImageView mImageUrl;
        public ImageButton mLike;
        public TextView mName;

        public String sImageUrl;
        public Boolean sLike;
        public String sName;




        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageUrl = view.findViewById(R.id.card_image);
            mLike = view.findViewById(R.id.favorite_button);
            mName = view.findViewById(R.id.card_title);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}