package com.graise.ansafn.lifeblood;

import com.graise.ansafn.lifeblood.Donor.Donor;

/**
 * Created by ansaf.n on 2/23/2018.
 */

public class Common {
    private static String DB_LB_REG = "lifebloodreg";
    public static String USER_COLLECTION = "user_registration";
    private static String DB_LB_MNG = "lifebloodmng";
    public static String DONATION_REQ_COLLECTION = "requests";
    private static String API_KEY = "pmt_ndxPT0bqszftCsrdF3OKLM6q0XMr";

    public static String getDonorSingle(Donor donor) {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_REG, USER_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/" + donor.get_id().getOid() + "?apiKey=" + API_KEY);
        return stringBuilder.toString();
    }

    public static String getDonorAPI() {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_LB_REG, USER_COLLECTION);
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

}
