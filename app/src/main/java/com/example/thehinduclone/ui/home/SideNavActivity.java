package com.example.thehinduclone.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thehinduclone.NotificationActivity;
import com.example.thehinduclone.R;
import com.example.thehinduclone.ReadLaterActivity;
import com.example.thehinduclone.SearchBarActivity;
import com.example.thehinduclone.SettingActivity;
import com.example.thehinduclone.ui.menu.ElectionFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class SideNavActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView mIvSearch;
    private LinearLayout linearLayoutBriefing;
    private LinearLayout linearLayoutTrending;
    private LinearLayout linearLayoutPremium;
    private LinearLayout linearLayoutMyAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_side_nav);

        mIvSearch = findViewById(R.id.search);
        linearLayoutBriefing = findViewById(R.id.llLayoutBriefing);
        linearLayoutTrending = findViewById(R.id.llLayoutTrending);
        linearLayoutPremium = findViewById(R.id.llLayoutPremium);
        linearLayoutMyAccount = findViewById(R.id.llLayoutMyAccount);

        openActivity();


        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideNavActivity.this, SearchBarActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_election, R.id.nav_covid, R.id.nationalFragment, R.id.internationalFragment,
                R.id.statesFragment, R.id.businessFragment, R.id.editorialsFragment, R.id.sportsFragment, R.id.educationFragment,
                R.id.entertainmentFragment, R.id.scienceFragment, R.id.technologyFragment, R.id.multimediaFragment, R.id.lifeStyleFragment,
                R.id.citiesFragment, R.id.booksFragment, R.id.newsInQuotesFragment, R.id.wellbeingFragment, R.id.thReadFragment,
                R.id.topPicksFragment, R.id.specialsFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setViewPagerAdapter();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_read_later:
                Intent read_later = new Intent(this, ReadLaterActivity.class);
                startActivity(read_later);
                break;
            case R.id.action_notification:
                Intent notification = new Intent(this, NotificationActivity.class);
                startActivity(notification);
                break;
            case R.id.action_personalise:
                break;
            case R.id.action_settings:
                Intent settings = new Intent(this, SettingActivity.class);
                startActivity(settings);
                break;
            case R.id.action_share:
               try {
                   Intent intent = new Intent(Intent.ACTION_SEND);
                   intent.setType("text/plain");
                   intent.putExtra(Intent.EXTRA_SUBJECT,"The Hindu");
                   intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                   startActivity(Intent.createChooser(intent,"Share With"));
               }
               catch (Exception e){
                   Toast.makeText(this,"Unable to share this application",Toast.LENGTH_SHORT).show();
               }
               break;
        }
      return true;
    }

    private void setViewPagerAdapter() {
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_election:
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                ElectionFragment electionFragment = new ElectionFragment();
//                fragmentTransaction.add(R.id.lLayoutParent,electionFragment,"ElectionFragment").commit();


        }
        return super.onContextItemSelected(item);
    }

    private void openActivity() {


    }

}