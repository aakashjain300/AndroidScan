package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ScansListAdapter;
import com.example.myapplication.model.Scan;
import com.example.myapplication.network.APIInterface;
import com.example.myapplication.utilities.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aakash on 07/05/2019.
 */

public class ScanListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listviewScans;

    Retrofit retrofit;
    APIInterface apiInterface;
    ScansListAdapter scansListAdapter;

    ArrayList<Scan> dataArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scans);

        listviewScans = (ListView) findViewById(R.id.listScans);

        SetAPIBuilder();
    }

    void SetAPIBuilder() {

        retrofit = new Retrofit.Builder().baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiInterface = retrofit.create(APIInterface.class);

        callAPI();
    }

    void callAPI() {

        Constants.showProgressDialog(this);

        Call<ArrayList<Scan>> call = apiInterface.getAllScans();

        call.enqueue(new Callback<ArrayList<Scan>>() {
            @Override
            public void onResponse(Call<ArrayList<Scan>> call, Response<ArrayList<Scan>> response) {

                if (response != null) {
                    dataArray = response.body();
                    if (dataArray != null) {


                        scansListAdapter = new ScansListAdapter(ScanListActivity.this, dataArray);
                        listviewScans.setAdapter(scansListAdapter);

                        listviewScans.setOnItemClickListener(ScanListActivity.this);
                    } else {
                        Toast.makeText(ScanListActivity.this, R.string.norecords, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ScanListActivity.this, R.string.norecords, Toast.LENGTH_SHORT).show();
                }
                Constants.closeProgressDialog();
            }

            @Override
            public void onFailure(Call<ArrayList<Scan>> call, Throwable t) {
                Constants.closeProgressDialog();
                Toast.makeText(ScanListActivity.this, R.string.norecords, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Scan selectedScan = dataArray.get(i);
        Intent intent = new Intent(ScanListActivity.this, ScanCriteriaActivity.class);
        intent.putExtra(Constants.selectedScan, selectedScan);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

    }
}
