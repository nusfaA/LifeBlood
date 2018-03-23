package com.graise.ansafn.managelifeblood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {

    Button btnSgnUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogIn = (Button)findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //authenticate login.....

                //once logged in
                Intent intent = new Intent(ActivityMain.this,ActivityMenu.class);
                startActivity(intent);

            }
        });

        btnSgnUp = (Button)findViewById(R.id.btnSignUp);
        btnSgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivityUserreg.class);
                startActivity(intent);
            }
        });
    }
}
