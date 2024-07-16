package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Model;
import Utel.UtelsDB;
import Model.ModelCart;
import Model.ModelProfile;
public class DataBaseAccess {

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper sqLiteOpenHelper;
    public static DataBaseAccess instance;

    private DataBaseAccess(Context context){
        this.sqLiteOpenHelper = new Database(context);
    }

    public static DataBaseAccess getInstance(Context context){
        if (instance==null){
            return new DataBaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.sqLiteDatabase = this.sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        if (this.sqLiteDatabase!=null){
            this.sqLiteDatabase.close();
        }
    }






    public void addDessert(Model model){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_NAME, model.getName());
        contentValues.put(UtelsDB.KEY_PRICE, model.getPrice());
        contentValues.put(UtelsDB.KEY_TYPE, model.getType());
        contentValues.put(UtelsDB.KEY_IMG, model.getImg());
        contentValues.put(UtelsDB.KEY_FAVORITE, model.getFavorite());
        contentValues.put(UtelsDB.KEY_CART, model.getCart());
        sqLiteDatabase.insert(UtelsDB.DESSERT_TABLE,null,contentValues);

    }


    public Model getDessertById(int id){

        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
                new String[]{UtelsDB.KEY_ID, UtelsDB.KEY_NAME, UtelsDB.KEY_PRICE, UtelsDB.KEY_TYPE, UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART}, UtelsDB.KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);

        if (cursor != null && cursor.moveToFirst()) {
            Model model = new Model(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    Boolean.parseBoolean(cursor.getString(5)),
                    Boolean.parseBoolean(cursor.getString(6)));
            cursor.close();
            return model;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;

    }


    public ArrayList<Model> getAllDESSERT(){
        ArrayList<Model> dessertList = new ArrayList<Model>();
        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setType(cursor.getString(3));
                model.setImg(cursor.getString(4));
                model.setFavorite(Boolean.parseBoolean(cursor.getString(5)));
                model.setCart(Boolean.parseBoolean(cursor.getString(6)));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }


    public int updateDessert(Model model){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_NAME, model.getName());
        contentValues.put(UtelsDB.KEY_PRICE, model.getPrice());
        contentValues.put(UtelsDB.KEY_TYPE, model.getType());
        contentValues.put(UtelsDB.KEY_IMG, model.getImg());
        contentValues.put(UtelsDB.KEY_FAVORITE, String.valueOf(model.getFavorite()));
        contentValues.put(UtelsDB.KEY_CART, String.valueOf(model.getCart()));

        int result = sqLiteDatabase.update(UtelsDB.DESSERT_TABLE,contentValues,UtelsDB.KEY_ID+"=?",new String[]{String.valueOf(model.getId())});
        return result;
    }


    public ArrayList<Model> getByTypeDESSERT(String s){
        ArrayList<Model> dessertList = new ArrayList<Model>();

        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
                UtelsDB.KEY_TYPE + "=?",
                new String[]{String.valueOf(s)}, null, null, null, null);

        if (cursor.moveToFirst())
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setImg(cursor.getString(3));
                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }

    public int deleteDessert(Model model){
        int result = sqLiteDatabase.delete(UtelsDB.DESSERT_TABLE,UtelsDB.KEY_ID+"=?",new String[]{String.valueOf(model.getId())});
        return result;
    }


    public int NumDessert(){
        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
        return cursor.getCount();
    }



    public ArrayList<Model> getFavorateDESSERT(){
        ArrayList<Model> dessertList = new ArrayList<Model>();

        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
                UtelsDB.KEY_FAVORITE + "=?",
                new String[]{String.valueOf("true")}, null, null, null, null);

        if (cursor.moveToFirst())
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setImg(cursor.getString(3));
                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }


    public ArrayList<Model> getCartDESSERT(){
        ArrayList<Model> dessertList = new ArrayList<Model>();

        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
                UtelsDB.KEY_CART + "=?",
                new String[]{String.valueOf("true")}, null, null, null, null);

        if (cursor.moveToFirst())
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setImg(cursor.getString(3));
                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }


    public ArrayList<Model> searchDESSERT(String s){
        ArrayList<Model> dessertList = new ArrayList<Model>();

        String query = "SELECT * FROM "+UtelsDB.DESSERT_TABLE+" WHERE "+ UtelsDB.KEY_NAME +" LIKE '%" + s + "%'";


        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setImg(cursor.getString(3));
                model.setImg(cursor.getString(4));
                model.setFavorite(Boolean.parseBoolean(cursor.getString(5)));
                model.setCart(Boolean.parseBoolean(cursor.getString(6)));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }



    public void addDessertCart(ModelCart model){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_IDD_CART, model.getIdd());
        contentValues.put(UtelsDB.KEY_COUNT_CART, model.getCount());
        contentValues.put(UtelsDB.KEY_PRICE_CART, model.getPrice());
        contentValues.put(UtelsDB.KEY_TOTAL_PRICE_ITEM_CART, model.getTotalPrice());
        sqLiteDatabase.insert(UtelsDB.DESSERT_TABLE_CART,null,contentValues);

    }


    public ArrayList<ModelCart> getAllDESSERTCart(){
        ArrayList<ModelCart> dessertList = new ArrayList<ModelCart>();
        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE_CART;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())
            do {
                ModelCart model = new ModelCart();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setIdd(cursor.getString(1));
                model.setCount(cursor.getString(2));
                model.setPrice(cursor.getString(3));
                model.setTotalPrice(cursor.getString(4));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }


    public Model getDessertCartById(int id){

        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
                new String[]{UtelsDB.KEY_ID, UtelsDB.KEY_NAME, UtelsDB.KEY_PRICE, UtelsDB.KEY_TYPE, UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART}, UtelsDB.KEY_ID+" =? AND "+ UtelsDB.KEY_CART+" =? ",
                new String[]{String.valueOf(id),"true"},
                null,null,null,null);

        if (cursor != null && cursor.moveToFirst()) {
            Model model = new Model(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    Boolean.parseBoolean(cursor.getString(5)),
                    Boolean.parseBoolean(cursor.getString(6)));
            cursor.close();
            return model;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;

    }


    public int updateDessertCart(ModelCart model){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_IDD_CART, model.getIdd());
        contentValues.put(UtelsDB.KEY_COUNT_CART, model.getCount());
        contentValues.put(UtelsDB.KEY_PRICE_CART, model.getPrice());
        contentValues.put(UtelsDB.KEY_TOTAL_PRICE_ITEM_CART, model.getTotalPrice());

        int result = sqLiteDatabase.update(UtelsDB.DESSERT_TABLE_CART,contentValues,UtelsDB.KEY_ID_CART+"=?",new String[]{String.valueOf(model.getId())});
        return result;
    }



    public int deleteDessertCart(ModelCart model){
        int result = sqLiteDatabase.delete(UtelsDB.DESSERT_TABLE_CART,UtelsDB.KEY_ID_CART+"=?",new String[]{String.valueOf(model.getId())});
        return result;
    }


    public String addProfile(ModelProfile model){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_NAME_PROFILE, model.getName());
        contentValues.put(UtelsDB.KEY_EMAIL_PROFILE, model.getEmail());
        contentValues.put(UtelsDB.KEY_PASSWORD_PROFILE, model.getPassword());
        contentValues.put(UtelsDB.KEY_NUMBERPURCHASES_PROFILE, model.getNumberPurchases());
        contentValues.put(UtelsDB.KEY_TOTALSPEND_PROFILE, model.getTotalSpend());
        String n = String.valueOf(sqLiteDatabase.insert(UtelsDB.TABLE_PROFILE,null,contentValues));

        return n;
    }


    public ModelProfile getPerson(ModelProfile profile){

        Cursor cursor = sqLiteDatabase.query(UtelsDB.TABLE_PROFILE,
                new String[]{UtelsDB.KEY_ID_PROFILE, UtelsDB.KEY_NAME_PROFILE,UtelsDB.KEY_EMAIL_PROFILE,UtelsDB.KEY_PASSWORD_PROFILE,UtelsDB.KEY_NUMBERPURCHASES_PROFILE,UtelsDB.KEY_TOTALSPEND_PROFILE}, UtelsDB.KEY_ID_PROFILE+"=?",
                new String[]{String.valueOf(profile.getId())},
                null,null,null,null);

        if (cursor != null && cursor.moveToFirst()) {
            ModelProfile p = new ModelProfile(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    Double.parseDouble(cursor.getString(5)));
            cursor.close();
            return p;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public ModelProfile getPersonByEmail(String email){

        Cursor cursor = sqLiteDatabase.query(UtelsDB.TABLE_PROFILE,
                new String[]{UtelsDB.KEY_ID_PROFILE, UtelsDB.KEY_NAME_PROFILE, UtelsDB.KEY_EMAIL_PROFILE, UtelsDB.KEY_PASSWORD_PROFILE, UtelsDB.KEY_NUMBERPURCHASES_PROFILE,UtelsDB.KEY_TOTALSPEND_PROFILE}, UtelsDB.KEY_EMAIL_PROFILE+"=?",
                new String[]{email},
                null,null,null,null);

        if (cursor != null && cursor.moveToFirst()) {
            ModelProfile p = new ModelProfile(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getDouble(5));
            cursor.close();
            return p;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public int updatePerson(ModelProfile model){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsDB.KEY_NAME_PROFILE, model.getName());
        contentValues.put(UtelsDB.KEY_EMAIL_PROFILE, model.getEmail());
        contentValues.put(UtelsDB.KEY_PASSWORD_PROFILE, model.getPassword());
        contentValues.put(UtelsDB.KEY_NUMBERPURCHASES_PROFILE, model.getNumberPurchases());
        contentValues.put(UtelsDB.KEY_TOTALSPEND_PROFILE,model.getTotalSpend());

        int result = sqLiteDatabase.update(UtelsDB.TABLE_PROFILE,contentValues,UtelsDB.KEY_ID_PROFILE+"=?",new String[]{String.valueOf(model.getId())});
        return result;
    }


    public boolean isEmailExist(String email) {
//        if (sqLiteDatabase == null) {
//            throw new IllegalStateException("Database not initialized");
//        }

        if (sqLiteDatabase != null){
            Cursor cursor = sqLiteDatabase.query(UtelsDB.TABLE_PROFILE,
                    new String[]{UtelsDB.KEY_ID_PROFILE, UtelsDB.KEY_NAME_PROFILE, UtelsDB.KEY_EMAIL_PROFILE, UtelsDB.KEY_PASSWORD_PROFILE, UtelsDB.KEY_NUMBERPURCHASES_PROFILE, UtelsDB.KEY_TOTALSPEND_PROFILE},
                    UtelsDB.KEY_EMAIL_PROFILE + "=?", new String[]{email}, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                cursor.close();
                return true;
            }
            if (cursor != null) {
                cursor.close();
            }
            return false;
        }
        return false;
    }


















}
