package com.akuma.ao.theveganspot;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayFoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase mydatabase = openOrCreateDatabase("Products",MODE_PRIVATE,null);
     //   mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Products(Product_ID INT, Name VARCHAR, Brand_ID INT, Ingredients VARCHAR, Description VARCHAR);");
      //  mydatabase.execSQL("INSERT INTO Products VALUES('1','BioCheese', 1, 'Pepper, orange, peels', 'A vegan cheese');");
        setContentView(R.layout.activity_display_food_list);
    }
}
