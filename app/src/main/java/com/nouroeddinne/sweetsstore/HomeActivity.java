package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Normalizer2;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Controlar.Adapter;
import Database.Database;
import Model.Model;
import Model.ModelCart;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView favourite,cart,search,profail;
    TextView textViewNotifFaverate,textViewNotifCart,textCapcake,textDounet,textCookies,textCandy;
    LinearLayout linearCapcake,linearCapcakewhite,linearDounet,linearDounetwhite,linearCookies,linearCookieswhite,linearCandy,linearCandywhite;
    RecyclerView recyclerView;
    static RecyclerView.Adapter adapter;
    static ArrayList<Model> dessertList,dessertListFavorate,dessertListCart;
     Database db;
     String type ="";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        favourite = findViewById(R.id.imageView_favourite);
        cart = findViewById(R.id.imageView_cart);
        search = findViewById(R.id.imageView_search);
        profail = findViewById(R.id.imageView_profaile);
        recyclerView = findViewById(R.id.recyclerView);

        textViewNotifFaverate = findViewById(R.id.textView_notif_faverate);
        textViewNotifCart = findViewById(R.id.textView_notif_cart);

        linearCapcake = findViewById(R.id.linear_capcake);
        linearDounet = findViewById(R.id.linear_dounet);
        linearCookies = findViewById(R.id.linear_cookies);
        linearCandy = findViewById(R.id.linear_candy);
        linearCapcakewhite = findViewById(R.id.linear_capcake_white);
        linearDounetwhite = findViewById(R.id.linear_dounet_white);
        linearCookieswhite = findViewById(R.id.linear_cookies_white);
        linearCandywhite = findViewById(R.id.linear_candy_white);

        linearCapcake.setBackgroundResource(R.drawable.null_background);
        linearCookies.setBackgroundResource(R.drawable.null_background);
        linearDounet.setBackgroundResource(R.drawable.null_background);
        linearCandy.setBackgroundResource(R.drawable.null_background);

        linearCapcakewhite.setBackgroundResource(R.drawable.null_background);
        linearCookieswhite.setBackgroundResource(R.drawable.null_background);
        linearDounetwhite.setBackgroundResource(R.drawable.null_background);
        linearCandywhite.setBackgroundResource(R.drawable.null_background);

        textCapcake = findViewById(R.id.textView21_capcake);
        textDounet = findViewById(R.id.textView22_dounet);
        textCookies = findViewById(R.id.textView31_cookies);
        textCandy = findViewById(R.id.textView32_candy);

        db = new Database(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        int show =0;
        if (show ==0){
            addItems();
            show++;
        }
        textNotif();

        dessertList=db.getAllDESSERT();
        adapter = new Adapter(this, dessertList);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                textNotif();
            }
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                textNotif();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        linearCapcake.setOnClickListener(this);
        linearDounet.setOnClickListener(this);
        linearCookies.setOnClickListener(this);
        linearCandy.setOnClickListener(this);

        favourite.setOnClickListener(HomeActivity.this);
        cart.setOnClickListener(this);
        search.setOnClickListener(this);
        profail.setOnClickListener(this);

//        Log.d("TAG", "dessert List : "+dessertList.size());
//        for (Model m: dessertList){
//            Log.d("TAG", "dessert List : "+m.getId()+" getName "+m.getName()+" getPrice "+m.getPrice()+" getType "+m.getType()+" getFavorite "+m.getFavorite()+" getCart "+m.getCart());
//        }
//
//        ArrayList<ModelCart> lc = db.getAllDESSERTCart();
//        Log.d("TAG", "dessert List Cart : "+lc.size());
//
//        for (ModelCart m: lc){
//            Log.d("TAG", "dessert List Cart : "+m.getId()+" getIdd "+m.getIdd()+" getPrice "+m.getPrice()+" getCount "+m.getCount()+" getTotalPrice "+m.getTotalPrice());
//        }


        }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imageView_favourite){
                Intent intent = new Intent(HomeActivity.this, Favourite_Dessert_Activity.class);
                startActivity(intent);
        } else if (v.getId() == R.id.imageView_cart) {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.imageView_search) {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.imageView_profaile) {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.linear_capcake) {

                type = "capcake";
                dessertList=db.getByTypeDESSERT(type);
                adapter = new Adapter(HomeActivity.this, dessertList);
                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        textNotif();
                    }
                });
                recyclerView.setAdapter(adapter);

                linearCapcake.setBackgroundResource(R.drawable.background_button_checkout);
                linearCookies.setBackgroundResource(R.drawable.null_background);
                linearDounet.setBackgroundResource(R.drawable.null_background);
                linearCandy.setBackgroundResource(R.drawable.null_background);

                linearCapcakewhite.setBackgroundResource(R.color.white);
                linearCookieswhite.setBackgroundResource(R.drawable.null_background);
                linearDounetwhite.setBackgroundResource(R.drawable.null_background);
                linearCandywhite.setBackgroundResource(R.drawable.null_background);

                textCapcake.setTextColor(ContextCompat.getColor(this, R.color.white));
                textCookies.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textDounet.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textCandy.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));


        }else if (v.getId() == R.id.linear_dounet) {

                type = "dounet";
                dessertList=db.getByTypeDESSERT(type);
            adapter = new Adapter(HomeActivity.this, dessertList);
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        textNotif();
                    }
                });
                recyclerView.setAdapter(adapter);

                linearCapcake.setBackgroundResource(R.drawable.null_background);
                linearCookies.setBackgroundResource(R.drawable.null_background);
                linearDounet.setBackgroundResource(R.drawable.background_button_checkout);
                linearCandy.setBackgroundResource(R.drawable.null_background);

                linearCapcakewhite.setBackgroundResource(R.drawable.null_background);
                linearCookieswhite.setBackgroundResource(R.drawable.null_background);
                linearDounetwhite.setBackgroundResource(R.color.white);
                linearCandywhite.setBackgroundResource(R.drawable.null_background);

                textCapcake.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textCookies.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textDounet.setTextColor(ContextCompat.getColor(this, R.color.white));
                textCandy.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));


        }else if (v.getId() == R.id.linear_cookies) {

                type = "cookies";
                dessertList=db.getByTypeDESSERT(type);
            adapter = new Adapter(HomeActivity.this, dessertList);
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        textNotif();
                    }
                });
                recyclerView.setAdapter(adapter);

                linearCapcake.setBackgroundResource(R.drawable.null_background);
                linearCookies.setBackgroundResource(R.drawable.background_button_checkout);
                linearDounet.setBackgroundResource(R.drawable.null_background);
                linearCandy.setBackgroundResource(R.drawable.null_background);

                linearCookieswhite.setBackgroundResource(R.color.white);
                linearCapcakewhite.setBackgroundResource(R.drawable.null_background);
                linearDounetwhite.setBackgroundResource(R.drawable.null_background);
                linearCandywhite.setBackgroundResource(R.drawable.null_background);

                textCapcake.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textCookies.setTextColor(ContextCompat.getColor(this, R.color.white));
                textDounet.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
                textCandy.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));

        }else if (v.getId() == R.id.linear_candy) {

                type = "candy";
                dessertList=db.getByTypeDESSERT(type);
            adapter = new Adapter(HomeActivity.this, dessertList);
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        textNotif();
                    }
                });
                recyclerView.setAdapter(adapter);

                linearCapcake.setBackgroundResource(R.drawable.null_background);
                linearCookies.setBackgroundResource(R.drawable.null_background);
                linearDounet.setBackgroundResource(R.drawable.null_background);
                linearCandy.setBackgroundResource(R.drawable.background_button_checkout);

                linearCandywhite.setBackgroundResource(R.color.white);
                linearCookieswhite.setBackgroundResource(R.drawable.null_background);
                linearDounetwhite.setBackgroundResource(R.drawable.null_background);
                linearCapcakewhite.setBackgroundResource(R.drawable.null_background);

            textCapcake.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
            textCandy.setTextColor(ContextCompat.getColor(this, R.color.white));
            textDounet.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));
            textCookies.setTextColor(ContextCompat.getColor(this, R.color.DarkPurpol));

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, String.valueOf(event.getAction()), Toast.LENGTH_LONG).show();
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }



    public void addItems(){

        db.addDessert(new Model("fresh squeezed mimosa","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2016/08/fresh-squeezed-mimosa-cupcakes-2-300x450.jpg",false,false));
        db.addDessert(new Model("blueberry lemon cupcakes","3.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2016/04/blueberry-lemon-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("homemade strawberry cupcakes","1.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2018/08/homemade-strawberry-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("smores brownie cupcakes","4.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2017/08/smores-brownie-cupcakes-3-300x300.jpg",false,false));
        db.addDessert(new Model("Strawberry Cupcakes with Creamy Strawberry Frosting","6.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2015/04/Strawberry-Cupcakes-with-Creamy-Strawberry-Frosting-2-300x300.jpg",false,false));
        db.addDessert(new Model("white chocolate-peppermint cupcakes","1.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2017/12/white-chocolate-peppermint-cupcakes-2-300x300.jpg",false,false));
        db.addDessert(new Model("delicious peppermint mocha cupcakes","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2013/11/delicious-peppermint-mocha-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("the-best lemon cupcakes","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2013/04/the-best-lemon-cupcakes-5-300x300.jpg",false,false));

        db.addDessert(new Model("glazed doughnuts homemade","9.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2016/05/glazed-doughnuts-homemade-2-300x300.jpg",false,false));
        db.addDessert(new Model("Baked Funfetti Donuts","19.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2014/01/Baked-Funfetti-Donuts.-These-taste-just-like-your-favorite-sprinkled-donuts-at-the-bakery.-And-theyre-so-simple-to-make-at-home-3-300x300.jpg",false,false));
        db.addDessert(new Model("homemade-doughnuts Cover them in a rich","3.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2014/04/My-favorite-homemade-doughnuts-Cover-them-in-a-rich-super-easy-chocolate-ganache-and-tons-of-rainbow-sprinkles.-This-is-a-recipe-to-hold-onto-300x300.jpg",false,false));
        db.addDessert(new Model("donuts","6.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2024/01/cookbook-recipes-latest-testing-5-300x300.jpg",false,false));
        db.addDessert(new Model("party donuts sallys baking addiction","8.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2016/02/party-donuts-sallys-baking-addiction-300x300.jpg",false,false));
        db.addDessert(new Model("lemon poppy seed donuts","5.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2016/09/lemon-poppy-seed-donuts-6-300x300.jpg",false,false));
        db.addDessert(new Model("pumpkin donuts","7.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2019/09/pumpkin-donuts-300x300.jpg",false,false));
        db.addDessert(new Model("apple cider doughnuts","2.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2018/09/apple-cider-doughnuts-300x300.jpg",false,false));
        db.addDessert(new Model("baked maple glazed-donuts","6.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2014/09/baked-maple-glazed-donuts-2-300x300.jpg",false,false));
        db.addDessert(new Model("apple cider doughnuts","7.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2018/09/apple-cider-doughnuts-300x300.jpg",false,false));
        db.addDessert(new Model("homemade doughnuts frostings","9.99","dounet","https://sallysbakingaddiction.com/wp-content/uploads/2016/07/homemade-doughnuts-frostings-2-300x300.jpg",false,false));

        db.addDessert(new Model("pumpkinsnickerdoodles","2.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2014/09/pumpkin-snickerdoodles-2-300x450.jpg",false,false));
        db.addDessert(new Model("flourless monster cookies","12.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2023/06/flourless-monster-cookies-2-300x450.jpg",false,false));
        db.addDessert(new Model("soft mm cookie bars","1.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2022/12/soft-mm-cookie-bars-300x450.jpg",false,false));
        db.addDessert(new Model("peppermint snowballs cookies","1.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2023/11/peppermint-snowballs-cookies-2-300x450.jpg",false,false));
        db.addDessert(new Model("cranberry orange icebox cookies","2.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2022/12/cranberry-orange-icebox-cookies-3-300x450.jpg",false,false));
        db.addDessert(new Model("pumpkin oatmeal cookies","6.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2016/09/pumpkin-oatmeal-cookies-2-300x450.jpg",false,false));
        db.addDessert(new Model("banana breakfast cookie chocolate chips","4.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2014/01/banana-breakfast-cookie-chocolate-chips-2-300x450.jpg",false,false));
        db.addDessert(new Model("hot cocoa cookies with marshmallow","7.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2023/11/hot-cocoa-cookies-with-marshmallow-300x450.jpg",false,false));
        db.addDessert(new Model("gf flourless peanut butter oatmeal cookies","9.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2014/03/gf-flourless-peanut-butter-oatmeal-cookies-2-300x450.jpg",false,false));
        db.addDessert(new Model("peanut butter chocolate swirl cookies","4.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2013/02/peanut-butter-chocolate-swirl-cookies-2-300x450.jpg",false,false));
        db.addDessert(new Model("nutella crinkles","6.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2023/12/nutella-crinkles-photo-2-300x450.jpg",false,false));
        db.addDessert(new Model("lemon-curd thumbprint cookies","2.99","cookies","https://sallysbakingaddiction.com/wp-content/uploads/2023/11/lemon-curd-thumbprint-cookies-300x450.jpg",false,false));

        db.addDessert(new Model("Chocolate Covered Pretzels","7.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2011/12/Chocolate-Covered-Pretzels-by-sallysbakingaddiction.com-3-300x450.jpg",false,false));
        db.addDessert(new Model("Pretzel MM Hugs","17.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2012/10/Pretzel-MM-Hugs-300x450.jpg",false,false));
        db.addDessert(new Model("cookies and cream puppy chow","4.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2013/07/cookies-and-cream-puppy-chow-4-300x450.jpg",false,false));
        db.addDessert(new Model("Dark Chocolate Key Lime Pie Truffles","6.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2015/03/Dark-Chocolate-Key-Lime-Pie-Truffles-2-300x450.jpg",false,false));
        db.addDessert(new Model("Peanut Butter MM Truffles","1.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2015/06/Peanut-Butter-MM-Truffles-5-300x450.jpg",false,false));
        db.addDessert(new Model("pumpkin spice truffles","6.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2015/10/pumpkin-spice-truffles-3-300x450.jpg",false,false));
        db.addDessert(new Model("sea salt vanilla caramels","6.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2016/10/sea-salt-vanilla-caramels-5-300x450.jpg",false,false));
        db.addDessert(new Model("peanut butter easter egg candies","1.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2022/03/peanut-butter-easter-egg-candies-2-300x450.jpg",false,false));
        db.addDessert(new Model("chocolate covered peanut butter balls","4.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2023/12/chocolate-covered-peanut-butter-balls-300x450.jpg",false,false));
        db.addDessert(new Model("chocolate cake pops recipe","2.99","candy","https://sallysbakingaddiction.com/wp-content/uploads/2018/02/chocolate-cake-pops-recipe-300x450.jpg",false,false));



    }


public void textNotif(){

    dessertListFavorate=db.getFavorateDESSERT();
    dessertListCart=db.getCartDESSERT();

//    for (Model m:dessertListCart){
//        Log.d("TAG", "textNotif: "+m.getName()+" "+m.getPrice()+" "+m.getCart());
//    }
    if (dessertListFavorate.size()>0){
        textViewNotifFaverate.setVisibility(View.VISIBLE);
        textViewNotifFaverate.setText(String.valueOf(dessertListFavorate.size()));
    }else {
        textViewNotifFaverate.setVisibility(View.GONE);
    }

    if (dessertListCart.size()>0){
        textViewNotifCart.setVisibility(View.VISIBLE);
        textViewNotifCart.setText(String.valueOf(dessertListCart.size()));
    }else {
        textViewNotifCart.setVisibility(View.GONE);
    }

}



}



