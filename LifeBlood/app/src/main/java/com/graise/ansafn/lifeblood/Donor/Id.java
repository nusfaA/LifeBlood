package com.graise.ansafn.lifeblood.Donor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ansaf.n on 2/23/2018.
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
