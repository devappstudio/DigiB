package io.codehouse.stocksolution.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 12/6/16.
 */

public class Income extends RealmObject {
    @PrimaryKey
    int id;
    int id_stock_session,id_user,id_product,id_stock_session_server,id_product_server,id_user_server,id_server;
    String narration,timestamp;
    double quantity,price;

    public int getId_server() {
        return id_server;
    }

    public void setId_server(int id_server) {
        this.id_server = id_server;
    }

    public int getId_stock_session_server() {
        return id_stock_session_server;
    }

    public void setId_stock_session_server(int id_stock_session_server) {
        this.id_stock_session_server = id_stock_session_server;
    }

    public int getId_product_server() {
        return id_product_server;
    }

    public void setId_product_server(int id_product_server) {
        this.id_product_server = id_product_server;
    }

    public int getId_user_server() {
        return id_user_server;
    }

    public void setId_user_server(int id_user_server) {
        this.id_user_server = id_user_server;
    }

    public Income(int id, int id_stock_session, int id_user, int id_product, String narration, String timestamp, double quantity, double price) {
        this.id = id;
        this.id_stock_session = id_stock_session;
        this.id_user = id_user;
        this.id_product = id_product;
        this.narration = narration;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.price = price;
    }


    public Income() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_stock_session() {
        return id_stock_session;
    }

    public void setId_stock_session(int id_stock_session) {
        this.id_stock_session = id_stock_session;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
