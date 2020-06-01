package com.example.kabultrafficjams.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kabultrafficjams.R;
import com.example.kabultrafficjams.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    // Variables
    ImageView backgroundImage;
    TextView poweredByLine;

    // Animations
    Animation sideAnim, bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // statues bar will be gone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        backgroundImage=findViewById(R.id.background_image);
        poweredByLine=findViewById(R.id.powered_by_line);

        // Animations design
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //set Animation on elements
        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                // when the first time the user come to the application the compiler will going to create the share preference
                // with the name of onBoardingScreen
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                // then to get the boolean of type first time which is set True
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                // so when the user come to first time
                if(isFirstTime){

                    // so when the user not see the onBoardingScreen again again so for that we use the editor
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    // and we just call the first Time variable
                    editor.putBoolean("firstTime",false);
                    // it just commit the all changed
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(),OnBoarding.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIMER);
    }
}
