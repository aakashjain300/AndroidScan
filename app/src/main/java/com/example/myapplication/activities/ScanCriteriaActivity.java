package com.example.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CriteriaAdapter;
import com.example.myapplication.model.Scan;
import com.example.myapplication.utilities.Constants;

/**
 * Created by Aakash on 08/05/2019.
 */

public class ScanCriteriaActivity extends AppCompatActivity {

    TextView txtScanName, txtScanStatus;
    RecyclerView recyclerCriteria;

    CriteriaAdapter criteriaAdapter;

    Scan selectedScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        txtScanName = findViewById(R.id.txtScanName);
        txtScanStatus = findViewById(R.id.txtScanStatus);
        recyclerCriteria = findViewById(R.id.recyclerCriteria);

        recyclerCriteria.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCriteria.setLayoutManager(layoutManager);


        getIntentData();
        setData();
    }

    void getIntentData() {
        selectedScan = (Scan) getIntent().getSerializableExtra(Constants.selectedScan);
    }

    void setData() {
        txtScanName.setText(selectedScan.getName());
        txtScanStatus.setText(selectedScan.getTag());

        if(selectedScan.getColor().equals(Constants.green))
            txtScanStatus.setTextColor(getResources().getColor(R.color.green));
        else
            txtScanStatus.setTextColor(getResources().getColor(R.color.red));


        criteriaAdapter = new CriteriaAdapter(this, selectedScan.getCriteria());
        recyclerCriteria.setAdapter(criteriaAdapter);

    }
}
