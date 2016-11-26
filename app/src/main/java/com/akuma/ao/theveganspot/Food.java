package com.akuma.ao.theveganspot;

/**
 * Created by akuma on 26/11/16.
 */

public class Food {
    protected int id;
    protected String name;

    public Food() {

    }

    public Food(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
