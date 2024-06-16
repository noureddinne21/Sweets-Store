package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Model.ModelCart;
import Utel.UtelsCart;

public class DatabaseCart extends SQLiteOpenHelper {
    public DatabaseCart(@Nullable Context context) {
        super(context, UtelsCart.NAME_DATABASE_CART, null, UtelsCart.VERSTION_DATABASE_CART);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAT_TABLE="CREATE TABLE "+ UtelsCart.DESSERT_TABLE_CART+" ("+
                UtelsCart.KEY_ID_CART+" INTEGER PRIMARY KEY,"+
                UtelsCart.KEY_IDD_CART+" TEXT,"+
                UtelsCart.KEY_COUNT_CART+" TEXT,"+
                UtelsCart.KEY_PRICE_CART+"  TEXT)";
        db.execSQL(CREAT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+UtelsCart.DESSERT_TABLE_CART);
        onCreate(db);
    }



    public void addDessertCart(ModelCart model){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtelsCart.KEY_IDD_CART, model.getIdd());
        contentValues.put(UtelsCart.KEY_PRICE_CART, model.getPrice());
        contentValues.put(UtelsCart.KEY_COUNT_CART, model.getCount());
        sqLiteDatabase.insert(UtelsCart.DESSERT_TABLE_CART,null,contentValues);
        sqLiteDatabase.close();

    }


    public ArrayList<ModelCart> getAllDESSERTCart(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<ModelCart> dessertList = new ArrayList<ModelCart>();
        String getAll = "SELECT * FROM "+UtelsCart.DESSERT_TABLE_CART;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())
            do {
                ModelCart model = new ModelCart();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setIdd(cursor.getString(1));
                model.setPrice(cursor.getString(2));
                model.setCount(cursor.getString(3));
                dessertList.add(model);
            }while (cursor.moveToNext());

        return dessertList;

    }



    public boolean checkTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor cursor = db.rawQuery("SELECT * FROM UtelsCart.NAME_DATABASE_CART WHERE type = ? AND name = ?", new String[]{"table", UtelsCart.DESSERT_TABLE_CART});
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+UtelsCart.NAME_DATABASE_CART+" WHERE type = ? AND name = ?", new String[]{"table", UtelsCart.DESSERT_TABLE_CART});

        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        }
        return false;
    }


    public ArrayList<String> getAllTables() {
        ArrayList<String> tables = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    tables.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
        }
        return tables;
    }












}
