package dev.mobile.digibanq.db;


// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Entity mapped to table "USER".
 */
public class User extends RealmObject {
    @PrimaryKey
    private int id;
    private int serverid;

    private String fullname,uuid;
    private String phone;
    private String email;
    private String dob;
    private String smscode;
    private String address;
    private Boolean pendingsync;
    private String lastsync;
    private Double wallet;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, int serverid, String fullname, String phone, String email, String dob, String smscode, String address, Boolean pendingsync, String lastsync, Double wallet) {
        this.id = id;
        this.serverid = serverid;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.smscode = smscode;
        this.address = address;
        this.pendingsync = pendingsync;
        this.lastsync = lastsync;
        this.wallet = wallet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User(int id, int serverid, String fullname, String uuid, String phone, String email, String dob, String smscode, String address, Boolean pendingsync, String lastsync, Double wallet) {
        this.id = id;
        this.serverid = serverid;
        this.fullname = fullname;
        this.uuid = uuid;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.smscode = smscode;
        this.address = address;
        this.pendingsync = pendingsync;
        this.lastsync = lastsync;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServerid() {
        return serverid;
    }

    public void setServerid(int serverid) {
        this.serverid = serverid;
    }

    
    public String getFullname() {
        return fullname;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFullname( String fullname) {
        this.fullname = fullname;
    }

    
    public String getPhone() {
        return phone;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPhone( String phone) {
        this.phone = phone;
    }

    
    public String getEmail() {
        return email;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEmail( String email) {
        this.email = email;
    }

    
    public String getDob() {
        return dob;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDob( String dob) {
        this.dob = dob;
    }

    
    public String getSmscode() {
        return smscode;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setSmscode( String smscode) {
        this.smscode = smscode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPendingsync() {
        return pendingsync;
    }

    public void setPendingsync(Boolean pendingsync) {
        this.pendingsync = pendingsync;
    }

    public String getLastsync() {
        return lastsync;
    }

    public void setLastsync(String lastsync) {
        this.lastsync = lastsync;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
