package com.example.mycompany.householdbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 2022/02/02.
 */

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Button mainPageButton = findViewById(R.id.mainPageButton);

        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);

        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);

        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);

        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }
}
