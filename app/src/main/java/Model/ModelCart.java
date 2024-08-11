package Model;

public class ModelCart {

    private String uid;
    private int count;
    private String price;
    private String totalPrice;

    public ModelCart(String uid, int count,String price,String totalPrice) {
        this.uid = uid;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public ModelCart(String price, int count, String uid) {
        this.price = price;
        this.count = count;
        this.uid = uid;
    }

    public ModelCart(String uid, int count) {
        this.uid = uid;
        this.count = count;
    }

    public ModelCart() {
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
