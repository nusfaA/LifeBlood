package com.graise.ansafn.managelifeblood.User;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ansaf.n on 2/24/2018.
 */

public class Id {
    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
