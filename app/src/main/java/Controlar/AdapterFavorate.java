package Controlar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.nouroeddinne.sweetsstore.OnClickListener;
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;
import java.util.ArrayList;
import Database.Database;
import Model.Model;
import Model.ModelCart;
import Database.DataBaseAccess;

public class AdapterFavorate extends Adapter{
    private DataBaseAccess db ;

    public AdapterFavorate(Context context, ArrayList<Model> dessertList) {
        super(context, dessertList);
    }

    public void onBindViewHolder(@NonNull Adapter.ViweHolder holder, @SuppressLint("RecyclerView") int position) {

        int currentPosition =holder.getAdapterPosition();
        Model model = dessertList.get(currentPosition);
        holder.textName.setText(shorterWord(model.getName(),30));
        holder.textPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);
        holder.imgFavorate.setImageResource(R.drawable.favorite_full);

        holder.imgCart.setVisibility(View.GONE);

        db = DataBaseAccess.getInstance(context);
//        if (model.getCart()){
//            holder.imgCart.setVisibility(View.GONE);
//        }

//        db=new Database(context);

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

                int currentPosition =holder.getAdapterPosition();
                boolean newFavoriteStatus = !model.getFavorite();
                model.setFavorite(newFavoriteStatus);

                db.open();
                db.updateDessert(model);
                db.close();

                dessertList.remove(currentPosition);
                notifyItemRemoved(currentPosition);

            }
        });

//
//        holder.imgCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int currentPosition =holder.getAdapterPosition();
//                boolean newCartStatus = !model.getCart();
//                model.setCart(newCartStatus);
//
//                if (newCartStatus) {
//                    holder.imgCart.setVisibility(View.GONE);
//                }
//
//                db.updateDessert(model);
//                dessertList.set(currentPosition, model);
//                db.addDessertCart(new ModelCart(String.valueOf(model.getId()),"1",String.valueOf(model.getPrice()),String.valueOf(model.getPrice())));
//                notifyItemChanged(currentPosition);
//
//            }
//        });

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
