
//package com.example.helloworld;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.helloworld.models.MatchItem;
//
///**
// * Provides UI for the view with Cards.
// */
//public class MatchesFragment extends Fragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
//        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        return recyclerView;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView picture;
//        public TextView name;
//        public TextView description;
//        ImageButton favButton;
//        //TextView favDisplay;
//        Boolean like = false;
//
//        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
//
//            super(inflater.inflate(R.layout.matches_fragment, parent, false));
//
//            picture =  itemView.findViewById(R.id.card_image);
//            name = itemView.findViewById(R.id.card_title);
//            description = itemView.findViewById(R.id.card_text);
//            favButton = itemView.findViewById(R.id.favorite_button);
//            //favDisplay = itemView.findViewById(R.id.favorite_display);
//
//            //set the click listener for each button on the itemView
//            favButton.setOnClickListener(v -> {
//                if (!like) {
//                    //like = true;
//                    //Toast.makeText(getApplicationContext(), "Your toast message.", Toast.LENGTH_SHORT).show(); //display in long period of time
//                    Toast.makeText(itemView.getContext(), "You like " + name.getText().toString(), Toast.LENGTH_SHORT).show();
//                    //favDisplay.setText("Liked");
//                }
////                    } else {
////                        like = false;
////                        Toast.makeText(itemView.getContext(), "You don't like " + name.getText().toString(), Toast.LENGTH_SHORT).show();
////                        favDisplay.setText("");
////                        }
//            });
//        }
//    }
//    /**
//     * Adapter to display recycler view.
//     */
//    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
//        // Set numbers of List in RecyclerView.
//        private static final int LENGTH = 8;
//        private final String[] mPlaces;
//        private final String[] mPlaceDesc;
//        private final Drawable[] mPlacePictures;
//        public ContentAdapter(Context context) {
//            Resources resources = context.getResources();
//            mPlaces = resources.getStringArray(R.array.places);
//            mPlaceDesc = resources.getStringArray(R.array.place_desc);
//            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
//            mPlacePictures = new Drawable[a.length()];
//            for (int i = 0; i < mPlacePictures.length; i++) {
//                mPlacePictures[i] = a.getDrawable(i);
//            }
//            a.recycle();
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
//            holder.name.setText(mPlaces[position % mPlaces.length]);
//            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
//        }
//
//        @Override
//        public int getItemCount() {
//            return LENGTH;
//        }
//    }
//}
