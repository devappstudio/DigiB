package dev.mobile.digibanq.db;

import io.realm.RealmObject;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "USER_GOALS".
 */
public class UserGoals extends RealmObject {

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END
    private int id;

    public UserGoals(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserGoals() {
    }


    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
