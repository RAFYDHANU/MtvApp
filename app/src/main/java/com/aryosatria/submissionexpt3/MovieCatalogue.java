package com.aryosatria.submissionexpt3;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;

public class MovieCatalogue extends Application {

    public static final String MOVIE_DB_API_KEY = "9a9cdf7996d43678b35076aca17ff21b";
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API",message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);

        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
