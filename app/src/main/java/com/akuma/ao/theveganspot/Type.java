package com.akuma.ao.theveganspot;

/**
 * Created by akuma on 27/11/16.
 */

public class Type {
    protected int id;
    protected String type;

    public Type() {

    }

    public Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
