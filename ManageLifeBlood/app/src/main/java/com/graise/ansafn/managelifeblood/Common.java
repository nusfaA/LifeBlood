package com.graise.ansafn.managelifeblood;

import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;
import com.graise.ansafn.managelifeblood.User.User;

/**
 * Created by ansaf.n on 2/24/2018.
 */

public class Common {

    private static String DB_NAME="lifebloodmng";
    public static String USER_COLLECTION="user";
    public static String ELIGIBILITY_COLLECTION="eligibilitycriteria";
    private static String API_KEY="pmt_ndxPT0bqszftCsrdF3OKLM6q0XMr";

    //user collection
    public static String getUserSingle(User user){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,USER_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+user.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getUserAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,USER_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    //eligibilitycriteria collection
    public static String getEligibilitySingle(EligibilityCriteria eliCri){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,ELIGIBILITY_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("/"+eliCri.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getEligibilityAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,ELIGIBILITY_COLLECTION);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}
