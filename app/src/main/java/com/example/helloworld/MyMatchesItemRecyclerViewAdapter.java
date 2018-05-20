package com.example.helloworld;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        holder.stringImageUrl = mValues.get(position).imageUrl;

        Picasso.get().load(holder.stringImageUrl).into(holder.mImageUrl); // set image url into ImageView
        holder.mFav = mValues.get(position).favorite;



        holder.mFavorite.setOnClickListener(v -> {
            if (null != mListener) {


                Toast.makeText(holder.mView.getContext(), "You liked " + mValues.get(position).name, Toast.LENGTH_LONG).show();

                mListener.onListFragmentInteraction(holder.mItem);

        }});
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public MatchItem mItem;

        public ImageView mImageUrl;
        public ImageButton mFavorite;
        public TextView mName;

        public String stringImageUrl;
        public boolean mFav;




        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.card_title);
            mImageUrl = view.findViewById(R.id.card_image);
            mFavorite = view.findViewById(R.id.favorite_button);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}