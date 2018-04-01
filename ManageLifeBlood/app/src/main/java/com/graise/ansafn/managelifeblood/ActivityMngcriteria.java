package com.graise.ansafn.managelifeblood;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityMngcriteria extends AppCompatActivity {

    private static final String TAG = "ActivityMngCri";

    private Button btnAdd, btnEdit, btnDelete;
    private EditText edtCondi, edtValue, edtPriority;

    private ListView lstViewDet;

    EligibilityCriteria eliCriSelected = null;
    List<EligibilityCriteria> criterias = new ArrayList<EligibilityCriteria>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mngcriteria);

        lstViewDet = (ListView) findViewById(R.id.lstViewDet);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        edtCondi = (EditText) findViewById(R.id.edtCondition);
        edtValue = (EditText) findViewById(R.id.edtValue);
        edtPriority = (EditText) findViewById(R.id.edtPriority);

        //load data
        new GetData().execute(Common.getEligibilityAPI());

        //add event
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostData(edtCondi.getText().toString(), edtValue.getText().toString(), edtPriority.getText().toString()).execute(Common.getEligibilityAPI());
            }
        });

        //edit event
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PutData(edtCondi.getText().toString(), edtValue.getText().toString(), edtPriority.getText().toString()).execute(Common.getEligibilitySingle(eliCriSelected));
            }
        });

        //delete event
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteData(edtCondi.getText().toString(), edtValue.getText().toString(), edtPriority.getText().toString()).execute(Common.getEligibilitySingle(eliCriSelected));
            }
        });

        //view data
        lstViewDet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                eliCriSelected = criterias.get(position);

                edtCondi.setText(eliCriSelected.getCriteria());
                edtValue.setText(eliCriSelected.getValue());
                edtPriority.setText(eliCriSelected.getPriority());
                btnEdit.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.INVISIBLE);
            }
        });
    }


    //process data
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityMngcriteria.this);


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
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<EligibilityCriteria>>() {
            }.getType();
            criterias = gson.fromJson(s, listType);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), criterias);
            lstViewDet.setAdapter(adapter);
            pd.dismiss();
        }

    }

    //Add new User
    class PostData extends AsyncTask<String, String, String> {

        ProgressDialog pd = new ProgressDialog(ActivityMngcriteria.this);
        String criteria;
        String value;
        String prority;

        public PostData(String criteria, String value, String prority) {
            this.criteria = criteria;
            this.value = value;
            this.prority = prority;
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
            String code = "lbu1800" + prority;

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"ec_code\":\"" + code + "\"," +
                    "\"criteria\":\"" + criteria + "\"," +
                    "\"value\":\"" + value + "\"," +
                    "\"priority\":\"" + prority + "\"" +
                    "}";

            hh.PostHTTPData(urlString, json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getEligibilityAPI());
            refresh();

            pd.dismiss();

        }
    }

    //Edit new User
    class PutData extends AsyncTask<String, String, String> {

        ProgressDialog pd = new ProgressDialog(ActivityMngcriteria.this);
        String criteria;
        String value;
        String prority;

        public PutData(String criteria, String value, String prority) {
            this.criteria = criteria;
            this.value = value;
            this.prority = prority;
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
            String code = "lbu1800" + prority;

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"ec_code\":\"" + code + "\"," +
                    "\"criteria\":\"" + criteria + "\"," +
                    "\"value\":\"" + value + "\"," +
                    "\"priority\":\"" + prority + "\"" +
                    "}";

            hh.PutHTTPData(urlString, json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getEligibilityAPI());
            refresh();

            pd.dismiss();

        }
    }

    //Delete  User
    class DeleteData extends AsyncTask<String, String, String> {

        ProgressDialog pd = new ProgressDialog(ActivityMngcriteria.this);
        String criteria;
        String value;
        String prority;

        public DeleteData(String criteria, String value, String prority) {
            this.criteria = criteria;
            this.value = value;
            this.prority = prority;
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
            String code = "lbu1800" + prority;

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"ec_code\":\"" + code + "\"," +
                    "\"criteria\":\"" + criteria + "\"," +
                    "\"value\":\"" + value + "\"," +
                    "\"priority\":\"" + prority + "\"" +
                    "}";

            hh.DeleteHTTPData(urlString, json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getEligibilityAPI());
            refresh();

            pd.dismiss();

        }
    }

    private void refresh() {
        edtCondi.setText("");
        edtValue.setText("");
        edtPriority.setText("");
        btnEdit.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.VISIBLE);
    }
}
