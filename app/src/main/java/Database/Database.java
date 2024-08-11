//package Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//
//import Model.Model;
//import Utel.UtelsDB;
//import Model.ModelCart;
//public class Database extends SQLiteOpenHelper {
//
//    public Database(@Nullable Context context) {
//        super(context, UtelsDB.NAME_DATABASE, null, UtelsDB.VERSTION_DATABASE);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//
//        String CREAT_TABLE="CREATE TABLE "+UtelsDB.DESSERT_TABLE+" ("+
//                UtelsDB.KEY_ID+" INTEGER PRIMARY KEY,"+
//                UtelsDB.KEY_NAME+" TEXT,"+
//                UtelsDB.KEY_PRICE+ " TEXT,"+
//                UtelsDB.KEY_TYPE+ " TEXT,"+
//                UtelsDB.KEY_IMG+ " TEXT,"+
//                UtelsDB.KEY_FAVORITE+ " TEXT,"+
//                UtelsDB.KEY_CART+"  TEXT)";
//        db.execSQL(CREAT_TABLE);
//
//        CREAT_TABLE="CREATE TABLE "+ UtelsDB.DESSERT_TABLE_CART+" ("+
//                UtelsDB.KEY_ID_CART+" INTEGER PRIMARY KEY,"+
//                UtelsDB.KEY_IDD_CART+" TEXT,"+
//                UtelsDB.KEY_COUNT_CART+" TEXT,"+
//                UtelsDB.KEY_PRICE_CART+" TEXT,"+
//                UtelsDB.KEY_TOTAL_PRICE_ITEM_CART+"  TEXT)";
//        db.execSQL(CREAT_TABLE);
//
//        CREAT_TABLE="CREATE TABLE "+ UtelsDB.TABLE_PROFILE+" ("+
//                UtelsDB.KEY_ID_PROFILE+" INTEGER PRIMARY KEY,"+
//                UtelsDB.KEY_NAME_PROFILE+" TEXT,"+
//                UtelsDB.KEY_EMAIL_PROFILE+" TEXT,"+
//                UtelsDB.KEY_PASSWORD_PROFILE+" TEXT,"+
//                UtelsDB.KEY_NUMBERPURCHASES_PROFILE+" INTEGER,"+
//                UtelsDB.KEY_TOTALSPEND_PROFILE+" DOUBLE)";
//        db.execSQL(CREAT_TABLE);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+UtelsDB.DESSERT_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS "+UtelsDB.DESSERT_TABLE_CART);
//        db.execSQL("DROP TABLE IF EXISTS "+UtelsDB.TABLE_PROFILE);
//        onCreate(db);
//    }
//
////
////    public void addDessert(Model model){
////
////        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(UtelsDB.KEY_NAME, model.getName());
////        contentValues.put(UtelsDB.KEY_PRICE, model.getPrice());
////        contentValues.put(UtelsDB.KEY_TYPE, model.getType());
////        contentValues.put(UtelsDB.KEY_IMG, model.getImg());
////        contentValues.put(UtelsDB.KEY_FAVORITE, model.getFavorite());
////        contentValues.put(UtelsDB.KEY_CART, model.getCart());
////        sqLiteDatabase.insert(UtelsDB.DESSERT_TABLE,null,contentValues);
////        sqLiteDatabase.close();
////
////    }
////
////
////    public Model getDessertById(int id){
////
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
////                new String[]{UtelsDB.KEY_ID, UtelsDB.KEY_NAME, UtelsDB.KEY_PRICE, UtelsDB.KEY_TYPE, UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART}, UtelsDB.KEY_ID+"=?",
////                new String[]{String.valueOf(id)},
////                null,null,null,null);
////
////        if (cursor != null && cursor.moveToFirst()) {
////            Model model = new Model(
////                    Integer.parseInt(cursor.getString(0)),
////                    cursor.getString(1),
////                    cursor.getString(2),
////                    cursor.getString(3),
////                    cursor.getString(4),
////                    Boolean.parseBoolean(cursor.getString(5)),
////                    Boolean.parseBoolean(cursor.getString(6)));
////            cursor.close();
////            return model;
////        }
////        if (cursor != null) {
////            cursor.close();
////        }
////        return null;
////
////    }
////
////
////    public ArrayList<Model> getAllDESSERT(){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<Model> dessertList = new ArrayList<Model>();
////        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE;
////        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
////
////        if (cursor.moveToFirst())
////            do {
////                Model model = new Model();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setName(cursor.getString(1));
////                model.setPrice(cursor.getString(2));
////                model.setImg(cursor.getString(3));
////                model.setImg(cursor.getString(4));
////                model.setFavorite(Boolean.parseBoolean(cursor.getString(5)));
////                model.setCart(Boolean.parseBoolean(cursor.getString(6)));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////
////    public int updateDessert(Model model){
////        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(UtelsDB.KEY_NAME, model.getName());
////        contentValues.put(UtelsDB.KEY_PRICE, model.getPrice());
////        contentValues.put(UtelsDB.KEY_TYPE, model.getType());
////        contentValues.put(UtelsDB.KEY_IMG, model.getImg());
////        contentValues.put(UtelsDB.KEY_FAVORITE, String.valueOf(model.getFavorite()));
////        contentValues.put(UtelsDB.KEY_CART, String.valueOf(model.getCart()));
////
////        int result = sqLiteDatabase.update(UtelsDB.DESSERT_TABLE,contentValues,UtelsDB.KEY_ID+"=?",new String[]{String.valueOf(model.getId())});
////        sqLiteDatabase.close();
////        return result;
////    }
////
////
////    public ArrayList<Model> getByTypeDESSERT(String s){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<Model> dessertList = new ArrayList<Model>();
////
////        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
////                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
////                UtelsDB.KEY_TYPE + "=?",
////                new String[]{String.valueOf(s)}, null, null, null, null);
////
////        if (cursor.moveToFirst())
////            do {
////                Model model = new Model();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setName(cursor.getString(1));
////                model.setPrice(cursor.getString(2));
////                model.setImg(cursor.getString(3));
////                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
////                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////    public int deleteDessert(Model model){
////        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
////        int result = sqLiteDatabase.delete(UtelsDB.DESSERT_TABLE,UtelsDB.KEY_ID+"=?",new String[]{String.valueOf(model.getId())});
////        sqLiteDatabase.close();
////        return result;
////    }
////
////
////    public int NumDessert(){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE;
////        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
////        return cursor.getCount();
////    }
////
////
////
////    public ArrayList<Model> getFavorateDESSERT(){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<Model> dessertList = new ArrayList<Model>();
////
////        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
////                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
////                UtelsDB.KEY_FAVORITE + "=?",
////                new String[]{String.valueOf("true")}, null, null, null, null);
////
////        if (cursor.moveToFirst())
////            do {
////                Model model = new Model();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setName(cursor.getString(1));
////                model.setPrice(cursor.getString(2));
////                model.setImg(cursor.getString(3));
////                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
////                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////
////    public ArrayList<Model> getCartDESSERT(){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<Model> dessertList = new ArrayList<Model>();
////
////        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
////                new String[]{UtelsDB.KEY_ID,UtelsDB.KEY_NAME,UtelsDB.KEY_PRICE,UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART},
////                UtelsDB.KEY_CART + "=?",
////                new String[]{String.valueOf("true")}, null, null, null, null);
////
////        if (cursor.moveToFirst())
////            do {
////                Model model = new Model();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setName(cursor.getString(1));
////                model.setPrice(cursor.getString(2));
////                model.setImg(cursor.getString(3));
////                model.setFavorite(Boolean.parseBoolean(cursor.getString(4)));
////                model.setCart(Boolean.parseBoolean(cursor.getString(5)));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////
////    public ArrayList<Model> searchDESSERT(String s){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<Model> dessertList = new ArrayList<Model>();
////
////        String query = "SELECT * FROM "+UtelsDB.DESSERT_TABLE+" WHERE "+ UtelsDB.KEY_NAME +" LIKE '%" + s + "%'";
////
////
////        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
////
////        if (cursor.moveToFirst())
////            do {
////                Model model = new Model();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setName(cursor.getString(1));
////                model.setPrice(cursor.getString(2));
////                model.setImg(cursor.getString(3));
////                model.setImg(cursor.getString(4));
////                model.setFavorite(Boolean.parseBoolean(cursor.getString(5)));
////                model.setCart(Boolean.parseBoolean(cursor.getString(6)));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////
////
////    public void addDessertCart(ModelCart model){
////
////        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(UtelsDB.KEY_IDD_CART, model.getIdd());
////        contentValues.put(UtelsDB.KEY_COUNT_CART, model.getCount());
////        contentValues.put(UtelsDB.KEY_PRICE_CART, model.getPrice());
////        contentValues.put(UtelsDB.KEY_TOTAL_PRICE_ITEM_CART, model.getTotalPrice());
////        sqLiteDatabase.insert(UtelsDB.DESSERT_TABLE_CART,null,contentValues);
////        sqLiteDatabase.close();
////
////    }
////
////
////    public ArrayList<ModelCart> getAllDESSERTCart(){
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        ArrayList<ModelCart> dessertList = new ArrayList<ModelCart>();
////        String getAll = "SELECT * FROM "+UtelsDB.DESSERT_TABLE_CART;
////        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
////
////        if (cursor.moveToFirst())
////            do {
////                ModelCart model = new ModelCart();
////                model.setId(Integer.parseInt(cursor.getString(0)));
////                model.setIdd(cursor.getString(1));
////                model.setCount(cursor.getString(2));
////                model.setPrice(cursor.getString(3));
////                model.setTotalPrice(cursor.getString(4));
////                dessertList.add(model);
////            }while (cursor.moveToNext());
////
////        return dessertList;
////
////    }
////
////
////    public Model getDessertCartById(int id){
////
////        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
////        Cursor cursor = sqLiteDatabase.query(UtelsDB.DESSERT_TABLE,
////                new String[]{UtelsDB.KEY_ID, UtelsDB.KEY_NAME, UtelsDB.KEY_PRICE, UtelsDB.KEY_TYPE, UtelsDB.KEY_IMG,UtelsDB.KEY_FAVORITE,UtelsDB.KEY_CART}, UtelsDB.KEY_ID+" =? AND "+ UtelsDB.KEY_CART+" =? ",
////                new String[]{String.valueOf(id),"true"},
////                null,null,null,null);
////
////        if (cursor != null && cursor.moveToFirst()) {
////            Model model = new Model(
////                    Integer.parseInt(cursor.getString(0)),
////                    cursor.getString(1),
////                    cursor.getString(2),
////                    cursor.getString(3),
////                    cursor.getString(4),
////                    Boolean.parseBoolean(cursor.getString(5)),
////                    Boolean.parseBoolean(cursor.getString(6)));
////            cursor.close();
////            return model;
////        }
////        if (cursor != null) {
////            cursor.close();
////        }
////        return null;
////
////    }
////
////
////    public int updateDessertCart(ModelCart model){
////        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(UtelsDB.KEY_IDD_CART, model.getIdd());
////        contentValues.put(UtelsDB.KEY_COUNT_CART, model.getCount());
////        contentValues.put(UtelsDB.KEY_PRICE_CART, model.getPrice());
////        contentValues.put(UtelsDB.KEY_TOTAL_PRICE_ITEM_CART, model.getTotalPrice());
////
////        int result = sqLiteDatabase.update(UtelsDB.DESSERT_TABLE_CART,contentValues,UtelsDB.KEY_ID_CART+"=?",new String[]{String.valueOf(model.getId())});
////        sqLiteDatabase.close();
////        return result;
////    }
////
////
////
////    public int deleteDessertCart(ModelCart model){
////        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
////        int result = sqLiteDatabase.delete(UtelsDB.DESSERT_TABLE_CART,UtelsDB.KEY_ID_CART+"=?",new String[]{String.valueOf(model.getId())});
////        sqLiteDatabase.close();
////        return result;
////    }
////
////
////
////
////
////
////
////
////
////
////
////
////
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
