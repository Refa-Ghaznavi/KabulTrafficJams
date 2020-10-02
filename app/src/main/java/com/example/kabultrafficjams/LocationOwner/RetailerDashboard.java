package com.example.kabultrafficjams.LocationOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.kabultrafficjams.Common.LoginSignup.Login;
import com.example.kabultrafficjams.Common.LoginSignup.RetailerStartUpScreen;
import com.example.kabultrafficjams.Databases.SessionManager;
import com.example.kabultrafficjams.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.HashMap;

public class RetailerDashboard extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_dashboard);

        // session manager
        /*TextView textView = findViewById(R.id.textView);

        SessionManager sessionManager = new SessionManager(RetailerDashboard.this, SessionManager.SESSION_USERSESSION);

        HashMap<String, String> userDetails = sessionManager.getUsersDetailFromSession();

        String fullName = userDetails.get(SessionManager.KEY_FULLNAME);
        String phoneNumber = userDetails.get(SessionManager.KEY_PHONENUMBER);

        textView.setText(fullName + "\n" + phoneNumber);*/

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RetailerDashboardFragment()).commit();
        bottomMenu();

    }

    private void bottomMenu() {


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_nav_dashboard:
                        fragment = new RetailerDashboardFragment();
                        break;
                    case R.id.bottom_nav_manage:
                        fragment = new RetailerManagerFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new RetailerProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });

    }

    /*public void logoutTheUserFromSession(View view){
        sessionManager.logoutUserFromSession();
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
        finish();
    }*/

}