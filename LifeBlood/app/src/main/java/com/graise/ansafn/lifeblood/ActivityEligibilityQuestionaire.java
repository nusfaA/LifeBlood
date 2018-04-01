package com.graise.ansafn.lifeblood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Babara on 24/03/2018.
 */

public class ActivityEligibilityQuestionaire extends AppCompatActivity {

    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility_questionaire);

        question = (TextView) findViewById(R.id.tvQuestion);


        findViewById(R.id.btnyes);
    }
}
