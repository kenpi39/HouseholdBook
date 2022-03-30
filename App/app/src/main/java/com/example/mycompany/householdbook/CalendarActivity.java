package com.example.mycompany.householdbook;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class CalendarActivity extends AppCompatActivity {
    private CalendarActivity calendarActivity;
    private HbDataManager hdm = new HbDataManager();
    private int selectYear;
    private int selectMonth;
    private int selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarActivity = this;
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                selectYear = eventDay.getCalendar().get(Calendar.YEAR);
                selectMonth = eventDay.getCalendar().get(Calendar.MONTH) + 1;
                selectDay = eventDay.getCalendar().get(Calendar.DAY_OF_MONTH);
                if (selectYear == headerYear && selectMonth == headerMonth){
                    hdm.setSpendingDataByDateEqual(selectYear, selectMonth, selectDay);
                    Timer timer = new Timer();
                    TimerTask tt = new TimerTask () {
                        public void run() {
                            final Handler mainHandler = new Handler(Looper.getMainLooper());
                            if (hdm.fReadComplete) {
                                mainHandler.post(() -> {
                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(calendarActivity, android.R.layout.simple_list_item_1, hdm.readDataList);
                                    ListView spendingList = findViewById(R.id.spendingList);
                                    spendingList.setAdapter(arrayAdapter);
                                    timer.cancel();
                                });
                            }
                        }
                    };
                    timer.schedule(tt, 0, 200);
                }else {

                }
            }
        });

        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }
}
