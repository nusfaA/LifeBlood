package com.graise.ansafn.managelifeblood;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import java.util.Random;

/**
 * Created by ansaf.n on 21/3/2018.
 * Summary : registration for admin user and medical practitioners
 */

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

        //password
        Random r = new Random();
        int i1 = r.nextInt(9 - 1) + 0;
        int i2 = r.nextInt(9 - 1) + 0;
        int i3 = r.nextInt(9 - 1) + 0;
        int i4 = r.nextInt(9 - 1) + 0;

        String password = String.valueOf(i1) + String.valueOf(i2) + String.valueOf(i3) + String.valueOf(i4);

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
                    "\"roleName\":\"" + "Admin" + "\"," +
                    "\"password\":\"" + password + "\"" +
                    "}";

            hh.PostHTTPData(urlString, json);

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.dismiss();

            AlertDialog alertDialog = new AlertDialog.Builder(ActivityUserreg.this).create();
            alertDialog.setTitle("Success");
            alertDialog.setMessage("Your Account successfully is created. Your Username is " + username + " and your Password is " + password + " !");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }

    }

    private void reset() {
        edtFname.setText("");
        edtLname.setText("");
        edtConNum.setText("");
        edtEmail.setText("");
        disDob.setText(null);
    }
}
