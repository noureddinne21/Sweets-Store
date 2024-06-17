package Controlar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.nouroeddinne.sweetsstore.Favourite_Dessert_Activity;
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;

import java.util.ArrayList;

import Database.Database;
import Model.Model;
import Model.ModelCart;

public class AdapterFavorate extends Adapter{
    public AdapterFavorate(Context context, ArrayList<Model> dessertList) {
        super(context, dessertList);
    }


    public void onBindViewHolder(@NonNull Adapter.ViweHolder holder, @SuppressLint("RecyclerView") int position) {

        Model model = dessertList.get(position);
        holder.textName.setText(model.getName());
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);
        holder.imgFavorate.setImageResource(R.drawable.favorite_full);


        if (model.getCart()){
            holder.imgCart.setImageResource(R.drawable.aded_to_cart);
        }else {
            holder.imgCart.setImageResource(R.drawable.add_to_cart);
        }

        db=new Database(context);

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

                db.updateDessert(model);
                dessertList.remove(position);
                notifyItemRemoved(position);

                db.addDessertCart(new ModelCart(String.valueOf(model.getId()),"1",model.getPrice()));


            }
        });


        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newCartStatus = !model.getCart();
                model.setCart(newCartStatus);

                if (newCartStatus) {
                    holder.imgCart.setVisibility(View.GONE);
                }

                db.updateDessert(model);
                dessertList.set(position, model);
                notifyItemChanged(position);

            }
        });

    }

}
