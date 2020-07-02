package com.example.kabultrafficjams.Common.LoginSignup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kabultrafficjams.Databases.CheckInternet;
import com.example.kabultrafficjams.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {


    // Variables
    private ImageView screenIcon;
    private TextView title, description;

    private TextInputLayout confirmNewPassword,newPassword;

    //private Button updatePasswordBtn;

    private Animation animation;

    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        // hooks
        screenIcon = findViewById(R.id.new_password_icon);
        title = findViewById(R.id.new_password_title);
        description = findViewById(R.id.new_password_description);


        newPassword = findViewById(R.id.new_password);
        confirmNewPassword = findViewById(R.id.confirm_password);
        //updatePasswordBtn = findViewById(R.id.set_new_password_btn);
        progressBar = findViewById(R.id.set_new_progress_bar);


        // animation hook
        animation = AnimationUtils.loadAnimation(this, R.anim.side_anim);

        // set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        //newPassword.setAnimation(animation);
        //confirmNewPassword.setAnimation(animation);
        //updatePasswordBtn.setAnimation(animation);
        //progressBar.setAnimation(animation);

    }



    public void setNewPasswordBtn(View view){

        // Check internet connection
        CheckInternet checkInternet = new CheckInternet();
        if(!checkInternet.isConnected(this)){
            showCustomDialog();
            return;
        }

        if(!validatePassword() | !validateConfirmPassword()){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        // get data from fields
        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNo");

        // update data in firebase and in Sessions
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();


    }

    /*
      internet
      connection dialog
       */
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPassword.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Wifi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Mobile Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private boolean validatePassword() {

        String val = newPassword.getEditText().getText().toString();

        if(val.isEmpty()){
            newPassword.setError(("field can not be empty"));
            return false;
        }else{
            newPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {

        String val = confirmNewPassword.getEditText().getText().toString();
        if(val.isEmpty()){
            confirmNewPassword.setError(("field can not be empty"));
            return false;
        }
        else if(!val.equals(val)){
            confirmNewPassword.setError(("Password Does not match? Please try again"));
            return false;
        }
        else{
            confirmNewPassword.setError(null);
            return true;
        }

    }

}
