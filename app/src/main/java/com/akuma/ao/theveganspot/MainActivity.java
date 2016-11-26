package com.akuma.ao.theveganspot;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.akuma.ao.theveganspot", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(prefs.getBoolean("firstrun", true)) {

            Intent intent = new Intent(this, LoadDatabse.class);
            startActivity(intent);

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    public void sendMessage(View view) {
        //So stuff
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void food_list(View view) {
        Intent intent = new Intent(this, DisplayFoodList.class);
        startActivity(intent);
    }
}
