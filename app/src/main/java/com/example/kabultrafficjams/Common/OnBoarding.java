package com.example.kabultrafficjams.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kabultrafficjams.HelperClasses.SliderAdapter;
import com.example.kabultrafficjams.R;
import com.example.kabultrafficjams.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;

    // so for creating dots we use text view of arrays
    TextView[] dots;

    Button letsGetStared;
    Button backBtn;
    Button nextBtn;

    Animation animation;

    int currentPos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // status bar will be gone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        letsGetStared = findViewById(R.id.get_started_btn);
        backBtn = findViewById(R.id.back_btn);
        nextBtn = findViewById(R.id.next_btn);

        // call the adapter so in here we want ot create as reference sliderAdapter
        // so we have created the constructor and in here we have to pass this means in this activity which is on boarding dot this
        sliderAdapter = new SliderAdapter(this);

        // in here we have to set view pager setAdapter and out adapter will be slider adapter
        viewPager.setAdapter(sliderAdapter);

        // in here we should call the addDots function inside on create method
        addDots(0);

        // so in here on view pager with addOn page method we have to call the change listener variable
        viewPager.addOnPageChangeListener(changeListener);
    }


    public void skip(View view){

        startActivity(new Intent(this, UserDashboard.class));
        finish();
    }

    public void next(View view){

        // in here the view pager will go to next page while the user click the next button
        viewPager.setCurrentItem(currentPos + 1);


    }
    public void back(View view){

        // in here the view pager will go to next page while the user click the next button
        viewPager.setCurrentItem(currentPos - 1);


    }


    // so for creating the dost we have to create the function  of addDots
    private void addDots(int position){

        // in here in the text view of arrays we have pass the four slides
        dots = new TextView[4];

        // in here the dots layout should clear every time
        dotsLayout.removeAllViews();

        // for creating these views we are going to use for loop
        for (int i=0; i<dots.length;i++){

            // so this basically creating the text view and inside this we have to pass this on boarding activity
            // so we show the text view inside this on boarding screen
            dots[i]=new TextView(this);
            // so the create of dot we use html form with special characters
            dots[i].setText(Html.fromHtml("&#8226;"));
            // in here we set the text view size as wel as color and we could change as the way as we want
            dots[i].setTextSize(35);

            // so in here we add the dots in our layout
            dotsLayout.addView(dots[i]);

        }
        // if there is something then color of current page should be changed
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    // to change the color of dots we should get the current slide using the page
    // view pager has default method
    // so u will have the three method
    final ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            // in here we should call the function and pass the position
            // so for not getting the position you have to create the parameter in addDots function
            // as wel as pass zero the addDots function that you created on create method
            addDots(position);

            // in here we get the position
            currentPos = position;

            // this code is for hiding the LETS GET STARTED button form first second and third slide
            if (position == 0) {
                letsGetStared.setVisibility(View.INVISIBLE);
                backBtn.setVisibility(View.INVISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                letsGetStared.setVisibility(View.INVISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);

            } else if (position == 2) {
                letsGetStared.setVisibility(View.INVISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);

            } else {
                // in here we set animation to the button of LETS GET STARTED
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsGetStared.setAnimation(animation);
                letsGetStared.setVisibility(View.VISIBLE);

              /*  two = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.side_anim);
                backBtn.setVisibility(two);*/
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.INVISIBLE);


            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
