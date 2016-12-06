package com.akuma.ao.theveganspot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;


public class searchActivity extends AppCompatActivity {

    public static String SEARCHTYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void food_list(View view) {
        Intent intent = new Intent(this, ListFoodActivity.class);
        String message = "1";
        intent.putExtra(searchActivity.SEARCHTYPE, message);
        startActivity(intent);
    }

    public void brand_search(View view) {
        Intent intent = new Intent(this, BrandActivity.class);
        startActivity(intent);
    }

    public void type_search(View view) {
        Intent intent = new Intent(this, TypeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
