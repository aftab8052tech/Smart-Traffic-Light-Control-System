package com.mastercoding.trafficlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.Navigation_drawer_open , R.string.Navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        fragmentManager = getSupportFragmentManager();
        openFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.bottom_home){
                    openFragment(new HomeFragment());
                    return  true;
                }
                 else if(itemId == R.id.bottom_setting){
                    openFragment(new SettingFragment());
                    return true;
                }
                else if(itemId == R.id.bottom_map){
                    openFragment(new FeedbackFragment());
                    return true;
                }
                else if(itemId == R.id.bottom_rule){
                    openFragment(new RuleFragment());
                    return true;
                }

                return false;
            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.nav_feedback){
            openFragment(new MapFragment());
        }
        else if(itemId == R.id.nav_pedestrian){
            openFragment(new PedestrianFragment());
        }
        else if(itemId == R.id.nav_maintainance){
            openFragment(new MaintainanceFragment());
        }
        else if(itemId == R.id.nav_help){
            openFragment(new HelpFragment());
        }
        else if(itemId == R.id.nav_share){
            openFragment(new ShareFragment());
        }
        else if(itemId == R.id.nav_about){
            openFragment(new AboutFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    private void openFragment(Fragment fragment){
        FragmentTransaction  transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}