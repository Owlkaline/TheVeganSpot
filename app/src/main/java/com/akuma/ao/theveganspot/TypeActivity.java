package com.akuma.ao.theveganspot;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

public class TypeActivity extends AppCompatActivity {

    protected ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView lv = (ListView) findViewById(R.id.listview);
        generateList();
        lv.setAdapter(new TypeListAdapter(this, R.layout.list_button_layout, data));
    }

    class IgnoreCaseComparator implements Comparator<String> {
        public int compare(String strA, String strB) {
                return strA.compareToIgnoreCase(strB);
            }
    }

    public void generateList() {
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < db.getTypeCount(); ++i) {
            Type type = db.getType(i+1);
            data.add("" + type.getType());
        }
        com.akuma.ao.theveganspot.TypeActivity.IgnoreCaseComparator icc = new com.akuma.ao.theveganspot.TypeActivity.IgnoreCaseComparator();
        java.util.Collections.sort(data,icc);

        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

