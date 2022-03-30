package com.example.mycompany.householdbook;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //TODO:各項目に説明を追加する
        ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String,String>>();
        ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String,String>>>();

        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "データの入力について");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "カレンダーについて");
        HashMap<String, String> groupC = new HashMap<String, String>();
        groupC.put("group", "レポートについて");

        groupData.add(groupA);
        groupData.add(groupB);
        groupData.add(groupC);

        ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> childAA = new HashMap<String, String>();
        childAA.put("group", "データの入力について");
        childAA.put("name", "日付選択");
        HashMap<String, String> childAB = new HashMap<String, String>();
        childAB.put("group", "データの入力について");
        childAB.put("name", "メモ");
        HashMap<String, String> childAC = new HashMap<String, String>();
        childAC.put("group", "データの入力について");
        childAC.put("name", "金額");
        HashMap<String, String> childAD = new HashMap<String, String>();
        childAD.put("group", "データの入力について");
        childAD.put("name", "タグ");
        HashMap<String, String> childAE = new HashMap<String, String>();
        childAE.put("group", "データの入力について");
        childAE.put("name", "ユーザー名");

        childListA.add(childAA);
        childListA.add(childAB);
        childListA.add(childAC);
        childListA.add(childAD);
        childListA.add(childAE);

        childData.add(childListA);

        ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> childBA = new HashMap<String, String>();
        childBA.put("group", "カレンダーについて");
        childBA.put("name", "カレンダー");
        HashMap<String, String> childBB = new HashMap<String, String>();
        childBB.put("group", "カレンダーについて");
        childBB.put("name", "総支出");

        childListB.add(childBA);
        childListB.add(childBB);

        childData.add(childListB);

        ArrayList<HashMap<String, String>> childListC = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childCA = new HashMap<String, String>();
        childCA.put("group", "レポートについて");
        childCA.put("name", "レポート");
        HashMap<String, String> childCB = new HashMap<String, String>();
        childCB.put("group", "レポートについて");
        childCB.put("name", "総支出");

        childListC.add(childCA);
        childListC.add(childCB);

        childData.add(childListC);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getApplicationContext(),
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] {"group"},
                new int[] { android.R.id.text1 },
                childData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {"name", "group"},
                new int[] { android.R.id.text1, android.R.id.text2 });

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.helpList);
        listView.setAdapter(adapter);

        Button mainPageButton = findViewById(R.id.mainPageButton);
        mainPageButton.setText("支出入力");
        mainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button calendarPageButton = findViewById(R.id.calendarPageButton);
        calendarPageButton.setText("カレンダー");
        calendarPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        Button reportPageButton = findViewById(R.id.reportPageButton);
        reportPageButton.setText("レポート");
        reportPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button othersPageButton = findViewById(R.id.othersPageButton);
        othersPageButton.setText("その他");
        othersPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }
}
