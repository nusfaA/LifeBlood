package com.graise.ansafn.managelifeblood;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityUserreg extends AppCompatActivity {

    private static final String TAG = "ActivityUserreg";

    private ImageButton btnClander;
    private DatePickerDialog.OnDateSetListener listDateSet;
    private TextView disDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);

        disDob = (TextView) findViewById(R.id.disDob);

        btnClander = (ImageButton)findViewById(R.id.btnCalander);
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
                        year,month,day);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDialog.show();
            }
        });

        listDateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG, dayOfMonth +"/"+month +"/" + year);

                String dateSel = dayOfMonth +"/"+month +"/" + year;
                disDob.setText(dateSel);
            }
        };
    }
}
