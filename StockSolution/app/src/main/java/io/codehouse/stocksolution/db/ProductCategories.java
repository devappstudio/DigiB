package io.codehouse.stocksolution.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 12/6/16.
 */

public class ProductCategories extends RealmObject {


    @PrimaryKey
    int id;
    String name;
    int id_store,id_user,server_id,id_store_server,id_user_server;

    public int getId_store_server() {
        return id_store_server;
    }

    public void setId_store_server(int id_store_server) {
        this.id_store_server = id_store_server;
    }

    public int getId_user_server() {
        return id_user_server;
    }

    public void setId_user_server(int id_user_server) {
        this.id_user_server = id_user_server;
    }

    public ProductCategories() {
    }

    public ProductCategories(int id, String name, int id_store, int id_user, int server_id) {
        this.id = id;
        this.name = name;
        this.id_store = id_store;
        this.id_user = id_user;
        this.server_id = server_id;
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

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }
}