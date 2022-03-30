package com.example.mycompany.householdbook;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import static android.icu.util.Calendar.getInstance;

public class MainActivity extends AppCompatActivity {
    private MainActivity main;
    private Common common;
    private HbDataManager hdm = new HbDataManager();
    int selectedYear;
    int selectedMonth;
    int selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = this;
        common = (Common)this.getApplication();
        final Calendar calendar = getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final Button daySelectButton = findViewById(R.id.daySelectButton);
        daySelectButton.setText(getDateString(year, month, day));
        daySelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog(main,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                daySelectButton.setText(getDateString(year, monthOfYear, dayOfMonth));
                                selectedYear = year;
                                selectedMonth = monthOfYear;
                                selectedDay = dayOfMonth;
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        RadioGroup tagRadioGroup = findViewById(R.id.tagRadioGroup);
        int checkedId = tagRadioGroup.getCheckedRadioButtonId();

        final Button finishButton = findViewById(R.id.finishButton);
        finishButton.setText("入力完了");
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText memoText = findViewById(R.id.memo);
                SpannableStringBuilder sbm = (SpannableStringBuilder)memoText.getText();
                String memo = sbm.toString();

                RadioGroup tagRadioGroup = findViewById(R.id.tagRadioGroup);
                int checkedId = tagRadioGroup.getCheckedRadioButtonId();
                String tag = "";
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                tag = radioButton.getText().toString();

                EditText amountsText = findViewById(R.id.amounts);
                SpannableStringBuilder sba = (SpannableStringBuilder)amountsText.getText();
                String str = sba.toString();

                int amounts = Integer.parseInt(sba.toString());

                if (common.getUserName() == null){
                    new AlertDialog.Builder(main)
                            .setTitle("ユーザー名の未設定")
                            .setMessage("ユーザー名を設定してください")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(main, OthersActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }else {
                    hdm.addDbData(selectedYear, selectedMonth, selectedDay, common.getUserName(), memo, tag, amounts);
                }
            }
        });

        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }

    private  String getDateString(int year, int month , int day){
        return year + "年" + (month + 1) + "月" + day + "日";
    }
}
