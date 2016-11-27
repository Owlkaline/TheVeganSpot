package com.akuma.ao.theveganspot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by akuma on 27/11/16.
 */

public class BrandListAdapter extends ArrayAdapter<String> {
    public class ViewHolder {
        Button btn;
    }

    protected int layout;
    protected Context context;

    public BrandListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout = resource;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BrandListAdapter.ViewHolder mainViewHolder = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            final BrandListAdapter.ViewHolder viewHolder = new BrandListAdapter.ViewHolder();
            viewHolder.btn = (Button) convertView.findViewById(R.id.button_list);
            viewHolder.btn.setText(convertView.findViewById(R.id.button_list).toString());
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListFoodActivity.class);
                    DBHandler db = new DBHandler(context);
                    Brand brand = db.findBrand(viewHolder.btn.getText().toString());
                    //Food food = db.getFood(position+1);
                    intent.putExtra(searchActivity.SEARCHTYPE, "2");
                    intent.putExtra(BrandActivity.TYPE, ""+brand.getId());
                    context.startActivity(intent);
                }
            });
            convertView.setTag(viewHolder);
        }
        mainViewHolder = (BrandListAdapter.ViewHolder) convertView.getTag();
        mainViewHolder.btn.setText(getItem(position));

        return convertView;
    }
}
