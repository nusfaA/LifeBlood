package com.graise.ansafn.lifeblood;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Lushani on 24/03/2018.
 */

public class ActivityDonorRegistration extends AppCompatActivity {

    private static final String TAG = "ActivityDonorRegistration";

    //private TextView donorName, age, mobile, address, bloodgroup;
    private EditText editdonorName, editAge, editMobile, editAddress;
    private Spinner bloodGroup;
    private Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);

        btnregister = (Button) findViewById(R.id.register);
        //donorName = (TextView) findViewById(R.id.donorName);
        //age = (TextView) findViewById(R.id.age);
        //mobile = (TextView) findViewById(R.id.mobile);
        //address = (TextView) findViewById(R.id.address);
        //bloodgroup = (TextView) findViewById(R.id.bloodgroup);
        editdonorName = (EditText) findViewById(R.id.editdonorname);
        editAge = (EditText) findViewById(R.id.edit_Age);
        editMobile = (EditText) findViewById(R.id.editMobile);
        editAddress = (EditText) findViewById(R.id.editaddress);

        //Save data
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostData(editdonorName.getText().toString(), editAge.getText().toString(), editMobile.getText().toString(), editAddress.getText().toString(), "A+(Positive)").execute(Common.getDonorAPI());
            }
        });


    }

    //Add new User
    class PostData extends AsyncTask<String, String, String> {

        ProgressDialog pd = new ProgressDialog(ActivityDonorRegistration.this);
        String donorname;
        String age;
        String mobile;
        String address;
        String bloodgroup;


        public PostData(String donorname, String age, String mobile, String address, String bloodgroup) {
            this.donorname = donorname;
            this.age = age;
            this.mobile = mobile;
            this.address = address;
            this.bloodgroup = bloodgroup;
        }

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
            donorname = donorname;//use a username builder

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{" +
                    "\"donorname\":\"" + donorname + "\"," +
                    "\"age\":\"" + age + "\"," +
                    "\"mobile\":\"" + mobile + "\"," +
                    "\"address\":\"" + address + "\"," +
                    "\"bloodgroup\":\"" + bloodgroup + "\"" +
                    "}";

            hh.PostHTTPData(urlString, json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.dismiss();

        }
    }
}
