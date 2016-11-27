package com.akuma.ao.theveganspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FoodDetialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detials);

        Intent intent = getIntent();
        String food_id = intent.getStringExtra(ListFoodActivity.FOOD_ID);
       // Log.d("ID", "onCreate: " + food_id);

        TextView txtbrand = (TextView)findViewById(R.id.brand_text);
        TextView txtFood  = (TextView) findViewById(R.id.product_text);
        TextView txtType  = (TextView) findViewById(R.id.type_text);
        TextView txtIngredients = (TextView) findViewById(R.id.ingredients_text);

        DBHandler db = new DBHandler(this);
        Brand brand = db.getBrand(Integer.parseInt(food_id));
        Food food = db.getFood(Integer.parseInt(food_id));
        Type type = db.getType(food.getType_id());

        txtFood.setText("Product: " + food.getName());
        txtbrand.setText("Brand: " + brand.getName());
        txtType.setText("Type: " + type.getType());
        txtIngredients.setText("Ingredients: \n" + food.getIngredients());
    }
}
