package com.nouroeddinne.sweetsstore;

import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_EMAIL;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_USER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DISPLAY_TIME = 3000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(SHAREDPREFERNCES_FILENAME_USER, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_EMAIL, null)==null) {
                    Intent intent = new Intent(SplashScreenActivity.this, SignupActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN_DISPLAY_TIME);




    }






















}