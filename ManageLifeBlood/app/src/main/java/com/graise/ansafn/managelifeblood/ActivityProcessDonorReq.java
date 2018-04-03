package com.graise.ansafn.managelifeblood;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graise.ansafn.managelifeblood.DonationRequest.DonationRequest;
import com.graise.ansafn.managelifeblood.Donor.Donor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ansaf.n on 4/3/2018.
 * Summary : Process donors based on the predicted blood donation and send notifications
 */

public class ActivityProcessDonorReq extends AppCompatActivity {
    private ListView lstRequest, lstDonors;
    List<DonationRequest> reqList = new ArrayList<DonationRequest>();
    List<Donor> donorList = new ArrayList<Donor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_donor_req);

        lstRequest = (ListView) findViewById(R.id.lstRequest);
        lstDonors = (ListView) findViewById(R.id.lstDnrDet);

        //load data
        new GetData().execute(Common.getRequestAPI());

        //Donor Selection Process
        if (reqList != null && reqList.size() > 0) {
            processRequest(reqList);
        }

        DonationRequestAdapter adapter = new DonationRequestAdapter(getApplicationContext(), reqList);
        lstRequest.setAdapter(adapter);

    }

    //process data for donation requests
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityProcessDonorReq.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<DonationRequest>>() {
            }.getType();
            reqList = gson.fromJson(s, listType);

            pd.dismiss();
        }

    }

    private void processRequest(List<DonationRequest> requests) {

        //load donors
        new GetDonorData().execute(Common.getDonorAPI());

        if (donorList != null && donorList.size() > 0) {
            for (DonationRequest reqObj : requests) {
                List<Donor> tempDonorLst = new ArrayList<Donor>();

                for (Donor donorObj : donorList) {
                    if (donorObj.getBloodGroup().equalsIgnoreCase(reqObj.getGroup())) {
                        tempDonorLst.add(donorObj);
                    }
                }
                if (tempDonorLst != null) {
                    if (tempDonorLst.size() > 1) {
                        Collections.sort(tempDonorLst, new Comparator<Donor>() {
                            @Override
                            public int compare(Donor lhs, Donor rhs) {
                                return Double.compare(lhs.getScore(), rhs.getScore());
                            }
                        });
                    }
                    reqObj.setDonors(tempDonorLst);

                    //DonorAdapter dnrAdapter = new DonorAdapter(getApplicationContext(), tempDonorLst);
                    //lstDonors.setAdapter(dnrAdapter);

                }
            }
        }
    }

    //process data for donors
    class GetDonorData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityProcessDonorReq.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Loading...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Donor>>() {
            }.getType();
            donorList = gson.fromJson(s, listType);

            pd.dismiss();
        }

    }

    //Send SMS notofication to eligible donors
    private void sendNotification(List<Donor> lstDnr){
        for (Donor dnr : lstDnr) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(dnr.getMobile(), null, "Wish to donate blood. You are required!" +
                    " Kindly visit LifeBlood to place your generous donation.", null, null);
        }
    }


}
