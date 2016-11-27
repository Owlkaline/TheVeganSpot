package com.akuma.ao.theveganspot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE="default_message";
    SharedPreferences prefs = null;
    protected ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.akuma.ao.theveganspot", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean firstrun = prefs.getBoolean("firstrun", true);
        int updatedb = prefs.getInt("dbv", 1);

        if( firstrun) {
            populatedDB(DBHandler.DATABASE_VERSION);
            prefs.edit().putBoolean("firstrun", false).commit();
            prefs.edit().putInt("dbv", DBHandler.DATABASE_VERSION);
        } else if(updatedb != DBHandler.DATABASE_VERSION) {
            populatedDB(updatedb);
            prefs.edit().putInt("dbv", DBHandler.DATABASE_VERSION);
        }
    }

    protected void populatedDB(int olddb) {
        DBHandler db = new DBHandler(this);
        db.onUpgrade(db.getWritableDatabase(), olddb, DBHandler.DATABASE_VERSION);

        Log.d("Insert: ", "Inserting..");

        db.addType(new Type(1, "Snacks"));
        db.addType(new Type(2, "Cheeses"));
        db.addType(new Type(3, "Chips and Dips"));
        db.addType(new Type(4, "Spreads"));

        db.addBrand(new Brand(1, "MyLife"));
        db.addBrand(new Brand(2, "Coles"));
        db.addBrand(new Brand(3, "Deli Original"));
        db.addBrand(new Brand(4, "Sprinters"));

        // new Food ( id, Brand, Type, Name )
        db.addFood(new Food(0, 1, 2, "BioCheese"));
        db.addFood(new Food(1, 2, 1, "Cracked Peppers"));
        db.addFood(new Food(2, 3, 3, "Hommus Dip"));
        db.addFood(new Food(3, 4, 3, "Salt and Vinegar chips"));
        db.addFood(new Food(4, 1, 2, "BioCheese - Cheddar"));
        db.addFood(new Food(5, 1, 2, "BioCheese Slices"));
        db.addFood(new Food(6, 1, 2, "BioCheese Slices - Cheddar"));
        db.addFood(new Food(7, 1, 2, "BioCheese Shred - Cheddar"));
        db.addFood(new Food(8, 1, 2, "BioCheese Shred - Pizza"));
        db.addFood(new Food(9, 1, 4, "BioButtery"));

        db.close();
    }

    public void sendMessage(View view) {
        //So stuff
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void search_food(View view) {
        Intent intent = new Intent(this, searchActivity.class);
        startActivity(intent);
    }
}

