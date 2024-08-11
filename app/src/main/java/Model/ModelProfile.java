package Model;

public class ModelProfile {
    String name,email,password,gender,uid;
    int numberPurchases;
    Double totalSpend;

    public ModelProfile(String name, String email, String password, String gender,String uid, int numberPurchases, Double totalSpend) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.uid = uid;
        this.numberPurchases = numberPurchases;
        this.totalSpend = totalSpend;
    }

    public ModelProfile(String name, String email, String password, String gender, int numberPurchases, Double totalSpend) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.numberPurchases = numberPurchases;
        this.totalSpend = totalSpend;
    }

//    public ModelProfile(String uid, String name, String email, String password, int numberPurchases, Double totalSpend) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.uid = uid;
//        this.numberPurchases = numberPurchases;
//        this.totalSpend = totalSpend;
//    }

    public ModelProfile(String name, String email, String password, int numberPurchases, Double totalSpend) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberPurchases = numberPurchases;
        this.totalSpend = totalSpend;
    }

    public ModelProfile() {
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = id;
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
