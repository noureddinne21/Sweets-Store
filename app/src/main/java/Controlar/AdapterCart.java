package Controlar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.nouroeddinne.sweetsstore.R;
import com.nouroeddinne.sweetsstore.ShowDessertActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Database.Database;
import Model.Model;
import Model.ModelCart;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViweHolder>{

    Context context;
    //ArrayList<Model> dessertList;
    ArrayList<ModelCart> dessertListCart;
    Database db ;
    public static Double total=0.0;
    public AdapterCart(Context context, ArrayList<ModelCart> dessertListCart) {
        this.context = context;
        this.dessertListCart = dessertListCart;
    }

    @NonNull
    @Override
    public AdapterCart.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cart, parent, false);
        return new AdapterCart.ViweHolder(viwe);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViweHolder holder, int position) {
        db = new Database(context);
        DecimalFormat df = new DecimalFormat("#.##");


        ModelCart modelCart = dessertListCart.get(position);
        Model model = db.getDessertCartById(Integer.parseInt(modelCart.getIdd()));;

        //total+=Double.parseDouble(model.getPrice());

            holder.textName.setText(model.getName());
            holder.textPrice.setText(modelCart.getPrice());
            holder.textCount.setText(modelCart.getCount());
            Glide.with(context).load(model.getImg()).into(holder.imgDessert);

        //Toast.makeText(context, String.valueOf(total), Toast.LENGTH_SHORT).show();


        holder.imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDessertActivity.class);
                intent.putExtra("position",model.getId());
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });


        holder.imgDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newCartStatus = !model.getCart();
                model.setCart(newCartStatus);
                db.updateDessert(model);
                db.deleteDessertCart(modelCart);
                dessertListCart.remove(position);
                notifyItemRemoved(position);

            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count= Integer.parseInt(modelCart.getCount());
                count++;
                Double price = Double.valueOf(df.format(count*Double.valueOf(model.getPrice())));
                modelCart.setCount(String.valueOf(count));
                modelCart.setPrice(String.valueOf(price));
                db.updateDessertCart(modelCart);

                holder.textCount.setText(String.valueOf(count));
                holder.textPrice.setText(String.valueOf(price));

                //total+=sum;
                //Toast.makeText(context, String.valueOf(total), Toast.LENGTH_SHORT).show();

            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(modelCart.getCount());
                if (count>1){
                    count--;
                Double price = Double.valueOf(df.format(count*Double.valueOf(model.getPrice())));
                modelCart.setCount(String.valueOf(count));
                modelCart.setPrice(String.valueOf(price));
                db.updateDessertCart(modelCart);

                holder.textCount.setText(String.valueOf(count));
                holder.textPrice.setText(String.valueOf(price));
                }

//                DecimalFormat df = new DecimalFormat("#.##");
//                int count = Integer.parseInt(holder.textCount.getText().toString())-1;
//                Double sum = Double.valueOf(df.format(Double.parseDouble(model.getPrice())*count));
//                holder.textCount.setText(String.valueOf(count));
//                holder.textPrice.setText(String.valueOf(sum));
//                total+=sum;
//                Toast.makeText(context, String.valueOf(total), Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return dessertListCart.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{

        TextView textName,textPrice,textCount;
        ImageView imgDessert,imgDell,imgPlus,imgMinus;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);


            textName = itemView.findViewById(R.id.textView_item_cart_name);
            textPrice = itemView.findViewById(R.id.textView_item_cart_price);
            textCount = itemView.findViewById(R.id.textView_item_cart_count);
            imgDessert = itemView.findViewById(R.id.imageView_item_cart_img);
            imgDell = itemView.findViewById(R.id.imageViewimageView_item_cart_dell);
            imgPlus = itemView.findViewById(R.id.imageView_item_cart_plus);
            imgMinus = itemView.findViewById(R.id.imageView_item_cart_minus);


        }
    }



}



