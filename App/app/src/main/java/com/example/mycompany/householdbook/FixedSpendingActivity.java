package com.example.mycompany.householdbook;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class FixedSpendingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_spending);

        Button createNewButton = findViewById(R.id.createNewButton);
        createNewButton.setText("新規作成");
        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedSpendingActivity.this, CreateNewActivity.class);
                startActivity(intent);
            }
        });

        //TODO:固定支出の一覧を表示
        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedSpendingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedSpendingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedSpendingActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FixedSpendingActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }
}
