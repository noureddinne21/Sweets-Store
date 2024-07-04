package com.nouroeddinne.sweetsstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import Controlar.AdapterCart;
import Database.Database;
import Model.ModelCart;
import Database.DataBaseAccess;

public class CartActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    RecyclerView recyclerView;
    Button button;
    ImageView back;
    private TextView totalPrice;
    static RecyclerView.Adapter adapter;
    static ArrayList<ModelCart> dessertListCart;
    DataBaseAccess db = DataBaseAccess.getInstance(this);

    private static Double total=0.0;

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
        totalPrice = findViewById(R.id.textView14);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;

        callback = new OnBackPressedCallback(true) {
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
                finish();            }
        });

        db.open();
        dessertListCart=db.getAllDESSERTCart();
        db.close();

        adapter = new AdapterCart(this,dessertListCart);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                getTotal();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                getTotal();
            }
        });

        recyclerView.setAdapter(adapter);

        getTotal();

        for (ModelCart model : dessertListCart){
            total+=Double.valueOf(model.getPrice());
            Log.d("TAG", "Cart Activty: id "+model.getId()+" idd "+model.getIdd()+" price "+model.getPrice()+" count "+model.getCount()+" index "+String.valueOf(dessertListCart.indexOf(model)));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CartActivity.this, String.valueOf(total), Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void getTotal(){
        DecimalFormat df = new DecimalFormat("#.##");
        total=0.0;
        for (ModelCart model : dessertListCart){
            total+=Double.valueOf(model.getTotalPrice());
        }
        totalPrice.setText(String.valueOf(df.format(total)));

    }



















}