package com.example.mycompany.householdbook;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import static android.icu.util.Calendar.getInstance;

public class CreateNewActivity extends AppCompatActivity {
    CreateNewActivity createNew;
    int selectedStartYear;
    int selectedStartMonth;
    int selectedStartDay;
    int selectedEndYear;
    int selectedEndMonth;
    int selectedEndDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);
        createNew = this;
        final Calendar calendar = getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Button setStartButton = findViewById(R.id.setStartButton);
        setStartButton.setText("開始日：");
        setStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(createNew,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            setStartButton.setText("開始日：" + getDateString(year, monthOfYear, dayOfMonth));
                            selectedStartYear = year;
                            selectedStartMonth = monthOfYear + 1;
                            selectedStartDay = dayOfMonth;
                        }
                    },
                    year, month, day);
                datePickerDialog.show();
            }
        });

        Button setEndButton = findViewById(R.id.setEndButton);
        setEndButton.setText("終了日：");
        setEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(createNew,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                setEndButton.setText("終了日：" + getDateString(year, monthOfYear, dayOfMonth));
                                selectedEndYear = year;
                                selectedEndMonth = monthOfYear + 1;
                                selectedEndDay = dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }

    private  String getDateString(int year, int month , int day){
        return year + "年" + (month + 1) + "月" + day + "日";
    }
}
