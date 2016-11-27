package com.akuma.ao.theveganspot;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akuma on 26/11/16.
 */

public class DBHandler extends SQLiteOpenHelper {
    protected static final int DATABASE_VERSION = 3;

    protected static final String DATABASE_NAME = "VeganInfo";

    protected static final String TABLE_FOOD = "Food";

    protected static final String KEY_ID = "id";
    protected static final String KEY_BRAND_ID = "brand_id";
    protected static final String KEY_TYPE_ID = "type_id";
    protected static final String KEY_NAME = "name";
    protected static final String KEY_INGREDIENTS = "ingredients";

    protected static final String TABLE_BRAND = "Brand";
    protected static final String TABLE_TYPE = "Type";
    protected static final String KEY_TYPE = "type";

    public DBHandler(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_BRAND_ID + " INTEGER, " + KEY_TYPE_ID +
                " INTEGER, " + KEY_NAME + " TEXT, " + KEY_INGREDIENTS + " TEXT)";
        String CREATE_BRAND_TABLE = "CREATE TABLE " + TABLE_BRAND + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        String CREATE_TYPE_TABLE = "CREATE TABLE " + TABLE_TYPE + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_TYPE + " TEXT)";

        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_BRAND_TABLE);
        db.execSQL(CREATE_TYPE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRAND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        onCreate(db);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_BRAND_ID, food.getBrand_id());
        values.put(KEY_TYPE_ID, food.getType_id());
        values.put(KEY_INGREDIENTS, food.getIngredients());
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FOOD, new String[] { KEY_ID, KEY_BRAND_ID, KEY_TYPE_ID, KEY_NAME, KEY_INGREDIENTS }, KEY_ID + "=?",
                                 new String[] { String.valueOf(id) }, null, null, null, null);
        Food food = null;
        if(cursor != null && cursor.moveToFirst()) {
            food = new Food(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
        } else {
            food = new Food(-1, -1, -1, "Food", "Nill");
        }
        cursor.close();
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
                food.setBrand_id(Integer.parseInt(cursor.getString(1)));
                food.setType_id(Integer.parseInt(cursor.getString(2)));
                food.setName(cursor.getString(3));
                food.setIngredients(cursor.getString(4));
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        return foodList;
    }

    public int getFoodCount() {
        String countQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_BRAND_ID, food.getBrand_id());
        values.put(KEY_TYPE_ID, food.getType_id());
        values.put(KEY_INGREDIENTS, food.getIngredients());
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
            food.setBrand_id(Integer.parseInt(cursor.getString(1)));
            food.setType_id(Integer.parseInt(cursor.getString(2)));
            food.setName(cursor.getString(3));
            food.setIngredients(cursor.getString(4));
            cursor.close();
        } else {
            food = null;
        }
        db.close();
        return food;
    }

    public void addBrand(Brand brand) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, brand.getName());
        db.insert(TABLE_BRAND, null, values);
        db.close();
    }

    public Brand getBrand(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BRAND, new String[] { KEY_ID, KEY_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        Brand brand = null;
        if(cursor != null && cursor.moveToFirst()) {
            brand = new Brand(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        } else {
            brand = new Brand(1, "BRAND");
        }
        cursor.close();
        return brand;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<Brand>();

        String selectQuery = "SELECT * FROM " + TABLE_BRAND;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Brand brand = new Brand();
                brand.setId(Integer.parseInt(cursor.getString(0)));
                brand.setName(cursor.getString(1));
                brandList.add(brand);
            } while (cursor.moveToNext());
        }

        return brandList;
    }

    public int getBrandCount() {
        String countQuery = "SELECT * FROM " + TABLE_BRAND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Brand findBrand(String brandname) {
        String query = "SELECT * FROM " + TABLE_BRAND + " WHERE " + KEY_NAME +
                " = \"" + brandname + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Brand brand = new Brand();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            brand.setId(Integer.parseInt(cursor.getString(0)));
            brand.setName(cursor.getString(1));
            cursor.close();
        } else {
            brand = null;
        }
        db.close();
        return brand;
    }

    public void addType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, type.getType());
        db.insert(TABLE_TYPE, null, values);
        db.close();
    }

    public Type getType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TYPE, new String[] { KEY_ID, KEY_TYPE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        Type type = null;
        if(cursor != null && cursor.moveToFirst()) {
            type = new Type(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        } else {
            type = new Type(1, "Type");
        }
        cursor.close();
        return type;
    }

    public List<Type> getAllTypes() {
        List<Type> typeList = new ArrayList<Type>();

        String selectQuery = "SELECT * FROM " + TABLE_TYPE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Type type = new Type();
                type.setId(Integer.parseInt(cursor.getString(0)));
                type.setType(cursor.getString(1));
                typeList.add(type);
            } while (cursor.moveToNext());
        }

        return typeList;
    }

    public int getTypeCount() {
        String countQuery = "SELECT * FROM " + TABLE_TYPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Type findType(String typename) {
        String query = "SELECT * FROM " + TABLE_TYPE + " WHERE " + KEY_TYPE +
                " = \"" + typename + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Type type = new Type();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            type.setId(Integer.parseInt(cursor.getString(0)));
            type.setType(cursor.getString(1));
            cursor.close();
        } else {
            type = null;
        }
        db.close();
        return type;
    }
}
