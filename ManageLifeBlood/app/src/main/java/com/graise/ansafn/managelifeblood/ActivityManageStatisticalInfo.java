package com.graise.ansafn.managelifeblood;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Lushani on 4/3/2018.
 */



public class ActivityManageStatisticalInfo extends AppCompatActivity {

    private static final String TAG = "ActivityManageStatiticalInfo";

    //private TextView donorName, age, mobile, address, bloodgroup;
    private ImageButton btnClander;
    private DatePickerDialog.OnDateSetListener listDateSet;
    private TextView date;
    private EditText editlocationCode, editbloodgroup, edityear, editquarter, editqrequired, editqsupplied, editdate;
    private Button btnsubmit;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_statistical_info);

        btnsubmit = (Button) findViewById(R.id.submit);
        date = (TextView) findViewById(R.id.date);
        btnClander = (ImageButton) findViewById(R.id.btnCalander);
        editlocationCode = (EditText) findViewById(R.id.editlocationCode);
        editbloodgroup = (EditText) findViewById(R.id.editbloodgroup);
        edityear = (EditText) findViewById(R.id.edityear);
        editquarter = (EditText) findViewById(R.id.editquarter);
        editqrequired = (EditText) findViewById(R.id.editqrequired);
        editqsupplied = (EditText) findViewById(R.id.editqsupplied);

        btnClander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(ActivityManageStatisticalInfo.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        listDateSet,
                        year, month, day);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });

        listDateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
               // Log.d(TAG, dayOfMonth + "/" + month + "/" + year);

                String dateSel = dayOfMonth + "/" + month + "/" + year;
                date.setText(dateSel);
            }
        };

        //Save data
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostData(editlocationCode.getText().toString(), editbloodgroup.getText().toString(), edityear.getText().toString(), editquarter.getText().toString(), editqrequired.getText().toString(), editqsupplied.getText().toString(),"29/03/2018" ).execute(Common.getStatisticalinfoAPI());
            }
        });


    }


//Add new User
class PostData extends AsyncTask<String, String, String> {

    ProgressDialog pd = new ProgressDialog(ActivityManageStatisticalInfo.this);
    String locationCode;
    String bloodgroup;
    String year;
    String quarter;
    String qrequired;
    String qsupplied;
    String date;

    public PostData(String locationCode, String bloodgroup, String year, String quarter, String qrequired, String qsupplied, String date) {
        this.locationCode = locationCode;
        this.bloodgroup = bloodgroup;
        this.year = year;
        this.quarter = quarter;
        this.qrequired = qrequired;
        this.qsupplied = qsupplied;
        this.date = date;
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
        locationCode = locationCode;//use a username builder

        HTTPDataHandler hh = new HTTPDataHandler();
        String json = "{" +
                "\"locCode\":\"" + locationCode + "\"," +
                "\"group\":\"" + bloodgroup + "\"," +
                "\"year\":\"" + year + "\"," +
                "\"quarter\":\"" + quarter + "\"," +
                "\"quantityRequired\":\"" + qrequired + "\"" +
                "\"quantitySupplied\":\"" + qsupplied + "\"" +
                "\"createdDate\":\"" + date + "\"" +
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
