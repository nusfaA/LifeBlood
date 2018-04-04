package com.graise.ansafn.managelifeblood;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graise.ansafn.managelifeblood.DonationTypeQty.DonationTypeQty;
import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uthpala on 4/1/2018.
 * Summary : Dashboard representing the stock and blood donation requirement levels of the specified location.
 */

public class ActivityStockManagement extends AppCompatActivity {

    private static final String TAG = "ActivityStockManagement";
    private TableRow tblRowQty;

    List<DonationTypeQty> items = new ArrayList<DonationTypeQty>();
    private ListView lstViewDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_management);

        lstViewDet = (ListView)findViewById(R.id.lstViewItems);


        //load data
        new GetData().execute(Common.getItemQty());

        //when layout is clicked, go to a screen to update qty
//        lnrLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new ActivityMngcriteria.PostData(edtCondi.getText().toString(),edtValue.getText().toString(),edtPriority.getText().toString()).execute(Common.getEligibilityAPI());
//            }
//        });

        //view data
        showTable();
    }

    private void showTable() {
        tblRowQty = (TableRow)findViewById(R.id.tblRowQty);

//        TextView tv1 = new TextView(this);
//        tv1.setText(" Item Name ");
//        tblRowQty.addView(tv1);
//
//        TextView tv2 = new TextView(this);
//        tv2.setText(" Item Quantity ");
//        tblRowQty.addView(tv2);
    }

    //process data
    class GetData extends AsyncTask<String,Void,String> {

        ProgressDialog pd = new ProgressDialog(ActivityStockManagement.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream =http.GetHTTPData(urlString);
            return  stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<DonationTypeQty>>(){}.getType();
            items = gson.fromJson(s,listType);
            ItemAdapter adapter = new ItemAdapter(getApplicationContext(),items);
            lstViewDet.setAdapter(adapter);
            pd.dismiss();
        }

    }
}

