package dev.mobile.digibanq.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by finxl-isaac on 1/12/17.
 */

public class ConfirmationCode extends RealmObject {
    @PrimaryKey
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String code,email;

    public ConfirmationCode(String code, String email) {
        this.code = code;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConfirmationCode() {
    }
}
