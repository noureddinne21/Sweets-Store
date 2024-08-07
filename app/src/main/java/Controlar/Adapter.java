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
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nouroeddinne.sweetsstore.CartActivity;
import com.nouroeddinne.sweetsstore.Favourite_Dessert_Activity;
import com.nouroeddinne.sweetsstore.HomeActivity;
import com.nouroeddinne.sweetsstore.ProfileActivity;
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.SearchActivity;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;
import java.util.ArrayList;
import Database.Database;
import Model.Model;
import Model.ModelCart;
import Database.DataBaseAccess;

public class Adapter extends RecyclerView.Adapter<Adapter.ViweHolder> {

    Context context;
    ArrayList<Model> dessertList;
    private DataBaseAccess db ;

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

        int currentPosition =holder.getAdapterPosition();
        Model model = dessertList.get(currentPosition);
        holder.textName.setText(shorterWord(model.getName(),30));
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);

        if (model.getFavorite()){
            holder.imgFavorate.setImageResource(R.drawable.favorite_full);
        }else {
            holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
        }

        if (model.getCart()){
            holder.imgCart.setVisibility(View.GONE);
        }else {
            holder.imgCart.setVisibility(View.VISIBLE);
        }

        db = DataBaseAccess.getInstance(context);



        holder.imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDessertActivity.class);
                intent.putExtra("position",model.getId());
                context.startActivity(intent);
            }
        });


        holder.imgFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newFavoriteStatus = !model.getFavorite();

                if (model.getFavorite()){
                    holder.imgFavorate.setImageResource(R.drawable.favorite_full);
                }else {
                    holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
                }

                model.setFavorite(newFavoriteStatus);
                db.open();
                db.updateDessert(model);
                db.close();
                dessertList.set(currentPosition, model);
                notifyDataSetChanged();

            }
        });

        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newCartStatus = !model.getCart();

                if (model.getCart()){
                    holder.imgCart.setVisibility(View.GONE);
                }else {
                    holder.imgCart.setVisibility(View.VISIBLE);
                }

                model.setCart(newCartStatus);
                db.open();
                db.updateDessert(model);
                db.addDessertCart(new ModelCart(String.valueOf(model.getId()),"1",String.valueOf(model.getPrice()),String.valueOf(model.getPrice())));
                db.close();
                notifyDataSetChanged();


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



}































