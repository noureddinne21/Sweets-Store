package Model;

public class ModelCart {

    private int id;
    private String idd;
    private String count;
    private String price;
    private String totalPrice;

    public ModelCart(int id, String idd, String count,String price,String totalPrice) {
        this.id = id;
        this.idd = idd;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public ModelCart(String idd, String count,String price,String totalPrice) {
        this.idd = idd;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public ModelCart() {
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
