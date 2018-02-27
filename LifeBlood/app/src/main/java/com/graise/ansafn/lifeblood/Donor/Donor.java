package com.graise.ansafn.lifeblood.Donor;

/**
 * Created by ansaf.n on 2/23/2018.
 */

public class Donor {

    private Id _id;
    private String username;
    private String name_first;
    private String name_last;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName_first() {
        return name_first;
    }

    public void setName_first(String name_first) {
        this.name_first = name_first;
    }

    public String getName_last() {
        return name_last;
    }

    public void setName_last(String name_last) {
        this.name_last = name_last;
    }
}
