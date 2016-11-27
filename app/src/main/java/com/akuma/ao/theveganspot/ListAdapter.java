package com.akuma.ao.theveganspot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by akuma on 27/11/16.
 */

public class ListAdapter extends ArrayAdapter<String> {

    public class ViewHolder {
        TextView name;
        Button btn;
    }

    protected int layout;
    protected Context context;

    public ListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout = resource;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_list);
            viewHolder.btn = (Button) convertView.findViewById(R.id.button_list);
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FoodDetialsActivity.class);
                    DBHandler db = new DBHandler(context);
                    Food food = db.findFood(viewHolder.name.getText().toString());
                    //Food food = db.getFood(position+1);
                    intent.putExtra(ListFoodActivity.FOOD_ID, ""+food.getBrand_id());
                    db.close();
                    context.startActivity(intent);
                }
            });
            convertView.setTag(viewHolder);
        }
        mainViewHolder = (ViewHolder) convertView.getTag();
        mainViewHolder.name.setText(getItem(position));


        return convertView;
    }
}
