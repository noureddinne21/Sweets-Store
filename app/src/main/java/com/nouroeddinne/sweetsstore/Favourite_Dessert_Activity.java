package com.nouroeddinne.sweetsstore;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Controlar.AdapterFavorate;
import Database.Database;
import Model.Model;
import Database.DataBaseAccess;

public class Favourite_Dessert_Activity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    ImageView back;
    RecyclerView recyclerView;
    static AdapterFavorate adapter;
    static ArrayList<Model> dessertList;
    DataBaseAccess db = DataBaseAccess.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_dessert);


        back = findViewById(R.id.imageView17);
        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        db.open();
        dessertList=db.getFavorateDESSERT();
        db.close();

        adapter = new AdapterFavorate(this, dessertList);
        recyclerView.setAdapter(adapter);

        callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Favourite_Dessert_Activity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favourite_Dessert_Activity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}