package com.graise.ansafn.lifeblood;

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
import com.graise.ansafn.lifeblood.Donor.Donor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    ListView lstViewDet;
    Button btnAdd,btnEdit,btnDelete;
    EditText edtFName;
    Donor donorSelected = null;
    List<Donor> donors = new ArrayList<Donor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstViewDet = (ListView)findViewById(R.id.lstViewDet);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        edtFName = (EditText) findViewById(R.id.edtFName);

        //load data
        new GetData().execute(Common.getAddressAPI());

        //add event
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostData(edtFName.getText().toString()).execute(Common.getAddressAPI());
            }
        });

        //edit event
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PutData(edtFName.getText().toString()).execute(Common.getAddressSingle(donorSelected));
            }
        });

        //edit event
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteData(edtFName.getText().toString()).execute(Common.getAddressSingle(donorSelected));
            }
        });

        //view data
        lstViewDet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                donorSelected = donors.get(position);

                edtFName.setText(donorSelected.getUsername());
            }
        });

    }

    //process data
    class GetData extends AsyncTask<String,Void,String>{

        ProgressDialog pd = new ProgressDialog(ActivityMain.this);


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
            Type listType = new TypeToken<List<Donor>>(){}.getType();
            donors = gson.fromJson(s,listType);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),donors);
            lstViewDet.setAdapter(adapter);
            pd.dismiss();
        }

    }

    //Add new User
    class PostData extends  AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(ActivityMain.this);
        String username;

        public PostData(String username) {
            this.username = username;
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

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"username\":\""+username+"\"}";

            hh.PostHTTPData(urlString,json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getAddressAPI());

            pd.dismiss();

        }
    }

    //Edit new User
    class PutData extends  AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(ActivityMain.this);
        String username;

        public PutData(String username) {
            this.username = username;
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

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"username\":\""+username+"\"}";

            hh.PutHTTPData(urlString,json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getAddressAPI());

            pd.dismiss();

        }
    }

    //Delete  User
    class DeleteData extends  AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(ActivityMain.this);
        String username;

        public DeleteData(String username) {
            this.username = username;
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

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{\"username\":\""+username+"\"}";

            hh.DeleteHTTPData(urlString,json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //refresh data
            new GetData().execute(Common.getAddressAPI());

            pd.dismiss();

        }
    }
}
