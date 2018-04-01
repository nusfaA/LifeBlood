package com.graise.ansafn.managelifeblood;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;
import com.graise.ansafn.managelifeblood.User.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    Button btnLogIn;
    private EditText edtUsername, edtPassword;

    User userSel = new User();
    List<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogIn = (Button) findViewById(R.id.btnLog);

        new GetData().execute(Common.getUserAPI());

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userList != null) {
                    boolean userFound = false;
                    boolean unsuccessful = true;
                    for (User user : userList) {
                        if (user.getUsername().equalsIgnoreCase(edtUsername.getText().toString())) {
                            userFound = true;
                            if (user.getPassword().equalsIgnoreCase(edtPassword.getText().toString())) {
                                unsuccessful = false;
                                Intent intent = new Intent(ActivityLogin.this, ActivityMenu.class);
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                    if (userFound && unsuccessful) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ActivityLogin.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Incorrect Password!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else if (!userFound && unsuccessful) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ActivityLogin.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Incorrect User Name!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(ActivityLogin.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("No Users Found!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    //process data
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityLogin.this);

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
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            userList = gson.fromJson(s, listType);

            pd.dismiss();

        }

    }


}
