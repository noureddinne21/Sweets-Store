package Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    private String uid;
    private String name;
    private String price;
    private String img;
    private String type;

    public Model(String uid, String name, String price, String type, String img) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.type = type;
        this.img = img;
    }

    public Model(String name, String price, String type, String img) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.img = img;
    }

    public Model(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Model() {}

    protected Model(Parcel in) {
        uid = in.readString();
        name = in.readString();
        price = in.readString();
        img = in.readString();
        type = in.readString();
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
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(img);
        dest.writeString(type);
    }

    // Getters and setters for the fields

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = id;
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


}