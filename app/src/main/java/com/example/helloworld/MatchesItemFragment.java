package com.example.helloworld;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.entity.User;
import com.example.helloworld.models.MatchItem;
import com.example.helloworld.viewmodels.FirebaseMatchViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MatchesItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "data-set";

    // TODO: Customize parameters
    private int mColumnCount;
    private List<MatchItem> mDataSet;
    private OnListFragmentInteractionListener mListener;
    private MyMatchesItemRecyclerViewAdapter adapter;
    private RecyclerView view;
    private LocationManager locationManager;
    public double longitudeNetwork;
    public double latitudeNetwork;
    public int searchDistance;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MatchesItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MatchesItemFragment newInstance(int columnCount) {
        MatchesItemFragment fragment = new MatchesItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);


        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);




        /** From the boot up this wont populate because its not populated, so here is my work around.
         * catch the exception and set a value for it. it will display all the users within that mileage */
        List<User> users = Tabs_Activity.appDatabase.userDao().getAll();

        try {
            searchDistance = users.get(0).getSearchDistance();
        }catch (IndexOutOfBoundsException ex) {
            searchDistance = 10;
        }
        //Log.v("Users(0)", String.valueOf(users));



        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            FirebaseMatchViewModel viewModel = new FirebaseMatchViewModel();

            //this will pass in my lambda function and it will return a function.
            viewModel.getMatchItems((ArrayList<MatchItem> matches) -> {


                toggleNetworkUpdates(view);
                if (ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                    latitudeNetwork = loc.getLatitude();
                    longitudeNetwork = loc.getLongitude();
                    Log.v("LAT_LNG","LAT="+String.valueOf(latitudeNetwork) + " LNG=" + String.valueOf(longitudeNetwork));
                }


                MyMatchesItemRecyclerViewAdapter adapter = new MyMatchesItemRecyclerViewAdapter(matches, mListener, String.valueOf(latitudeNetwork), String.valueOf(longitudeNetwork), searchDistance);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(MatchItem item);
    }




    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

    public void toggleNetworkUpdates(View view) {
        if(!checkLocation()) {
            return;
        }

        else {
            if (ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerNetwork);

            }
        }
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {

            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();
            Log.v("LAT_LONGS", "LAT=" + String.valueOf(latitudeNetwork) + " LONG=" + String.valueOf(longitudeNetwork));

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };
}