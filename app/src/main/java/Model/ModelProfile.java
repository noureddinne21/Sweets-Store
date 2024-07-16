package Model;

public class ModelProfile {
    String name,email,password;
    int id,numberPurchases;
    Double totalSpend;

    public ModelProfile(int id,String name, String email, String password, int numberPurchases, Double totalSpend) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.numberPurchases = numberPurchases;
        this.totalSpend = totalSpend;
    }

    public ModelProfile(String name, String email, String password, int numberPurchases, Double totalSpend) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberPurchases = numberPurchases;
        this.totalSpend = totalSpend;
    }

    public ModelProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberPurchases() {
        return numberPurchases;
    }

    public void setNumberPurchases(int numberPurchases) {
        this.numberPurchases = numberPurchases;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }
}
