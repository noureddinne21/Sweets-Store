package Model;

public class ModelCart {

    private int id;
    private String idd;
    private String price;
    private String count;

    public ModelCart(int id, String idd, String price, String count) {
        this.id = id;
        this.idd = idd;
        this.price = price;
        this.count = count;
    }

    public ModelCart(String idd, String price, String count) {
        this.idd = idd;
        this.price = price;
        this.count = count;
    }

    public ModelCart() {
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
