package com.example.kabultrafficjams.Common.LoginSignup;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kabultrafficjams.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    // Variables

    ImageView backBtn;
    Button next, login;
    TextView titleText;

    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up);

        // Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        // hooks for getting data

        fullName = findViewById(R.id.signup_fullName);
        email = findViewById(R.id.signup_email);
        username= findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
    }


    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()){
            return;
        }


        String _fullName = fullName.getEditText().getText().toString();
        String _email = email.getEditText().getText().toString();
        String _username = username.getEditText().getText().toString();
        String _password = password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        intent.putExtra("fullName",_fullName);
        intent.putExtra("email",_email);
        intent.putExtra("username",_username);
        intent.putExtra("password",_password);

        // add transition

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    // validation function

    private  boolean validateFullName(){
        String val = fullName.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            fullName.setError(("field can not be empty"));
            return false;
        }else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private  boolean validateUsername(){
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";


        if(val.isEmpty()){
            username.setError(("Field can not be empty"));
            return false;
        }
        else if(val.length()>20){
            username.setError(("Username is too large!"));
            return false;
        }
        else if(!val.matches(checkspaces)){
            username.setError(("No White spaces are allowed!"));
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private  boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if(val.isEmpty()){
            email.setError(("Field can not be empty"));
            return false;
        }

        else if(!val.matches(checkEmail)){
            email.setError(("Invalid Email"));
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private  boolean validatePassword(){
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +   // at least 1 digit
                "(?=.*[a-z])" +   // at least 1 lower case letter
                "(?=.*[A-Z])" +   // at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  // any letter;
                "(?=.*[@#$%^&+=])" +   // at least 1 special character
                "(?=\\S+$)" +          // no white spaces
                ".{4,}" +              // at least 5 characters
                "$";

        if(val.isEmpty()){
            password.setError(("Field can not be empty"));
            return false;
        }

        else if(!val.matches(checkPassword)){
            password.setError(("Password should contain 4 characters!"));
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}
