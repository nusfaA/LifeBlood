package com.graise.ansafn.lifeblood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityExistingDonor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_donor);
    }

    public void checkEligibility(View view) {
        Intent intent = new Intent(ActivityExistingDonor.this, ActivityEligibilityDetection.class);
        startActivity(intent);
    }
}
