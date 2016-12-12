package io.codehouse.stocksolution.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 12/6/16.
 */

public class Expenses {

    int id;
    int server_id,id_stock_session,id_user;
    String expense,timestamp,narration;
    double cost;

    public Expenses(int id, int server_id, int id_stock_session, int id_user, String expense, String timestamp, String narration, double cost) {
        this.id = id;
        this.server_id = server_id;
        this.id_stock_session = id_stock_session;
        this.id_user = id_user;
        this.expense = expense;
        this.timestamp = timestamp;
        this.narration = narration;
        this.cost = cost;
    }

    public Expenses() {
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
