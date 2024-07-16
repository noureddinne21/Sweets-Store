package com.nouroeddinne.sweetsstore;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.Iterator;
import Controlar.AdapterCart;
import Model.Model;
import Model.ModelCart;
import Database.DataBaseAccess;
import Model.ModelProfile;

public class CartActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner , FragmentQuickAlert.OnButtonLisner {
    private OnBackPressedCallback callback;
    RecyclerView recyclerView;
    Button button;
    ImageView back;
    private TextView totalPrice;
    static RecyclerView.Adapter adapter;
    ArrayList<ModelCart> dessertListCart;
    DataBaseAccess db = DataBaseAccess.getInstance(this);
    private Double total;
    private int numberOfItems;


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
                finish();
            }
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

        numberOfItems=0;
        total=0.0;
        for (ModelCart model : dessertListCart){
            total+=Double.valueOf(model.getPrice());
            numberOfItems++;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (HomeActivity.EMAIL!=null){
                    if (total>0){
                        FragmentQuickAlert fragment2 = FragmentQuickAlert.newInstance("Success","Payment complited successfully!",String.valueOf(R.color.green),String.valueOf(R.drawable.baseline_done_all_24));
                        fragment2.show(getSupportFragmentManager(),null);
                        fragment2.setCancelable(false);
                    }else {
                        Toast.makeText(CartActivity.this, "Sorry the cart is empty!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CartActivity.this, "login or creat acount!", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


    @Override
    public void onCklick() {

        boolean newCartStatus = false;
        db.open();
        Iterator<ModelCart> iterator = dessertListCart.iterator();

        while (iterator.hasNext()) {
            ModelCart mc = iterator.next();
            Model model = db.getDessertCartById(Integer.parseInt(mc.getIdd()));
            model.setCart(newCartStatus);
            db.updateDessert(model);
            db.deleteDessertCart(mc);
            iterator.remove();
        }

        ModelProfile profile = db.getPersonByEmail(HomeActivity.EMAIL);
        int nof = profile.getNumberPurchases()+numberOfItems;
        profile.setNumberPurchases(nof);
        double ts = profile.getTotalSpend()+total;
        profile.setTotalSpend(ts);
        db.updatePerson(profile);
        db.close();
        adapter.notifyDataSetChanged();

        Intent intent = new Intent(CartActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();

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