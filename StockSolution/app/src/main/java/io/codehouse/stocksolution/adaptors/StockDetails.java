package io.codehouse.stocksolution.adaptors;

/**
 * Created by finxl-isaac on 12/8/16.
 */

public class StockDetails {
    int id;
    int server_id,id_stock_session,id_product,id_user;
    String timestamp;
    double quantity,purchasing_price;


    public StockDetails(int id, int server_id, int id_stock_session, int id_product, int id_user, String timestamp, double quantity, double purchasing_price) {
        this.id = id;
        this.server_id = server_id;
        this.id_stock_session = id_stock_session;
        this.id_product = id_product;
        this.id_user = id_user;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.purchasing_price = purchasing_price;
    }


    public StockDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }

    public int getId_stock_session() {
        return id_stock_session;
    }

    public void setId_stock_session(int id_stock_session) {
        this.id_stock_session = id_stock_session;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public double getPurchasing_price() {
        return purchasing_price;
    }

    public void setPurchasing_price(double purchasing_price) {
        this.purchasing_price = purchasing_price;
    }
}
