package com.nouroeddinne.sweetsstore;

import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_EMAIL;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_ITEMS;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_NAME;
import static Utel.UtelsDB.SHAREDPREFERNCES_FILENAME_USER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import Database.DataBaseAccess;
import Model.Model;
import Model.ModelPager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener , FragmentQuickAlert.OnButtonLisner, FragmentQuickAlertExit.OnButtonExitLisner, FragmentQuickAlertExit.OnButtonCancelLisner {
    ImageView favourite, cart, search, profail;
    static TextView textViewNotifFaverate;
    static TextView textViewNotifCart;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    static DataBaseAccess db;

    ArrayList<Model> dessertList, CapcakeList, Dounetlist, CookiesList, CandyList;
    static ArrayList<Model> dessertListFavorate;
    static ArrayList<Model> dessertListCart;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesEmail;
    public static String EMAIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        favourite = findViewById(R.id.imageView_favourite);
        cart = findViewById(R.id.imageView_cart);
        search = findViewById(R.id.imageView_search);
        profail = findViewById(R.id.imageView_profaile);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viwepager);

        textViewNotifFaverate = findViewById(R.id.textView_notif_faverate);
        textViewNotifCart = findViewById(R.id.textView_notif_cart);

        db = DataBaseAccess.getInstance(this);

        sharedPreferences = getSharedPreferences(SHAREDPREFERNCES_FILENAME_USER, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_EMAIL, null)!=null) {
            EMAIL=sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_EMAIL, null);
        }


        sharedPreferences = getSharedPreferences(SHAREDPREFERNCES_FILENAME_ITEMS, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(SHAREDPREFERNCES_FILENAME_ITEMS, null)==null) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("addItems", "done");
            editor.apply();

            db.open();
            addItems();
            db.close();
        }


        loadData();
        offer();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addTab(new ModelPager("All", HomeFragment.newInstance("All", dessertList)));
        adapter.addTab(new ModelPager("Capcake", HomeFragment.newInstance("Capcake", CapcakeList)));
        adapter.addTab(new ModelPager("Dounet", HomeFragment.newInstance("Dounet", Dounetlist)));
        adapter.addTab(new ModelPager("Cookies", HomeFragment.newInstance("Cookies", CookiesList)));
        adapter.addTab(new ModelPager("Candy", HomeFragment.newInstance("Candy", CandyList)));

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(adapter.getTabName(position));
        }).attach();

        tabLayout.getTabAt(1).setIcon(R.drawable.cupcake);
        tabLayout.getTabAt(2).setIcon(R.drawable.donut);
        tabLayout.getTabAt(3).setIcon(R.drawable.cookies);
        tabLayout.getTabAt(4).setIcon(R.drawable.candy);

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d("TAG", "onTabSelected: " + tab.getText() + " " + tab.getPosition() + " " + tab.getId());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {}
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {}
//        });

        favourite.setOnClickListener(this);
        cart.setOnClickListener(this);
        search.setOnClickListener(this);
        profail.setOnClickListener(this);


    }


    private void loadData() {
        db.open();
        dessertList = db.getAllDESSERT();
        CapcakeList = db.getByTypeDESSERT("capcake");
        Dounetlist = db.getByTypeDESSERT("dounet");
        CookiesList = db.getByTypeDESSERT("cookies");
        CandyList = db.getByTypeDESSERT("candy");
        db.close();

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.imageView_favourite) {
            intent = new Intent(HomeActivity.this, Favourite_Dessert_Activity.class);
        } else if (v.getId() == R.id.imageView_cart) {
            intent = new Intent(HomeActivity.this, CartActivity.class);
        } else if (v.getId() == R.id.imageView_search) {
            intent = new Intent(HomeActivity.this, SearchActivity.class);
        } else if (v.getId() == R.id.imageView_profaile) {
            if (HomeActivity.EMAIL!=null){
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
            }else {
                Toast.makeText(HomeActivity.this, "login or creat acount!", Toast.LENGTH_SHORT).show();
            }
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            FragmentQuickAlertExit fragmentExit = FragmentQuickAlertExit.newInstance();
            fragmentExit.show(getSupportFragmentManager(),null);
            fragmentExit.setCancelable(false);

//            new AlertDialog.Builder(this)
//                    .setTitle("Exit")
//                    .setMessage("Are you sure you want to Exit?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", (dialog, which) -> finish())
//                    .setNegativeButton("No", null)
//                    .show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public static void textNotif() {
        db.open();
        dessertListFavorate = db.getFavorateDESSERT();
        dessertListCart = db.getCartDESSERT();
        db.close();

        if (dessertListFavorate.size() > 0) {
            textViewNotifFaverate.setVisibility(View.VISIBLE);
            textViewNotifFaverate.setText(String.valueOf(dessertListFavorate.size()));
        } else {
            textViewNotifFaverate.setVisibility(View.GONE);
        }

        if (dessertListCart.size() > 0) {
            textViewNotifCart.setVisibility(View.VISIBLE);
            textViewNotifCart.setText(String.valueOf(dessertListCart.size()));
        } else {
            textViewNotifCart.setVisibility(View.GONE);
        }
    }

    public void offer(){

        int n = sharedPreferences.getInt("my_integer_key", 0);;

        n+=1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("my_integer_key", n);
        editor.apply();

        if ((sharedPreferences.getInt("my_integer_key", 0)%3)==0){
            FragmentQuickAlert fragment2 = FragmentQuickAlert.newInstance("Info","Buy two, get one free ",String.valueOf(R.color.yalow),String.valueOf(R.drawable.offer));
            fragment2.show(getSupportFragmentManager(),null);
            fragment2.setCancelable(false);
        }
    }

    private void addItems() {
        db.addDessert(new Model("fresh squeezed mimosa","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2016/08/fresh-squeezed-mimosa-cupcakes-2-300x450.jpg",false,false));
        db.addDessert(new Model("blueberry lemon cupcakes","3.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2016/04/blueberry-lemon-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("homemade strawberry cupcakes","1.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2018/08/homemade-strawberry-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("smores brownie cupcakes","4.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2017/08/smores-brownie-cupcakes-3-300x300.jpg",false,false));
        db.addDessert(new Model("Strawberry Cupcakes with Creamy Strawberry Frosting","6.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2015/04/Strawberry-Cupcakes-with-Creamy-Strawberry-Frosting-2-300x300.jpg",false,false));
        db.addDessert(new Model("white chocolate peppermint cupcakes","1.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2017/12/white-chocolate-peppermint-cupcakes-2-300x300.jpg",false,false));
        db.addDessert(new Model("delicious peppermint mocha cupcakes","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2013/11/delicious-peppermint-mocha-cupcakes-300x300.jpg",false,false));
        db.addDessert(new Model("lemon cupcakes","5.99","capcake","https://sallysbakingaddiction.com/wp-content/uploads/2013/04/the-best-lemon-cupcakes-5-300x300.jpg",false,false));

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

    @Override
    public void onCklick() {
        //Do something
    }

    @Override
    public void onCklickExit() {
        finishAffinity();
        System.exit(0);
    }

    @Override
    public void onCklickCancel() {
        // Do nothing
    }
}