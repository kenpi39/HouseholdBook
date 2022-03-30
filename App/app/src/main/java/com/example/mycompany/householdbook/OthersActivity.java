package com.example.mycompany.householdbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OthersActivity extends AppCompatActivity {
    private OthersActivity other;
    private Common common;
    private HbDataManager hdm = new HbDataManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        common = (Common) this.getApplication();
        other = this;

        Button helpPageButton = findViewById(R.id.helpPageButton);
        helpPageButton.setText("使い方・ヘルプ");
        helpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OthersActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        Button fixedSpendingPageButton = findViewById(R.id.fixedSpendingPageButton);
        fixedSpendingPageButton.setText("固定支出の設定");
        fixedSpendingPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OthersActivity.this, FixedSpendingActivity.class);
                startActivity(intent);
            }
        });

        Button setNameButton = findViewById(R.id.setNameButton);
        setNameButton.setText("ユーザー名の設定");
        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(other);
                editText.setHint("ユーザー名");
                new AlertDialog.Builder(other)
                        .setTitle("ユーザー名の設定")
                        .setMessage("ユーザー名を入力してください")
                        .setView(editText)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SpannableStringBuilder sb = (SpannableStringBuilder)editText.getText();
                                String name = sb.toString();
                                common.setUserName(name);
                                System.out.println(name);
                            }
                        })
                        .show();
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setText("データのリセット");
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(other)
                        .setTitle("データのリセット")
                        .setMessage("全てのデータを消去しますが本当によろしいですか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hdm.resetDbData();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });



        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OthersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OthersActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OthersActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
