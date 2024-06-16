package Controlar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nouroeddinne.sweetsstore.Favourite_Dessert_Activity;
import com.nouroeddinne.sweetsstore.HomeActivity;
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Database.Database;
import Database.DatabaseCart;
import Model.Model;
import Model.ModelCart;

public class Adapter extends RecyclerView.Adapter<Adapter.ViweHolder> {
    Context context;
    ArrayList<Model> dessertList;
    Database db ;
    DatabaseCart dbc ;

    public Adapter(Context context, ArrayList<Model> dessertList) {
        this.context = context;
        this.dessertList = dessertList;
    }

    @NonNull
    @Override
    public Adapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dessert, parent, false);
        return new ViweHolder(viwe);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViweHolder holder, @SuppressLint("RecyclerView") int position) {

        Model model = dessertList.get(position);
        holder.textName.setText(model.getName());
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);

        if (model.getFavorite()){
            holder.imgFavorate.setImageResource(R.drawable.favorite_full);
        }else {
            holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
        }

        if (model.getCart()){
            holder.imgCart.setVisibility(View.GONE);
        }

        db=new Database(context);
        dbc = new DatabaseCart(context);


        holder.imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDessertActivity.class);
                intent.putExtra("position",model.getId());
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });


        holder.imgFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newFavoriteStatus = !model.getFavorite();
                model.setFavorite(newFavoriteStatus);

                if (newFavoriteStatus) {
                    holder.imgFavorate.setImageResource(R.drawable.favorite_full);
                } else {
                    holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
                }

                db.updateDessert(model);
                dessertList.set(position, model);
                notifyItemChanged(position);

            }
        });


        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newCartStatus = !model.getCart();
                model.setCart(newCartStatus);

                db.updateDessert(model);
                dessertList.set(position, model);
                notifyItemChanged(position);

                dbc.addDessert(new ModelCart(String.valueOf(model.getId()), model.getPrice(),"0"));

                //Log.d("dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());

            }
        });

    }

    @Override
    public int getItemCount() {
        return dessertList.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{

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






}
