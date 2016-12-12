package io.codehouse.stocksolution.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 12/6/16.
 */

public class Products{

    int id;
    String name,measurement;
    int id_product_category,server_id;

    public Products(int id, String name, String measurement, int id_product_category, int server_id) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.id_product_category = id_product_category;
        this.server_id = server_id;
    }

    public Products() {
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_product_category() {
        return id_product_category;
    }

    public void setId_product_category(int id_product_category) {
        this.id_product_category = id_product_category;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }
}
