package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Controlar.Adapter;
import Controlar.AdapterHistory;
import Controlar.AdapterTrand;
import Model.Model;
import Utel.UtelsDB;

public class SearchActivity extends AppCompatActivity implements OnBackPressedDispatcherOwner {
    private OnBackPressedCallback callback;
    RecyclerView recyclerView,recyclerViewHistory,recyclerViewTrand;
    private AdapterTrand adapterTrand;
    private AdapterHistory adapterHistory;
    EditText editText;
    ImageView back;
    LinearLayout linear_results,linear_no_results;
    RecyclerView.Adapter adapter;
    TextView search;
//    DataBaseAccess db = DataBaseAccess.getInstance(this);
ArrayList<Model> dessertList = new ArrayList<>();

    DatabaseReference databaseReferencere;
    FirebaseDatabase firebaseDatabase;

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
        search = findViewById(R.id.textView28);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferencere = firebaseDatabase.getReference();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadHistory();
        loadTrand();

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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length()<1){
                    linear_no_results.setVisibility(View.VISIBLE);
                }

                databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dessertList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Model model = dataSnapshot.getValue(Model.class);
                            if (model.getName().toLowerCase().contains(editText.getText().toString().toLowerCase())){
                                dessertList.add(model);
                            }
                        }
                        adapter = new Adapter(context, dessertList);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Toast.makeText(context, ""+dessertList.size(), Toast.LENGTH_SHORT).show();

                if(dessertList.size()>0){
                    linear_results.setVisibility(View.VISIBLE);
                    linear_no_results.setVisibility(View.GONE);
                }else {
                    linear_results.setVisibility(View.GONE);
                    linear_no_results.setVisibility(View.VISIBLE);
                }
            }

        });

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length()<1){
//                    linear_no_results.setVisibility(View.VISIBLE);
//                }
//
//                databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        dessertList.clear();
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                            Model model = dataSnapshot.getValue(Model.class);
//                            if (model.getName().toLowerCase().contains(s.toString().toLowerCase())){
//                                dessertList.add(model);
//                            }
//                        }
//                        adapter = new Adapter(context, dessertList);
//                        recyclerView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//
//
////                db.open();
////                dessertList=db.searchDESSERT(String.valueOf(s));
////                db.close();
//
//                //dessertList = search(String.valueOf(s));
//                //search(String.valueOf(s));
//                Toast.makeText(context, ""+dessertList.size(), Toast.LENGTH_SHORT).show();
//
//                if(dessertList.size()>0){
//                    linear_results.setVisibility(View.VISIBLE);
//                    linear_no_results.setVisibility(View.GONE);
//                }else {
//                    linear_results.setVisibility(View.GONE);
//                    linear_no_results.setVisibility(View.VISIBLE);
//                }
//
////                adapter = new Adapter(context, dessertList);
////                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });


    }


    public void search(String text){

        //dessertList.clear();


//        databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot s : snapshot.getChildren()) {
//                    Model m = s.getValue(Model.class);
//                    if (m != null && m.getName() != null) {
////                        Log.d("TAG", "onDataChange: "+m.getName().contains(text)+" "+text.contains(m.getName())+" "+m.getName());
//                    }
//
//                    if (m != null && m.getName() != null && m.getName().contains(text)) {
//                        dessertList.add(m);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }


    private void performSearch(String query) {
        // Clear the existing list
        //dessertList.clear();
//        adapter.notifyDataSetChanged();

        // Add a ChildEventListener to listen for changes in the database
        databaseReferencere.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Model m = snapshot.getValue(Model.class);

                if (m != null && m.getName() != null && m.getName().toLowerCase().contains(query.toLowerCase())) {
                    dessertList.add(m);
                    adapter.notifyDataSetChanged();
                    Log.d("TAG", "onChildAdded: " + m.getName());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Handle changes if necessary
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Handle removals if necessary
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Handle moves if necessary
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

    }



    public void loadTrand() {

        recyclerViewTrand = findViewById(R.id.recyclerViewTrand);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);

        List<String> items = new ArrayList<>();
        items.add("Cupcakes with Creamy");
        items.add("Baked Funfetti");
        items.add("lemon cupcakes");
        items.add("chocolate cookies");
        items.add("cookie bars");
        items.add("cider doughnuts");

        adapterTrand = new AdapterTrand(items);

        recyclerViewTrand.setLayoutManager(flexboxLayoutManager);
        recyclerViewTrand.setAdapter(adapterTrand);

    }


    public void loadHistory(){

        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);

        List<String> items = new ArrayList<>();
        items.add("Cupcakes");
        items.add("chocolate");
        items.add("doughnuts");

        adapterHistory = new AdapterHistory(items);

        recyclerViewHistory.setLayoutManager(flexboxLayoutManager);
        recyclerViewHistory.setAdapter(adapterHistory);

    }


}