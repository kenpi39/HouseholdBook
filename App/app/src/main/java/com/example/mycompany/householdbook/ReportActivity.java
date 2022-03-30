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
import android.widget.ListView;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReportActivity extends AppCompatActivity {
    private ReportActivity report;
    final Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH) + 1;
    private HbDataManager hdm = new HbDataManager();
    private String tagsOriginal[] = {"食費", "日用品", "娯楽費", "交際費","交通費", "住居費", "光熱費", "通信費", "車両費",
            "医療費", "被服費", "美容費", "学費", "雑費"};
    private String tags[];
    private int amounts[];
    private List<String> spendingArray = new ArrayList<>();
    private List<PieEntry> pieEntries = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report = this;

        TextView yearMonthText = findViewById(R.id.yearMonthText);
        yearMonthText.setText(year + "年" + month + "月");

        Button lastMonthButton = findViewById(R.id.lastMonthButton);
        lastMonthButton.setText("先月");
        lastMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FIXME:先月へ移動するとアプリが落ちる
                //java.lang.ArrayIndexOutOfBoundsException: length=14; index=-1
                //ReportActivity.java:153
                calendar.add(Calendar.MONTH, -1);
                setMonth(calendar);
                setupPieChart();
            }
        });

        Button nextMonthButton = findViewById(R.id.nextMonthButton);
        nextMonthButton.setText("翌月");
        nextMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                setMonth(calendar);
                setupPieChart();
            }
        });

        setupPieChart();

        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setMonth(Calendar calendar){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        TextView yearMonthText = findViewById(R.id.yearMonthText);
        yearMonthText.setText(year + "年" + month + "月");
    }

    private void setupPieChart() {
        pieEntries.clear();
        spendingArray.clear();
        hdm.setSpendingDataBetweenDate(year, month);
        tags = tagsOriginal;
        amounts = new int[14];
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                final Handler mainHandler = new Handler(Looper.getMainLooper());
                if (hdm.fReadComplete) {
                    mainHandler.post(() -> {
                        for (int i = 0; i < hdm.readAmountList.size(); i++) {
                            String tag = hdm.readTagList.get(i);
                            int index = Arrays.asList(tags).indexOf(tag);
                            amounts[index] += hdm.readAmountList.get(i);
                        }
                        for (int i = 0; i < amounts.length; i++){
                            if (amounts[i] == 0){
                                tags[i] = "";
                            }
                        }
                        for (int i = 0; i < amounts.length; i++) {
                            pieEntries.add(new PieEntry(amounts[i], tags[i]));
                        }
                        PieDataSet dataSet = new PieDataSet(pieEntries, "datas");
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        dataSet.setValueTextSize(0f);
                        PieData data = new PieData(dataSet);
                        PieChart piechart = (PieChart) findViewById(R.id.pieChart);
                        piechart.setData(data);
                        piechart.getDescription().setEnabled(false);
                        piechart.getLegend().setEnabled(false);
                        piechart.invalidate();
                        //TODO:spendingListに表示させるものをタグだけでなくユーザー名でも分けるようにする
                        for (int i = 0; i < amounts.length; i++){
                            if (amounts[i] != 0){
                                spendingArray.add(tags[i] + " " + amounts[i] + "円");
                            }
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(report, android.R.layout.simple_list_item_1, spendingArray);
                        ListView spendingList = findViewById(R.id.spendingList);
                        spendingList.setAdapter(arrayAdapter);
                        timer.cancel();
                    });
                }
            }
        };
        timer.schedule(tt, 0, 200);
    }
}