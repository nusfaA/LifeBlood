package com.graise.ansafn.managelifeblood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class ActivityMenu extends AppCompatActivity {

    private Button btnEligibility, btnStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnEligibility = (Button) findViewById(R.id.btnEligibility);
        btnStock = (Button) findViewById(R.id.btnStock);

        btnEligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityMngcriteria.class);
                startActivity(intent);
            }
        });

        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityStockManagement.class);
                startActivity(intent);
            }
        });
    }
}
