package Controlar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nouroeddinne.sweetsstore.HomeActivity;
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Model;
import Model.ModelCart;
import Utel.UtelsDB;

public class Adapter extends RecyclerView.Adapter<Adapter.ViweHolder> {

    Context context;
    ArrayList<Model> dessertList = new ArrayList<Model>();
    //private DataBaseAccess db ;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferencere;

    private Map<String, Boolean> favoriteMap = new HashMap<>();
    private Map<String, Boolean> cartMap = new HashMap<>();

    public Adapter(Context context, ArrayList<Model> dessertList) {
        this.context = context;
        this.dessertList = dessertList;

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferencere = firebaseDatabase.getReference();

        fetchFavoriteAndCartData();
    }

    @NonNull
    @Override
    public Adapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dessert, parent, false);
        return new ViweHolder(viwe);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViweHolder holder, @SuppressLint("RecyclerView") int position) {
        Model model;
        boolean[] newStatus = {false,false};
        int currentPosition =holder.getAdapterPosition();
        model = dessertList.get(position);
        holder.textName.setText(shorterWord(model.getName(),30));
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);

//        if (model.getFavorite()){
//            holder.imgFavorate.setImageResource(R.drawable.favorite_full);
//        }else {
//            holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
//        }
//
//        if (model.getCart()){
//            holder.imgCart.setVisibility(View.GONE);
//        }else {
//            holder.imgCart.setVisibility(View.VISIBLE);
//        }

        //db = DataBaseAccess.getInstance(context);



//        databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_FAVORATE).child(auth.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String data = String.valueOf(snapshot.child(model.getId()).child("id").getValue());
//                if (data!=null){
//                    if (data.equals(String.valueOf(model.getId())) && currentPosition==position){
//                        Log.d("TAG", "onDataChange: "+model.getName());
//                        holder.imgFavorate.setImageResource(R.drawable.favorite_full);
//                        newStatus[0] = true;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//        databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_CART).child(auth.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String data = String.valueOf(snapshot.child(model.getId()).child("id").getValue());
//                if (data!=null){
//                    if (data.equals(String.valueOf(model.getId()))){
//                        holder.imgCart.setVisibility(View.GONE);
//                        newStatus[1] = true;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        if (favoriteMap.containsKey(model.getId())) {
            holder.imgFavorate.setImageResource(R.drawable.favorite_full);
        } else {
            holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
        }

        if (cartMap.containsKey(model.getId())) {
            holder.imgCart.setVisibility(View.GONE);
        } else {
            holder.imgCart.setVisibility(View.VISIBLE);
        }



        holder.imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDessertActivity.class);
                intent.putExtra("model",model);
                context.startActivity(intent);
            }
        });





        holder.imgFavorate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                newStatus[0] =!newStatus[0];

                if (newStatus[0]){

                    databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_FAVORATE).child(auth.getUid()).child(model.getId()).child("id").setValue(model.getId()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                //Toast.makeText(context, "isSuccessful", Toast.LENGTH_SHORT).show();
                                holder.imgFavorate.setImageResource(R.drawable.favorite_full);
                            }else{
                                //Toast.makeText(context, "not Successful", Toast.LENGTH_SHORT).show();
                                holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
                            }
                        }
                    });

                }else {

                    holder.imgFavorate.setImageResource(R.drawable.favorite_empty);

                    DatabaseReference ref = databaseReferencere
                            .child(UtelsDB.FIREBASE_TABLE_DESSERT_FAVORATE)
                            .child(auth.getUid())
                            .child(model.getId());
                    ref.removeValue();

                }

//                dessertList.set(currentPosition, model);
//                notifyItemChanged(currentPosition);

                notifyDataSetChanged();


//                model.setFavorite(newFavoriteStatus);
//                db.open();
//                db.updateDessert(model);
//                db.close();
                dessertList.set(currentPosition, model);

            }
        });

        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newStatus[1] =!newStatus[1];

                if (newStatus[1]){

                    ModelCart mc = new ModelCart(model.getPrice(),1,model.getId());

                    databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_CART).child(auth.getUid()).child(model.getId()).setValue(mc).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                //Toast.makeText(context, "isSuccessful", Toast.LENGTH_SHORT).show();
                                holder.imgCart.setVisibility(View.GONE);
                            }else{
                                //Toast.makeText(context, "not Successful", Toast.LENGTH_SHORT).show();
                                holder.imgCart.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                }else {
                    holder.imgCart.setVisibility(View.VISIBLE);
                }









//                ModelCart mc = new ModelCart(model.getId(), 1,model.getPrice(),model.getPrice());
//
//                databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_CART).child(auth.getUid()).child(model.getId()).setValue(mc).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            holder.imgCart.setVisibility(View.GONE);
//                        }else{
//                            holder.imgCart.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//                notifyDataSetChanged();


//                boolean newCartStatus = !model.getCart();
//
//                if (model.getCart()){
//                }else {
//                    holder.imgCart.setVisibility(View.VISIBLE);
//                }
//
//                model.setCart(newCartStatus);
//                db.open();
//                db.updateDessert(model);
//                db.addDessertCart(new ModelCart(String.valueOf(model.getId()),"1",String.valueOf(model.getPrice()),String.valueOf(model.getPrice())));
//                db.close();
//                notifyDataSetChanged();


            }
        });



    }

    @Override
    public int getItemCount() {
        return dessertList.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder {
        TextView textName,textPrice;
        ImageView imgDessert,imgFavorate,imgCart;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textView3);
            textPrice = itemView.findViewById(R.id.textView9);
            imgDessert = itemView.findViewById(R.id.imageView11);
            imgFavorate = itemView.findViewById(R.id.imageView10);
            imgCart = itemView.findViewById(R.id.imageView3);

        }
    }



    public String shorterWord(String s,int n){

        int length = s.length();
        if(length>n){
            s = s.substring(0,n);
            s = s+"...";
            return s;
        }
        return s;
    }






    private void fetchFavoriteAndCartData() {
        DatabaseReference favRef = databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_FAVORATE).child(auth.getUid());
        favRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoriteMap.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.child("id").getValue(String.class);
                    if (id != null) {
                        favoriteMap.put(id, true);
                    }
                }
                notifyDataSetChanged(); // Notify adapter of data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        DatabaseReference cartRef = databaseReferencere.child(UtelsDB.FIREBASE_TABLE_DESSERT_CART).child(auth.getUid());
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartMap.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.child("id").getValue(String.class);
                    if (id != null) {
                        cartMap.put(id, true);
                    }
                }
                notifyDataSetChanged(); // Notify adapter of data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
















}



