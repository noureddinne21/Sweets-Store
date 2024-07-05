package Model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

@SuppressLint("ParcelCreator")
public class Model {

    private int id;
    private String name;
    private String price;
    private String img;
    private String type;
    private boolean favorite;
    private boolean cart;



    public Model(int id, String name, String price, String type, String img,boolean favorite,boolean cart) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.img = img;
        this.favorite = favorite;
        this.cart = cart;
    }

    public Model(String name, String price, String type,String img,boolean favorite,boolean cart) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.img = img;
        this.favorite = favorite;
        this.cart = cart;

    }

    public Model(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Model() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

}
