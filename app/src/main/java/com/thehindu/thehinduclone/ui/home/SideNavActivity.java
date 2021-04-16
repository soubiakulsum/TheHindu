package com.thehindu.thehinduclone.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.thehindu.thehinduclone.NotificationActivity;
import com.thehindu.R;
import com.thehindu.thehinduclone.ReadLaterActivity;
import com.thehindu.thehinduclone.SettingActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.thehindu.themain.LocalConstants;
import com.thehindu.themain.models.tokenreqres.JwtRequest;
import com.thehindu.themain.services.ClientCalls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
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

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Dispatcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SideNavActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private GoogleSignInOptions gso;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkwithgoogle();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_side_nav);

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int p = 0;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        p = 0;
                        break;
                    case R.id.nav_covid:
                        p = 1;
                        break;
                    case R.id.nav_election:
                        p = 2;
                        break;
                    case R.id.nationalFragment:
                        p = 3;
                        break;
                    case R.id.internationalFragment:
                        p = 4;
                        break;
                    case R.id.statesFragment:
                        p = 5;
                        break;
                    case R.id.businessFragment:
                        p = 6;
                        break;
                    case R.id.editorialsFragment:
                        p = 7;
                        break;
                    case R.id.sportsFragment:
                        p = 8;
                        break;
                    case R.id.educationFragment:
                        p = 9;
                        break;
                    case R.id.entertainmentFragment:
                        p = 10;
                        break;
                    case R.id.scienceFragment:
                        p = 11;
                        break;
                    case R.id.technologyFragment:
                        p = 12;
                        break;
                    case R.id.multimediaFragment:
                        p = 13;
                        break;
                    case R.id.lifeStyleFragment:
                        p = 14;
                        break;
                    case R.id.citiesFragment:
                        p = 15;
                        break;
                    case R.id.booksFragment:
                        p = 16;
                        break;
                    case R.id.specialsFragment:
                        p = 22;
                        break;
                }
                viewPager.setCurrentItem(p);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ImageView ivHome = findViewById(R.id.ivHome);
        ImageView ivBriefing = findViewById(R.id.ivBriefing);
        ImageView ivTrending = findViewById(R.id.ivTrending);
        ImageView ivPremium = findViewById(R.id.ivPremium);
        ImageView ivMyAccount = findViewById(R.id.ivMyAccount);
        ivHome.setOnClickListener(this);
        ivBriefing.setOnClickListener(this);
        ivTrending.setOnClickListener(this);
        ivPremium.setOnClickListener(this);
        ivMyAccount.setOnClickListener(this);

    }

    private void checkwithgoogle() {
        gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

//        logout.setOnClickListener {
//            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
//                if (it.isSuccess) {
//                    gotoMainActivity()
//                    PreferenceHelper.writeBooleanToPreference(
//                            LocalConstants.PREF_TOKEN_BOOLEAN,
//                            false
//                    )
//                    PreferenceHelper.writeBooleanToPreference(LocalConstants.PREF_USER_LOGIN, false)
//                    PreferenceHelper.writeStringToPreference(LocalConstants.PREF_TOKEN_VALUE, "")
//                } else {
//                    Toast.makeText(this, "logout unsuccessful", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ClientCalls clientCalls = ClientCalls.Companion.getInstances();
        OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = (GoogleSignInResult) opr.get();
            clientCalls.handleSignInResult(result);
        } else {

            opr.setResultCallback(result -> {
                clientCalls.handleSignInResult((GoogleSignInResult) result);
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ivHome:
                intent = new Intent(this, SideNavActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ivBriefing:

                break;
            case R.id.ivTrending:

                break;
            case R.id.ivPremium:

                break;
            case R.id.ivMyAccount:

                break;
        }

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();
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
                    intent.putExtra(Intent.EXTRA_SUBJECT, "The Hindu");
                    intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(intent, "Share With"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share this application", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
}