package com.graise.ansafn.managelifeblood;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityUserreg extends AppCompatActivity {

    private static final String TAG = "ActivityUserreg";

    private ImageButton btnClander;
    private DatePickerDialog.OnDateSetListener listDateSet;
    private TextView disDob;
    private Button btnSave;
    private EditText edtFname, edtLname, edtConNum, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);

        disDob = (TextView) findViewById(R.id.disDob);
        btnClander = (ImageButton) findViewById(R.id.btnCalander);
        btnSave = (Button) findViewById(R.id.btnSave);
        edtFname = (EditText) findViewById(R.id.edtFName);
        edtLname = (EditText) findViewById(R.id.edtLName);
        edtConNum = (EditText) findViewById(R.id.edtContactNum);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnClander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(ActivityUserreg.this,
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
                Log.d(TAG, dayOfMonth + "/" + month + "/" + year);

                String dateSel = dayOfMonth + "/" + month + "/" + year;
                disDob.setText(dateSel);
            }
        };

        //Save data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostData(edtFname.getText().toString(), edtLname.getText().toString(), disDob.getText().toString(), edtConNum.getText().toString(), edtEmail.getText().toString()).execute(Common.getUserAPI());
            }
        });


    }

    //Add new User
    class PostData extends AsyncTask<String, String, String> {

        ProgressDialog pd = new ProgressDialog(ActivityUserreg.this);
        String firstname;
        String lastname;
        String dob;
        String contactnum;
        String email;
        String username;

        public PostData(String firstname, String lastname, String dob, String contactnum, String email) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.dob = dob;
            this.contactnum = contactnum;
            this.email = email;
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
            username = firstname;//use a username builder

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{" +
                    "\"username\":\"" + username + "\"," +
                    "\"name\":{" +
                    "\"firstname\":\"" + firstname + "\"," +
                    "\"lastname\":\"" + lastname + "\"}," +
                    "\"dob\":\"" + dob + "\"," +
                    "\"contactNumber\":\"" + contactnum + "\"," +
                    "\"email\":\"" + email + "\"," +
                    "\"location\":\"" + "Colombo" + "\"," +
                    "\"roleName\":\"" + "Admin" + "\"" +
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
