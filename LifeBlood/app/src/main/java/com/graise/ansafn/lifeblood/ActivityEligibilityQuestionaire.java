package com.graise.ansafn.lifeblood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.graise.ansafn.lifeblood.EligibilityQuestion.EligibilityQuestion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babara on 24/03/2018.
 */

public class ActivityEligibilityQuestionaire extends AppCompatActivity {

    int qNo;
    TextView question;
    List<EligibilityQuestion> questionList = new ArrayList<EligibilityQuestion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility_questionaire);

        question = (TextView) findViewById(R.id.tvQuestion);

        //load data
        //new GetData().execute(Common.getRequestAPI());

        questionList.add(new EligibilityQuestion(1, "Have you ever had a positive test for the AIDS virus? ", 2));
        questionList.add(new EligibilityQuestion(2, "Have you traveled in the past year, or lived in the past three years, in an area where malaria is endemic?", 3));
        questionList.add(new EligibilityQuestion(3, "Have you had a tattoo in the past 12 months or received a blood transfusion (except with your own blood) in the past 12 months? ", 4));
        questionList.add(new EligibilityQuestion(4, "Have you used injectable drugs, including anabolic steroids within 24 hours?.", 0));

        question.setText(questionList.get(0).getQuestion());
        qNo = questionList.get(0).getNextQNo();

        findViewById(R.id.btnyes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.btnyes).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnno).setVisibility(View.INVISIBLE);
                findViewById(R.id.imgHome).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btnno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qNo != 0 &&
                        questionList.get(qNo).getQuestion() != null &&
                        questionList.get(qNo).getQuestion() != "") {
                    question.setText(questionList.get(qNo).getQuestion());
                    qNo = questionList.get(qNo).getNextQNo();
                } else {
                    findViewById(R.id.btnyes).setVisibility(View.INVISIBLE);
                    findViewById(R.id.btnno).setVisibility(View.INVISIBLE);
                    findViewById(R.id.imgHome).setVisibility(View.VISIBLE);
                    question.setText("YOU ARE ELIGIBLE TO DONATE BLOOD");
                }
            }
        });


        findViewById(R.id.btnyes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEligibilityQuestionaire.this, ActivityExistingDonor.class);
                startActivity(intent);
            }
        });


    }


    //process data
    class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog pd = new ProgressDialog(ActivityEligibilityQuestionaire.this);

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
            /*Type listType = new TypeToken<List<DonationRequest>>() {
            }.getType();
            questionList = gson.fromJson(s, listType);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), questionList);
            lstRequest.setAdapter(adapter);
            pd.dismiss();*/
        }

    }
}
