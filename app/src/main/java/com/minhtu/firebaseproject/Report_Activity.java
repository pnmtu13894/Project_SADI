package com.minhtu.firebaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Report_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
