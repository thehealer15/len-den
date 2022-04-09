package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import Screens.authentication.WelcomeActivity;
import Screens.feed_fragment;
import Screens.fragment_dashboard;
import Screens.fragment_profile;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{
    private FrameLayout fragment_container;
    private BottomNavigationView bottomNav;
    private ActionBar actionBar;
    public static boolean signedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_container = findViewById(R.id.fragment_container);
        bottomNav = findViewById(R.id.bottomNav);
        loadFragment(new Screens.feed_fragment());
        bottomNav.setOnNavigationItemSelectedListener(this);
        FirebaseAuth m = FirebaseAuth.getInstance();
        FirebaseUser u = m.getCurrentUser();
        if(u == null || !signedIn){
            Toast.makeText(getApplicationContext(), "Not sign in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        }

        bottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) { } });

        Intent authen = getIntent();
        String path = authen.getStringExtra("path");
        if(Objects.equals(path, "MainActivity")){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Authentication successful", Snackbar.LENGTH_LONG);
            snackbar.show();
            loadFragment(new feed_fragment());
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.home_btn:
                fragment = new Screens.feed_fragment();
                break;

            case R.id.dashboard_btn:
                fragment = new Screens.fragment_dashboard();
                break;

            case R.id.profile_btn:
                fragment = new Screens.fragment_profile();
                break;
            default:
                fragment = new Screens.feed_fragment();
                break;
        }

        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.switch_mode, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.light_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;

            case R.id.dark_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;

            case R.id.profile_toolbar:
                loadFragment(new fragment_profile());
                bottomNav.setSelectedItemId(R.id.profile_btn);return true;
                default:
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                return true;
        }
    }
    private void loadFragment(int fragmentNumber){
        Fragment temp;
        switch (fragmentNumber){
            case 1: temp = new feed_fragment(); break;
            case 2: temp = new fragment_dashboard() ; break;
            default: temp = new fragment_profile();break;
        }
        loadFragment(temp);
    }

}


/**
 * OnTochClickListner code dump
 *
 fragment_container.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
@Override
public void onSwipeLeft() {
loadFragment((CurrentFragment + 1) %3);
}
@Override
public void onSwipeRight(){
loadFragment((CurrentFragment - 1) % 3);
}
});
 * */