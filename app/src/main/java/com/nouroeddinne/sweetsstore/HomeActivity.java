package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Controlar.Adapter;
import Database.Database;
import Model.Model;

public class HomeActivity extends AppCompatActivity {
    ImageView favourite,cart,search,profail;
    RecyclerView recyclerView;
    static RecyclerView.Adapter adapter;
    static ArrayList<Model> dessertList;
     Database db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        favourite = findViewById(R.id.imageView_favourite);
        cart = findViewById(R.id.imageView_cart);
        search = findViewById(R.id.imageView_search);
        profail = findViewById(R.id.imageView_profaile);
        recyclerView = findViewById(R.id.recyclerView);

        db = new Database(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));




//
//        db.addDessert(new Model("Cake","22.99","https://i.ibb.co/jDbTkv0/cake.png",false,false));
//        db.addDessert(new Model("Cake","13.99","https://i.ibb.co/5vS87y1/cake-1.png",false,false));
//        db.addDessert(new Model("Cake","9.99","https://i.ibb.co/yy5PKkS/cake-2.png",false,false));
//        db.addDessert(new Model("Cookies","12.99","https://i.ibb.co/ZxQcQkw/cookies.png",false,false));
//        db.addDessert(new Model("Croissant","11.99","https://i.ibb.co/Y2T7n5F/croissant.png",false,false));
//        db.addDessert(new Model("Cupcake","15.99","https://i.ibb.co/w7XQ24p/cupcake.png",false,false));
//        db.addDessert(new Model("Dessert","7.99","https://i.ibb.co/34VSYHG/dessert.png",false,false));
//        db.addDessert(new Model("Dessert","6.99","https://i.ibb.co/7tDTRwg/dessert-1.png",false,false));
//        db.addDessert(new Model("Donut","14.99","https://i.ibb.co/RSzHStR/donut.png",false,false));
//        db.addDessert(new Model("Icecream","3.99","https://i.ibb.co/6yh1F0d/ice-cream-1.png",false,false));
//        db.addDessert(new Model("Macaron","11.99","https://i.ibb.co/ZYB9NGD/macaron.png",false,false));
//        db.addDessert(new Model("Icecream","2.99","https://i.ibb.co/SQ3r9L9/ice-cream.png",false,false));
//        db.addDessert(new Model("Pancake","17.99","https://i.ibb.co/mFKV56Z/pancake.png",false,false));
//        db.addDessert(new Model("Pretzel","19.99","https://i.ibb.co/TgDTqLJ/pretzel.png",false,false));
//        db.addDessert(new Model("Tart","6.99","https://i.ibb.co/JcKmNbr/tart.png",false,false));
//        db.addDessert(new Model("Waffles","29.99","https://i.ibb.co/TqN6nBC/waffles.png",false,false));
//        db.addDessert(new Model("Dango","19.99","https://i.ibb.co/NnP0PcF/dango.png",false,false));
//



        dessertList=db.getAllDESSERT();

//        for (Model model : dessertList){
//
//            Log.d("dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());
//
//        }

        adapter = new Adapter(this,dessertList);
        recyclerView.setAdapter(adapter);

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, Favourite_Dessert_Activity.class);
                startActivity(intent);
                finish();

            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
                finish();

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();

            }
        });


        profail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();

            }
        });



        }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, String.valueOf(event.getAction()), Toast.LENGTH_LONG).show();
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }























}



