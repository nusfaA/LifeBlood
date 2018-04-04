package com.graise.ansafn.managelifeblood.DonationTypeQty;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uthpala on 3/24/2018.
 */

public class DonationTypeQty {
    @SerializedName("$itemId")
    private String itemId;
    private String itemName;
    private String donationType;
    private int qty;


    public String getId() {
        return itemId;
    }

    public void setId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
//
//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }
}
