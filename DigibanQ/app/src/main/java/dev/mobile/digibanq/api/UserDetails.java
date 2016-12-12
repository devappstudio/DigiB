package dev.mobile.digibanq.api;

/**
 * Created by finxl-isaac on 12/4/16.
 */

public class UserDetails {

    String full_name, dob,phone,email,residential_address,uuid,id;

    public UserDetails() {
    }

    public UserDetails(String full_name, String dob, String phone, String email, String residential_address, String uuid, String id) {
        this.full_name = full_name;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.residential_address = residential_address;
        this.uuid = uuid;
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}