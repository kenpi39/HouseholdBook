package com.example.mycompany.householdbook;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;

public class HbDataManager {
    boolean fReadComplete = false;
    boolean totalComplete = false;
    List<String> readDataList = new ArrayList<String>();
    List<String> readTagList = new ArrayList<String>();
    List<Integer> readAmountList = new ArrayList<Integer>();
    int total;

    public void addDbData(int year , int month , int day ,String name, String memo, String tag, int amounts){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("Date", getDate(year, month + 1 , day));
        data.put("Name", name);
        data.put("Memo", memo);
        data.put("Tag", tag);
        data.put("Amounts", amounts);
        data.put("Type", 1);
        db.collection("HB_table")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("myFirestore", "added ID=" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("myFirestore", "Error adding document", e);
                    }
                });
    }

    public void resetDbData(){
        //TODO:データベースのデータを全て消去する
    }

    //TODO:月の合計金額を表示するメソッドの実装
    /*public void setTotalSpending(int year, int month){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        totalComplete = false;
        total = 0;
        System.out.println("getDateFirst(year, month)" + getDateFirst(year, month) + "getDateEnd(year, month)" + getDateEnd(year, month));
        db.collection("HB_table")
                .whereGreaterThanOrEqualTo("Date", getDateFirst(year, month))
                .whereLessThanOrEqualTo("Date", getDateEnd(year, month))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                total += Integer.parseInt(document.get("Amounts").toString());
                            }
                            System.out.println("total;" + total);
                        }
                        System.out.println("total22;" + total);
                        totalComplete = true;
                    }
                });
    }

    public String getTotalSpendingString(int year, int month) {
        setTotalSpending(year, month);
        return "一か月合計 " + total + "円";
    }*/

    public void setSpendingDataByDateEqual(int year, int month, int day) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        fReadComplete = false;
        readDataList.clear();
        Task<QuerySnapshot> tsk = db.collection("HB_table")
                .whereEqualTo("Date", getDate(year, month, day))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String str = document.get("Name").toString() + " " + document.get("Tag").toString() + " " + document.get("Memo").toString() + " " + document.get("Amounts").toString() + "円";
                                readDataList.add(str);
                                Log.d("myFirestore", document.getId() + " => " + document.get("Name").toString() + " " + document.get("Tag").toString() + " " + document.get("Memo").toString() + " " + document.get("Amounts").toString() + "円");
                            }
                        } else {
                            Log.d("myFirestore", "Error getting documents: ", task.getException());
                        }
                        fReadComplete = true;
                    }
                });
    }

    public void setSpendingDataBetweenDate(int year, int month){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        fReadComplete = false;
        readTagList.clear();
        readAmountList.clear();
        db.collection("HB_table")
                .whereGreaterThanOrEqualTo("Date", getDateFirst(year, month))
                .whereLessThanOrEqualTo("Date", getDateEnd(year, month))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                readTagList.add(document.get("Tag").toString());
                                readAmountList.add(Integer.parseInt(document.get("Amounts").toString()));
                                fReadComplete = true;
                            }
                        }
                    }
                });
    }

    private int getDate(int year, int month , int day){
        int date = year * 10000 + month * 100 + day;
        return date;
    }

    private int getDateFirst(int year, int month){
        int date = year * 10000 + month * 100 + 1;
        return date;
    }

    private int getDateEnd(int year, int month){
        int date = year * 10000 + month * 100 + 31;
        return date;
    }
}



