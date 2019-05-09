package com.example.myapplication.network;

import com.example.myapplication.model.Scan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aakash on 08/05/2019.
 */

public interface APIInterface {

    @GET("data")
    Call<ArrayList<Scan>> getAllScans();

}
