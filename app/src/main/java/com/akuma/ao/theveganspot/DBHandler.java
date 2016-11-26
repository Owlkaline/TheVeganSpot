package com.akuma.ao.theveganspot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akuma on 26/11/16.
 */

public class DBHandler extends SQLiteOpenHelper {
    protected static final int DATABASE_VERSION = 1;

    protected static final String DATABASE_NAME = "VeganInfo";

    protected static final String TABLE_FOOD = "Food";

    protected static final String KEY_ID = "id";
    protected static final String KEY_NAME = "name";

    public DBHandler(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_FOOD_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FOOD, new String[] { KEY_ID, KEY_NAME }, KEY_ID + "=?",
                                 new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Food food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return food;
    }

    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<Food>();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        return foodList;
    }

    public int getFoodCount() {
        String countQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        return db.update(TABLE_FOOD, values, KEY_ID + " = ?", new String[] { String.valueOf(food.getId()) });
    }

    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, KEY_ID + " = ?", new String[] { String.valueOf(food.getId()) });
        db.close();
    }

    public Food findFood(String foodname) {
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + KEY_NAME +
                " = \"" + foodname + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Food food = new Food();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            food.setId(Integer.parseInt(cursor.getString(0)));
            food.setName(cursor.getString(1));
            cursor.close();
        } else {
            food = null;
        }
        db.close();
        return food;
    }
}
