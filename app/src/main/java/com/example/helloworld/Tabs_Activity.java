package com.example.helloworld;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.helloworld.models.MatchItem;
import com.example.helloworld.viewmodels.FirebaseMatchViewModel;

import java.util.ArrayList;
import java.util.List;

public class Tabs_Activity extends AppCompatActivity  implements MatchesItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "Tabs_Activity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private FirebaseMatchViewModel viewModel;
    private EditText newMatchItemText;
    private FrameLayout frameLayout;
    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // it will get the page to a tab layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_activity);
        //Log.d(TAG, "onCreate: Starting");

        // Adding Toolbar to Main screen
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new FirebaseMatchViewModel();
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        //initialize the database
        //appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "User").build();
    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new MatchesItemFragment(), "Matches");
        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onListFragmentInteraction(MatchItem item) {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}