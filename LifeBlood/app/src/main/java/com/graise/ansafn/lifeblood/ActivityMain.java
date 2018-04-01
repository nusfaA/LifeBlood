package com.graise.ansafn.lifeblood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graise.ansafn.lifeblood.DonationRequest.DonationRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    private Button btnSignUp;
    private ListView lstRequest;
    List<DonationRequest> reqList = new ArrayList<DonationRequest>();
    DonationRequest reqSeleceted = new DonationRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstRequest = (ListView) findViewById(R.id.lstRequest);

        //retrieve
        //String[] requestArray = {"O+ Blood is Needed", "Request 02", "Request 03", "Request 04",};
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.req_row, requestArray);

        //lstRequest.setAdapter(adapter);

        //load data
        new GetData().execute(Common.getRequestAPI());

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityEligibilityQuestionaire.class);
                startActivity(intent);
            }
        });

        //donor check-in
        //view data
        lstRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                reqSeleceted = reqList.get(position);


                /*Intent intent = new Intent(ActivityLevel.this,ActivityNewGame.class);

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add data to bundle
                bundle.putString("level","novice");

                //Add the bundle to the intent
                intent.putExtras(bundle);
                startActivity(intent);*/


                //send to Eligiblity Process for Donor Check-in

            }
        });
    }

    //process data
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityMain.this);


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
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), reqList);
            lstRequest.setAdapter(adapter);
            pd.dismiss();
        }

    }

}
