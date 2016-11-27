package com.akuma.ao.theveganspot;

/**
 * Created by akuma on 27/11/16.
 */

public class Brand {
    protected int id;
    protected String name;

    public Brand() {

    }

    public Brand(int id, String name) {
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
