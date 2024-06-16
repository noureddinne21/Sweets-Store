package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import Database.Database;
import Model.Model;

public class ShowDessertActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    ImageView imgDessert,imgFavorate,back;
    TextView textName,textPrice,textDescription,textIngredients;
    Button button1,button3,button5,button10,buttonAddToCart;
    EditText editTextCustom;
    Database db;
    ArrayList<Model> dessertList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_dessert);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgDessert = findViewById(R.id.imageView14_show_img);
        imgFavorate = findViewById(R.id.imageView15_show_favorate);
        back = findViewById(R.id.imageView17);
        textName = findViewById(R.id.textView10_show_name);
        textPrice = findViewById(R.id.textView22_show_price);
        textDescription = findViewById(R.id.textView21__show_description);
        textIngredients = findViewById(R.id.textView26_show_ingredients);
        button1 = findViewById(R.id.button_show_count_1);
        button3 = findViewById(R.id.button_show_count_3);
        button5 = findViewById(R.id.button_show_count_5);
        button10 = findViewById(R.id.button_show_count_10);
        editTextCustom = findViewById(R.id.editText_show_count);
        buttonAddToCart = findViewById(R.id.button);

        button1.setBackgroundResource(R.drawable.backgroun_item_cart);
        button3.setBackgroundResource(R.drawable.backgroun_item_cart);
        button5.setBackgroundResource(R.drawable.backgroun_item_cart);
        button10.setBackgroundResource(R.drawable.backgroun_item_cart);

        db = new Database(this);

        callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Toast.makeText(ShowDessertActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ShowDessertActivity.this, "Back", Toast.LENGTH_SHORT).show();

            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras!=null){

            int p = extras.getInt("position");

            Glide.with(this).load(db.getDessertById(p).getImg()).into(imgDessert);

            if (db.getDessertById(p).getFavorite()){
                imgFavorate.setImageResource(R.drawable.favorite_full);
            }else {
                imgFavorate.setImageResource(R.drawable.favorite_empty);
            }

            textName.setText(db.getDessertById(p).getName());
            textPrice.setText(db.getDessertById(p).getPrice());

            imgFavorate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean newFavoriteStatus = !db.getDessertById(p).getFavorite();

                    Model model = new Model(db.getDessertById(p).getId(),
                            db.getDessertById(p).getName(),
                            db.getDessertById(p).getPrice(),
                            db.getDessertById(p).getImg(),
                            newFavoriteStatus,
                            db.getDessertById(p).getCart());

                    db.updateDessert(model);

                    if (newFavoriteStatus) {
                        imgFavorate.setImageResource(R.drawable.favorite_full);
                    } else {
                        imgFavorate.setImageResource(R.drawable.favorite_empty);
                    }

                }
            });

            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean newCartStatus = !db.getDessertById(p).getCart();

                    Model model = new Model(db.getDessertById(p).getId(),
                            db.getDessertById(p).getName(),
                            db.getDessertById(p).getPrice(),
                            db.getDessertById(p).getImg(),
                            db.getDessertById(p).getFavorite(),
                            newCartStatus);

                    if (newCartStatus) {
                        db.updateDessert(model);
                        Intent intent = new Intent(ShowDessertActivity.this,CartActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ShowDessertActivity.this, "This Dessert Is Already In Cart", Toast.LENGTH_SHORT).show();

                    }


                }
            });

        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(R.drawable.background_button_checkout);
                button3.setBackgroundResource(R.drawable.backgroun_item_cart);
                button5.setBackgroundResource(R.drawable.backgroun_item_cart);
                button10.setBackgroundResource(R.drawable.backgroun_item_cart);
                editTextCustom.setBackgroundResource(R.drawable.backgroun_item_cart);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(R.drawable.backgroun_item_cart);
                button3.setBackgroundResource(R.drawable.background_button_checkout);
                button5.setBackgroundResource(R.drawable.backgroun_item_cart);
                button10.setBackgroundResource(R.drawable.backgroun_item_cart);
                editTextCustom.setBackgroundResource(R.drawable.backgroun_item_cart);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(R.drawable.backgroun_item_cart);
                button3.setBackgroundResource(R.drawable.backgroun_item_cart);
                button5.setBackgroundResource(R.drawable.background_button_checkout);
                button10.setBackgroundResource(R.drawable.backgroun_item_cart);
                editTextCustom.setBackgroundResource(R.drawable.backgroun_item_cart);

            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(R.drawable.backgroun_item_cart);
                button3.setBackgroundResource(R.drawable.backgroun_item_cart);
                button5.setBackgroundResource(R.drawable.backgroun_item_cart);
                button10.setBackgroundResource(R.drawable.background_button_checkout);
                editTextCustom.setBackgroundResource(R.drawable.backgroun_item_cart);

            }
        });



        editTextCustom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button1.setBackgroundResource(R.drawable.backgroun_item_cart);
                button3.setBackgroundResource(R.drawable.backgroun_item_cart);
                button5.setBackgroundResource(R.drawable.backgroun_item_cart);
                button10.setBackgroundResource(R.drawable.backgroun_item_cart);
                editTextCustom.setBackgroundResource(R.drawable.background_button_checkout);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });









    }




















}