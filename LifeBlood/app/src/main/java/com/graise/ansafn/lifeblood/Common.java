package com.graise.ansafn.lifeblood;

import com.graise.ansafn.lifeblood.Donor.Donor;

/**
 * Created by ansaf.n on 2/23/2018.
 */

public class Common {
    private static String DB_NAME="lifebloodreg";
    public static String COLLECTION_NAME="donor";
    private static String API_KEY="pmt_ndxPT0bqszftCsrdF3OKLM6q0XMr";

    public static String getAddressSingle(Donor donor){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+donor.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}
