package com.nouroeddinne.sweetsstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    Button tologin,singin,skip;
    ImageView imageView_facebook,imageView_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tologin = findViewById(R.id.button_goto_login);
        singin = findViewById(R.id.button_signup);
        skip = findViewById(R.id.button_skip);
        imageView_facebook = findViewById(R.id.imageView_facebook);
        imageView_google = findViewById(R.id.imageView_google);
        
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });


        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });


        imageView_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SignupActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });



        imageView_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SignupActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                
            }
        });



        
        
        
    }
}