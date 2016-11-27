package com.akuma.ao.theveganspot;

/**
 * Created by akuma on 26/11/16.
 */

public class Food {
    protected int id;
    protected int brand_id;
    protected int type_id;
    protected String name;
    protected String ingredients;

    public Food() {

    }

    public Food(int id, int brand_id, int type_id, String name, String ingredients) {
        this.id = id;
        this.brand_id = brand_id;
        this.type_id = type_id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand_id(int brand_id) { this.brand_id = brand_id; }


    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getId() {
        return id;
    }
    public int getBrand_id() {
        return brand_id;
    }
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
