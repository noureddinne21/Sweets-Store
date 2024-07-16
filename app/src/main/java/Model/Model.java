package Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    private int id;
    private String name;
    private String price;
    private String img;
    private String type;
    private boolean favorite;
    private boolean cart;

    public Model(int id, String name, String price, String type, String img, boolean favorite, boolean cart) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.img = img;
        this.favorite = favorite;
        this.cart = cart;
    }

    public Model(String name, String price, String type, String img, boolean favorite, boolean cart) {
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

    public Model() {}

    protected Model(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readString();
        img = in.readString();
        type = in.readString();
        favorite = in.readByte() != 0;
        cart = in.readByte() != 0;
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(img);
        dest.writeString(type);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeByte((byte) (cart ? 1 : 0));
    }

    // Getters and setters for the fields

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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