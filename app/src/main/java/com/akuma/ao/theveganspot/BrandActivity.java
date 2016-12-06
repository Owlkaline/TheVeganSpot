package com.akuma.ao.theveganspot;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BrandActivity extends AppCompatActivity {
    protected ArrayList<String> data = new ArrayList<String>();

    public static String TYPE = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView lv = (ListView) findViewById(R.id.listview);
        generateList();
        lv.setAdapter(new BrandListAdapter(this, R.layout.list_button_layout, data));

    }

    class IgnoreCaseComparator implements Comparator<String> {
        public int compare(String strA, String strB) {
            return strA.compareToIgnoreCase(strB);
        }
    }

    public void generateList() {
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < db.getBrandCount(); ++i) {
            Brand brand = db.getBrand(i+1);
            data.add("" + brand.getName());
        }
        BrandActivity.IgnoreCaseComparator icc = new BrandActivity.IgnoreCaseComparator();

        java.util.Collections.sort(data,icc);

        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
