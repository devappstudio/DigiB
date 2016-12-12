package io.codehouse.stocksolution.adaptors;

/**
 * Created by finxl-isaac on 12/8/16.
 */

public class Stores {

    String name,location,telephone,last_visit,next_visit;
    int server_id;

    public Stores(String name, String location, String telephone, String last_visit, String next_visit, int server_id) {
        this.name = name;
        this.location = location;
        this.telephone = telephone;
        this.last_visit = last_visit;
        this.next_visit = next_visit;
        this.server_id = server_id;
    }


    public Stores() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(String last_visit) {
        this.last_visit = last_visit;
    }

    public String getNext_visit() {
        return next_visit;
    }

    public void setNext_visit(String next_visit) {
        this.next_visit = next_visit;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }
}
