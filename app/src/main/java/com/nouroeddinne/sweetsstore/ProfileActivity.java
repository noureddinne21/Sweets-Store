package com.nouroeddinne.sweetsstore;

import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_NAME;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_USER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

import Database.DataBaseAccess;
import Model.ModelCart;
import Model.ModelProfile;

public class ProfileActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    ImageView back;
    TextView name,textView_number_of_purches,textView_total_sepend;
    SharedPreferences sharedPreferences;
    DataBaseAccess db = DataBaseAccess.getInstance(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.imageView17);
        name = findViewById(R.id.textView_name);
        textView_total_sepend = findViewById(R.id.textView_total_sepend);
        textView_number_of_purches = findViewById(R.id.textView_number_of_purches);

        sharedPreferences = getSharedPreferences(SHAREDPREFERNCES_FILENAME_USER, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_NAME, null)!=null) {
            name.setText(sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_NAME, null));
        }


        db.open();
        ModelProfile profile =db.getPersonByEmail(HomeActivity.EMAIL);
        db.close();

        textView_total_sepend.setText(String.valueOf(formatTotal(profile.getTotalSpend())));
        textView_number_of_purches.setText(String.valueOf(profile.getNumberPurchases()));
        Log.d("TAG", "onCreate: "+profile.getId()+" "+profile.getName()+" "+profile.getEmail()+" "+profile.getPassword()+" "+profile.getNumberPurchases()+" "+profile.getTotalSpend());



        callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



    public Double formatTotal(Double t){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(t));

    }
















}