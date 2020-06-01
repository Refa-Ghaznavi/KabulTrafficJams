package com.example.kabultrafficjams.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kabultrafficjams.Common.LoginSignup.RetailerStartUpScreen;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.kabultrafficjams.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.kabultrafficjams.R;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    ImageView menuIcon;

    LinearLayout contentView;

    // Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        // Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);

        // Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);


        navigationDrawer();


        //Functions will be executed automatically when this activity will be created
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }

    //Navigation Drawer Functions
    private void navigationDrawer() {

        // Navigation Drawer
        // this is what that we want to interact with that navigation drawer
        navigationView.bringToFront();
        // because we click those items so we have to set navigation item listener
        navigationView.setNavigationItemSelectedListener(this);
        // the home item must be check able by default
        navigationView.setCheckedItem(R.id.nav_home);

        // this code will visible the navigation drawer when it clicked
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    // this code just bring an animation to the navigation drawer to main activity and from main activity to navigation drawer
    private void animateNavigationDrawer() {
        // this line code just put the color az opacity during animation of navigation drawer
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the view, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    // in here when the back button is clicked instead of close the application go back the main activity and if then clicked close the application
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    // this function is the navigation item selected that it should return true that means there is an item which should be selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // when the user click the all categories item
        // it goes to the activity all categories
        switch (item.getItemId()) {
            case R.id.nav_all_categories:
               /* Intent intent = new Intent(getApplicationContext(),AllCategories.class);
                startActivity(intent;);*/
                // shortest way
                // start activity  new intent then the calling activity
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
        }
        return true;
    }

    //Recycler Views Functions
    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.school_icon, "Education", gradient1));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.hospital, "HOSPITAL", gradient2));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.restaurant_icon, "Restaurant", gradient3));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.shop_icon, "Shopping", gradient4));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.ic_directions_bus_black_24dp, "Transport", gradient1));

        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.splash_screen_background, "McDonald's"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.splash_screen_background, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.splash_screen_background, "J."));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.splash_screen_background, "Walmart"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {

        // the recycler view just load those views which are visible to the users
        featuredRecycler.setHasFixedSize(true);
        // in here we set our layout's orientation that should be HORIZONTAL
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // so in here we have to pass the array to our design or to our adapter
        // so first we have to create an array
        // and it is type is FeaturedHelperClass

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        // so we have created a simple array list

        // so in here we have add values inside in this featuredLocation which is the variable of ArrayList
        // and then inside that each time we have to create a new constructor of the HelperClass which is FeaturedHelperClass
        // and inside we have to pass the images titles and descriptions
        featuredLocations.add(new FeaturedHelperClass(R.drawable.splash_screen_background, "Mcdonald's", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.splash_screen_background, "Edenrobe", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.splash_screen_background, "Walmart", "asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));

        // so now we have to our array into our adapter class
        // so we need the global variable at top
        // and inside that FeaturedAdapter constructor we have to the featuredLocation
        adapter = new FeaturedAdapter(featuredLocations);
        // so we have created our adapter

        // so simply set this featured recycler in the setAdapter
        featuredRecycler.setAdapter(adapter);


    }

    // Normal Functions
    public void callRetailerScreens(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }

}
