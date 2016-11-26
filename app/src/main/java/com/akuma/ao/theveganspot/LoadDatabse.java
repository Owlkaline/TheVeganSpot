package com.akuma.ao.theveganspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LoadDatabse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_databse);

        DBHandler db = new DBHandler(this);

        Log.d("Insert: ", "Inserting..");
        db.addFood(new Food(1, "BioCheese"));
        db.addFood(new Food(2, "Cracked Peppers"));
        db.addFood(new Food(3, "Deli Hommus"));
       // finish();
    }
}
