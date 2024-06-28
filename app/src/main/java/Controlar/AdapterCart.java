package Controlar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

    private Context context;
    private ArrayList<ModelCart> dessertListCart;
    private Database db ;
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
        int currentPosition =holder.getAdapterPosition();

        ModelCart modelCart = dessertListCart.get(currentPosition);
        Model model = db.getDessertCartById(Integer.parseInt(modelCart.getIdd()));;

        holder.textName.setText(shorterWord(model.getName(),25));
        Glide.with(context).load(model.getImg()).into(holder.imgDessert);

        holder.textPrice.setText(modelCart.getPrice());
        holder.textCount.setText(modelCart.getCount());

        holder.imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDessertActivity.class);
                intent.putExtra("position",model.getId());
                context.startActivity(intent);
            }
        });


        holder.imgDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentPosition =holder.getAdapterPosition();
                boolean newCartStatus = !model.getCart();
                model.setCart(newCartStatus);
                db.updateDessert(model);
                db.deleteDessertCart(modelCart);
                dessertListCart.remove(currentPosition);
                notifyItemRemoved(currentPosition);

            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count= Integer.parseInt(modelCart.getCount());
                count++;
                Double price = Double.valueOf(count*Double.valueOf(model.getPrice()));

                modelCart.setCount(String.valueOf(count));
                modelCart.setTotalPrice(String.valueOf(price));
                db.updateDessertCart(modelCart);
                notifyDataSetChanged();

                holder.textCount.setText(String.valueOf(count));
                holder.textPrice.setText(String.valueOf(price));

            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count= Integer.parseInt(modelCart.getCount());
                if (count>1){
                    count--;
                    Double price = Double.valueOf(count*Double.valueOf(model.getPrice()));
                    modelCart.setCount(String.valueOf(count));
                    modelCart.setTotalPrice(String.valueOf(price));
                    db.updateDessertCart(modelCart);
                    notifyDataSetChanged();

                    holder.textCount.setText(String.valueOf(count));
                    holder.textPrice.setText(String.valueOf(price));

                }
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
            textPrice = itemView.findViewById(R.id.textView_item_cart_priceItem);
            textCount = itemView.findViewById(R.id.textView_item_cart_counts);
            imgDessert = itemView.findViewById(R.id.imageView_item_cart_img);
            imgDell = itemView.findViewById(R.id.imageViewimageView_item_cart_dell);
            imgPlus = itemView.findViewById(R.id.imageView_item_cart_plus);
            imgMinus = itemView.findViewById(R.id.imageView_item_cart_minus);

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



