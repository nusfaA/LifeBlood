package com.graise.ansafn.managelifeblood;

import com.graise.ansafn.managelifeblood.Donor.Donor;
import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;
import com.graise.ansafn.managelifeblood.User.User;

/**
 * Created by ansaf.n on 2/24/2018.
 */

public class Common {

    private static String DB_LB_MNG = "lifebloodmng";
    public static String USER_COLLECTION = "user";
    public static String ELIGIBILITY_COLLECTION = "eligibilitycriteria";
    public static String DONATION_REQ_COLLECTION = "requests";
    public static String STAT_COLLECTION = "statisticalInfo";
    public static String ITEM_QTY = "itemquantity";
    private static String DB_LB_REG = "lifebloodreg";
    public static String USER_REG_COLLECTION = "user_registration";
    public static String DONOR_COLLECTION = "donor";

    private static String API_KEY = "pmt_ndxPT0bqszftCsrdF3OKLM6q0XMr";

    //user collection
    public static String getUserSingle(User user) {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, USER_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/" + user.get_id().getOid() + "?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    public static String getUserAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, USER_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    //eligibilitycriteria collection
    public static String getEligibilitySingle(EligibilityCriteria eliCri) {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, ELIGIBILITY_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/" + eliCri.get_id().getOid() + "?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    public static String getEligibilityAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, ELIGIBILITY_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    //request collection - lifebloodmng
    public static String getRequestAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, DONATION_REQ_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    //donor collection - lifebloodreg
    public static String getDonorAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_REG, DONOR_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    //user_registration collection - lifebloodreg
    public static String getUserRegSingle(Donor donor) {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_REG, USER_REG_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/" + donor.getUserId() + "?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    public static String getStatisticalinfoAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, STAT_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    //DonationTypeQty itemQty
    public static String getItemQty() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_MNG, ITEM_QTY);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey=" + API_KEY); //"/"+itemQty.getId()+
        return stringBuilder.toString();
    }
}
