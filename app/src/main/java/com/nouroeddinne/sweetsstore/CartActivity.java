package com.nouroeddinne.sweetsstore;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Controlar.AdapterCart;
import Database.Database;
import Model.Model;
import Model.ModelCart;

public class CartActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    RecyclerView recyclerView;
    Button button;
    ImageView back;
    static RecyclerView.Adapter adapter;
    static ArrayList<ModelCart> dessertList;
    static ArrayList<ModelCart> dessertListCart;
    Database db;
    //DatabaseCart dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
        back = findViewById(R.id.imageView17);

        db = new Database(this);
        //dbc = new DatabaseCart(this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;

        callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dessertList=db.getAllDESSERTCart();
        adapter = new AdapterCart(this,dessertList);
        recyclerView.setAdapter(adapter);


        //dbc.addDessert(new ModelCart("0","11","2"));




        //Log.d("ttg", "tgv : "+dbc.checkTableExists());

        dessertListCart=db.getAllDESSERTCart();
        for (ModelCart model : dessertListCart){

            Log.d("TAG", "Cart Activty: id "+model.getId()+" idd "+model.getIdd()+" price "+model.getPrice()+" count "+model.getCount());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
























}