package Controlar;

import android.content.Context;
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
import com.nouroeddinne.sweetsstore.HomeActivity;
import com.nouroeddinne.sweetsstore.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Database.Database;
import Model.Model;

public class Adapter extends RecyclerView.Adapter<Adapter.ViweHolder> {
    Context context;
    ArrayList<Model> dessertList;
    Database db ;
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
    public void onBindViewHolder(@NonNull Adapter.ViweHolder holder, int position) {

        Model model = dessertList.get(position);
        holder.textName.setText(model.getName());
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);

        if (model.getFavorite()){
            holder.imgFavorate.setImageResource(R.drawable.favorite_full);
        }else {
            holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
        }

        if (model.getFavorite()){
            holder.imgCart.setImageResource(R.drawable.aded_to_cart);
        }else {
            holder.imgCart.setImageResource(R.drawable.add_to_cart);
        }

        db=new Database(context);

        holder.imgFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("dessert", "status : "+model.getFavorite());
                if (model.getFavorite()){
                    holder.imgFavorate.setImageResource(R.drawable.favorite_empty);
                    Toast.makeText(context, String.valueOf(db.updateDessert(new Model(model.getId(),model.getName(),model.getPrice(), model.getImg(),false, model.getCart()))), Toast.LENGTH_SHORT).show();
                }else {
                    holder.imgFavorate.setImageResource(R.drawable.favorite_full);
                    Toast.makeText(context, String.valueOf(db.updateDessert(new Model(model.getId(),model.getName(),model.getPrice(), model.getImg(),true,model.getCart()))), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context,String.valueOf( model.getFavorite())+"2", Toast.LENGTH_SHORT).show();
                }
                Log.d("dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());

                notifyDataSetChanged();notifyDataSetChanged




















            }
        });

        holder.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.getCart()){
                    holder.imgCart.setImageResource(R.drawable.add_to_cart);
                    db.updateDessert(new Model(model.getId(),model.getName(),model.getPrice(), model.getImg(), model.getFavorite(), false));
                }else {
                    holder.imgCart.setImageResource(R.drawable.aded_to_cart);
                    db.updateDessert(new Model(model.getId(),model.getName(),model.getPrice(), model.getImg(), model.getFavorite(), true));
                }
                Log.d("dessert", "onClick: "+model.getId()+" "+model.getName()+" "+model.getPrice()+" "+model.getImg()+" "+model.getFavorite()+" "+model.getCart());
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
