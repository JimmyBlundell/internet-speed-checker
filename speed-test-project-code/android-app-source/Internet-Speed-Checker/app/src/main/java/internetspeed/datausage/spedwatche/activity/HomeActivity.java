package internetspeed.datausage.spedwatche.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import internetspeed.datausage.spedwatche.R;
import internetspeed.datausage.spedwatche.fragment.GraphFragment;
import internetspeed.datausage.spedwatche.fragment.MonthFragment;
import internetspeed.datausage.spedwatche.service.DataService;
import internetspeed.datausage.spedwatche.utils.StoredData;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    InterstitialAd interstitialAd = null;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        String packageName = getPackageName();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
          Intent intent = new Intent();
          intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
          intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
          intent.setData(Uri.parse("package:" + packageName));
          startActivity(intent);
        }
      }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(!StoredData.isSetData) {
            StoredData.setZero();
        }
        if (!DataService.service_status) {
            Intent intent = new Intent(this, DataService.class);
            startService(intent);
        }
        Intent intentBC = new Intent();
        intentBC.setAction("com.tofabd.internetmeter");
        sendBroadcast(intentBC);
        Fragment fragment = new MonthFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen_1));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }
            });
        }else
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.month) {
            fragment = new MonthFragment();
        }

        else if (id == R.id.graph) {
            fragment = new GraphFragment();
        }
        else if (id == R.id.speedtest) {
            startActivity(new Intent(HomeActivity.this, SpeedTestActivity.class));

        }
        else if (id == R.id.speedtestresult) {
            try {
                startActivity(new Intent(HomeActivity.this, SpeedTestResult.class));
            }catch (Exception e){

            }

        }
        else if (id == R.id.settings) {
            startActivity(new Intent(HomeActivity.this, SettingsActivity.class));

        }


        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



}
