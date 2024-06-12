package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    Context context= this;



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
//
//        db.addDessert(new Model("Cake","22.99$","https://i.ibb.co/jDbTkv0/cake.png",false,false));
//        db.addDessert(new Model("Cake","13.99$","https://i.ibb.co/5vS87y1/cake-1.png",false,false));
//        db.addDessert(new Model("Cake","9.99$","https://i.ibb.co/yy5PKkS/cake-2.png",false,false));
//        db.addDessert(new Model("Cookies","12.99$","https://i.ibb.co/ZxQcQkw/cookies.png",false,false));
//        db.addDessert(new Model("Croissant","11.99$","https://i.ibb.co/Y2T7n5F/croissant.png",false,false));
//        db.addDessert(new Model("Cupcake","15.99$","https://i.ibb.co/w7XQ24p/cupcake.png",false,false));
//        db.addDessert(new Model("Dessert","7.99$","https://i.ibb.co/34VSYHG/dessert.png",false,false));
//        db.addDessert(new Model("Dessert","6.99$","https://i.ibb.co/7tDTRwg/dessert-1.png",false,false));
//        db.addDessert(new Model("Donut","14.99$","https://i.ibb.co/RSzHStR/donut.png",false,false));
//        db.addDessert(new Model("Icecream","3.99$","https://i.ibb.co/6yh1F0d/ice-cream-1.png",false,false));
//        db.addDessert(new Model("Macaron","11.99$","https://i.ibb.co/ZYB9NGD/macaron.png",false,false));
//        db.addDessert(new Model("Icecream","2.99$","https://i.ibb.co/SQ3r9L9/ice-cream.png",false,false));
//        db.addDessert(new Model("Pancake","17.99$","https://i.ibb.co/mFKV56Z/pancake.png",false,false));
//        db.addDessert(new Model("Pretzel","19.99$","https://i.ibb.co/TgDTqLJ/pretzel.png",false,false));
//        db.addDessert(new Model("Tart","6.99$","https://i.ibb.co/JcKmNbr/tart.png",false,false));
//        db.addDessert(new Model("Waffles","29.99$","https://i.ibb.co/TqN6nBC/waffles.png",false,false));
//        db.addDessert(new Model("Dango","19.99$","https://i.ibb.co/NnP0PcF/dango.png",false,false));
//



        dessertList=db.getAllDESSERT();

        for (Model model : dessertList){

            Log.d("dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());

        }

        adapter = new Adapter(context,dessertList);
        recyclerView.setAdapter(adapter);
        //refsh();

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, Favourite_Dessert_Activity.class);
                startActivity(intent);

            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, String.valueOf(db.NumDessert()), Toast.LENGTH_SHORT).show();

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        profail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        }


    public static void refsh(){
        adapter.notifyDataSetChanged();
    }

    public static void refshPosition(int position){
        adapter.notifyItemChanged(position);


    }




    }



