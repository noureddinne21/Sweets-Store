package com.nouroeddinne.sweetsstore;

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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Controlar.Adapter;
import Database.Database;
import Model.Model;

public class Favourite_Dessert_Activity extends AppCompatActivity {

    ImageView home,cart,search,profail;
    RecyclerView recyclerView;
    static RecyclerView.Adapter adapter;
    static ArrayList<Model> dessertList;
    Database db;
    Context context= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_dessert);

        home = findViewById(R.id.imageView_home);
        cart = findViewById(R.id.imageView_cart);
        search = findViewById(R.id.imageView_search);
        profail = findViewById(R.id.imageView_profaile);
        recyclerView = findViewById(R.id.recyclerView);

        db = new Database(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        dessertList=db.getFavorateDESSERT();

        for (Model model : dessertList){
            Log.d("favorate dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());
        }


        Toast.makeText(context, String.valueOf(dessertList.size()), Toast.LENGTH_SHORT).show();


        adapter = new Adapter(context,dessertList);
        recyclerView.setAdapter(adapter);







        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Favourite_Dessert_Activity.this, HomeActivity.class);
                startActivity(intent);

            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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
}