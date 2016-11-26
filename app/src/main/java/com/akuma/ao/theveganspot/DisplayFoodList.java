package com.akuma.ao.theveganspot;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DisplayFoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  SQLiteDatabase mydatabase = openOrCreateDatabase("Products",MODE_PRIVATE,null);
     //   mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Products(Product_ID INT, Name VARCHAR, Brand_ID INT, Ingredients VARCHAR, Description VARCHAR);");
      //  mydatabase.execSQL("INSERT INTO Products VALUES('1','BioCheese', 1, 'Pepper, orange, peels', 'A vegan cheese');");
        setContentView(R.layout.activity_display_food_list);

        DBHandler db = new DBHandler(this);

        Log.d("Reading: ", "Reading all food...");
        List<Food> foods = db.getAllFood();
        String msg = "";

        for(Food food: foods) {
            String log = "Id: " + food.getId() + ",Name: " + food.getName();
            msg += food.getName() + "\n";
            Log.d("Food: : ", log);
        }

        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setText(msg);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_food_list);
        layout.addView(textView);

    }
}
