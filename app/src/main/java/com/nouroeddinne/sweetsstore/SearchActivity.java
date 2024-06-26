package com.nouroeddinne.sweetsstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
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
import Database.DataBaseAccess;

public class SearchActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    RecyclerView recyclerView;
    EditText editText;
    ImageView back;
    LinearLayout linear_results,linear_no_results;
    static RecyclerView.Adapter adapter;
    static ArrayList<Model> dessertList;
//    Database db;
    DataBaseAccess db = DataBaseAccess.getInstance(this);
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editTextText);
        linear_results = findViewById(R.id.linear_results);
        linear_no_results = findViewById(R.id.linear_no_results);


        back = findViewById(R.id.imageView17);


//        db = new Database(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                db.open();
                dessertList=db.searchDESSERT(String.valueOf(s));
                db.close();

                if(dessertList.size()>0){
                    linear_results.setVisibility(View.VISIBLE);
                    linear_no_results.setVisibility(View.GONE);
                }else {
                    linear_results.setVisibility(View.GONE);
                    linear_no_results.setVisibility(View.VISIBLE);
                }

                adapter = new Adapter(context, dessertList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


    }




}