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
    String returnStr;
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

        Button setReturnButton = findViewById(R.id.setReturnButton);
        setReturnButton.setText("繰り返し方法：");
        setReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(createNew);
                builder.setTitle("繰り返し方法")
                        .setItems(new CharSequence[]{
                                "毎日", "毎週", "毎月", "キャンセル"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == 0){
                                    returnStr = "everyDay";
                                    setReturnButton.setText("繰り返し方法：毎日");
                                }else if (which == 1){
                                    returnStr = "everyWeek";
                                    setReturnButton.setText("繰り返し方法：毎週");
                                }else if (which == 2){
                                    returnStr = "everyMonth";
                                    setReturnButton.setText("繰り返し方法：毎月");
                                }else {

                                }
                            }
                        }
                );
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button setHolidayButton = findViewById(R.id.setHolidayButton);
        setHolidayButton.setText("土日祝日の場合：");
        setHolidayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(createNew);
                builder.setTitle("土日祝日の場合")
                        .setItems(new CharSequence[]{
                                "何もしない", "該当の日を直前の平日にする", "該当の日を直後の平日にする", "キャンセル"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == 0){
                                    setHolidayButton.setText("土日祝日の場合：何もしない");
                                }else if (which == 1){
                                    //TODO:該当の日を直前の平日にする
                                    setHolidayButton.setText("土日祝日の場合：該当の日を直前の平日にする");
                                }else if (which == 2){
                                    //TODO:該当の日を直後の平日にする
                                    setHolidayButton.setText("土日祝日の場合：該当の日を直後の平日にする");
                                }else {

                                }
                            }
                        }
                );
                AlertDialog dialog = builder.create();
                dialog.show();
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
