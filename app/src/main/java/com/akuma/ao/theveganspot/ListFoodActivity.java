package com.akuma.ao.theveganspot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static android.R.id.list;

public class ListFoodActivity extends AppCompatActivity {

    protected ArrayList<String> data = new ArrayList<String>();

    public static String FOOD_ID = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  SQLiteDatabase mydatabase = openOrCreateDatabase("Products",MODE_PRIVATE,null);
        //   mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Products(Product_ID INT, Name VARCHAR, Brand_ID INT, Ingredients VARCHAR, Description VARCHAR);");
        //  mydatabase.execSQL("INSERT INTO Products VALUES('1','BioCheese', 1, 'Pepper, orange, peels', 'A vegan cheese');");
        setContentView(R.layout.activity_list_food);

        Intent intent = getIntent();
        String typeOfSearch = intent.getStringExtra(searchActivity.SEARCHTYPE);

        ListView lv = (ListView) findViewById(R.id.listview);

        switch (typeOfSearch) {
            case "1":
                generateAllList();
                break;
            case "2":
                String brand = intent.getStringExtra(BrandActivity.TYPE);
                generateBrandList(Integer.parseInt(brand));
                break;
            case "3":
                String type = intent.getStringExtra(BrandActivity.TYPE);
                generateTypeList(Integer.parseInt(type));
                break;
        }


        lv.setAdapter(new ListAdapter(this, R.layout.list_layout, data));
        DBHandler db = new DBHandler(this);

        Log.d("Reading: ", "Reading all...");
        List<Food> foods = db.getAllFood();

        for (Food food : foods) {
            String log = "Id: " + food.getId() + ",Name: " + food.getName();
            Log.d("Food: : ", log);
        }
        List<Brand> brands = db.getAllBrands();

        for (Brand brand : brands) {
            String log = "Id: " + brand.getId() + ",Name: " + brand.getName();
            Log.d("Food: : ", log);
        }
        /*

        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText(msg);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_food_list);
        layout.addView(textView);*/

    }

    class IgnoreCaseComparator implements Comparator<String> {
        public int compare(String strA, String strB) {
            return strA.compareToIgnoreCase(strB);
        }
    }

    public void generateAllList() {
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < db.getFoodCount(); ++i) {
            Food food = db.getFood(i+1);
            data.add("" + food.getName());
        }
        IgnoreCaseComparator icc = new IgnoreCaseComparator();

        java.util.Collections.sort(data,icc);

        db.close();
    }

    public void generateBrandList(int brand) {
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < db.getFoodCount(); ++i) {
            Food food = db.getFood(i+1);
            if(food.getBrand_id() == brand)
                data.add("" + food.getName());
        }
        IgnoreCaseComparator icc = new IgnoreCaseComparator();

        java.util.Collections.sort(data,icc);

        db.close();
    }


    public void generateTypeList(int type) {
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < db.getFoodCount(); ++i) {
            Food food = db.getFood(i+1);
            if(food.getType_id() == type)
                data.add("" + food.getName());
        }
        IgnoreCaseComparator icc = new IgnoreCaseComparator();

        java.util.Collections.sort(data,icc);

        db.close();
    }

    public void buttonClicked(int i) {
        Intent intent = new Intent(this, FoodDetialsActivity.class);

        intent.putExtra(ListFoodActivity.FOOD_ID, i);
        startActivity(intent);
    }
}
