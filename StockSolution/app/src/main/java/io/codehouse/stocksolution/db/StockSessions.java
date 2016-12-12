package io.codehouse.stocksolution.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 12/6/16.
 */

public class StockSessions extends RealmObject {

    @PrimaryKey
    int id;
    String date_opened,date_closed;
    int id_store,id_user_opened,id_user_closed,server_id,id_store_server,id_user_opened_server,id_user_closed_server;

    public StockSessions() {
    }

    public int getId_store_server() {
        return id_store_server;
    }

    public void setId_store_server(int id_store_server) {
        this.id_store_server = id_store_server;
    }

    public int getId_user_opened_server() {
        return id_user_opened_server;
    }

    public void setId_user_opened_server(int id_user_opened_server) {
        this.id_user_opened_server = id_user_opened_server;
    }

    public int getId_user_closed_server() {
        return id_user_closed_server;
    }

    public void setId_user_closed_server(int id_user_closed_server) {
        this.id_user_closed_server = id_user_closed_server;
    }

    public StockSessions(int id, String date_opened, String date_closed, int id_store, int id_user_opened, int id_user_closed, int server_id) {
        this.id = id;
        this.date_opened = date_opened;
        this.date_closed = date_closed;
        this.id_store = id_store;
        this.id_user_opened = id_user_opened;
        this.id_user_closed = id_user_closed;
        this.server_id = server_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_opened() {
        return date_opened;
    }

    public void setDate_opened(String date_opened) {
        this.date_opened = date_opened;
    }

    public String getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(String date_closed) {
        this.date_closed = date_closed;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public int getId_user_opened() {
        return id_user_opened;
    }

    public void setId_user_opened(int id_user_opened) {
        this.id_user_opened = id_user_opened;
    }

    public int getId_user_closed() {
        return id_user_closed;
    }

    public void setId_user_closed(int id_user_closed) {
        this.id_user_closed = id_user_closed;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }
}
