package com.nouroeddinne.sweetsstore;

import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_EMAIL;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_NAME;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_PASSWORD;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_USER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DataBaseAccess;
import Model.ModelProfile;

public class SignupActivity extends AppCompatActivity {
    Button tologin,singin,skip;
    ImageView imageView_facebook,imageView_google,imgShow;
    EditText email,name,password;
    private boolean PasswordVisible = false;
    SharedPreferences sharedPreferences;
    public static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String PASSWORD_PATTERN = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$" ;
    public static final String NAME_PATTERN = "^[A-Z][a-zA-z ]{2,29}$" ;
    DataBaseAccess db = DataBaseAccess.getInstance(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tologin = findViewById(R.id.button_goto_login);
        singin = findViewById(R.id.button_signup);
        skip = findViewById(R.id.button_skip);
        imageView_facebook = findViewById(R.id.imageView_facebook);
        imageView_google = findViewById(R.id.imageView_google);

        email = findViewById(R.id.editTextText_singup_email);
        name = findViewById(R.id.editTextText_singup_name);
        password = findViewById(R.id.editTextText_singup_Password);
        imgShow = findViewById(R.id.imageView_singup_show);

        sharedPreferences = getSharedPreferences(SHAREDPREFERNCES_FILENAME_USER, Context.MODE_PRIVATE);

        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordVisible = !PasswordVisible;

                if (PasswordVisible){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // show
                    imgShow.setImageResource(R.drawable.visibility_off);
                }else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance()); //hidden
                    imgShow.setImageResource(R.drawable.visibility_on);
                }

            }
        });


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

                if (validate(NAME_PATTERN,name.getText().toString())&& !name.getText().toString().isEmpty()){
                    if (validate(EMAIL_PATTERN,email.getText().toString())&& !email.getText().toString().isEmpty()){
                        if (validate(PASSWORD_PATTERN,password.getText().toString())&& !password.getText().toString().isEmpty()){

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(SHAREDPREFERNCES_FILENAME_NAME, name.getText().toString());
                            editor.putString(SHAREDPREFERNCES_FILENAME_EMAIL, email.getText().toString());
                            editor.putString(SHAREDPREFERNCES_FILENAME_PASSWORD, password.getText().toString());
                            editor.apply();

                            ModelProfile profile = new ModelProfile(name.getText().toString(),email.getText().toString(),password.getText().toString(),0,0.0);

                            db.open();
                            db.addProfile(profile);
//                            Log.d("TAG", "onCreate: "+profile.getId()+" "+profile.getName()+" "+profile.getEmail()+" "+profile.getPassword()+" "+profile.getNumberPurchases()+" "+profile.getTotalSpend());
                            db.close();

                            Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            password.setError("At least 8 chars\n" +
                                    "\n" +
                                    "Contains at least one digit\n" +
                                    "\n" +
                                    "Contains at least one lower alpha char and one upper alpha char\n" +
                                    "\n" +
                                    "Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                                    "\n" +
                                    "Does not contain space, tab, etc.");
                        }
                    }else {
                        email.setError("exp@gmail.com");
                    }
                }else {
                    name.setError("name should be between 3 and 30 characters");
                }

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



    public static boolean validate(String textPattren,String text) {
        Pattern pattern = Pattern.compile(textPattren, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }












}