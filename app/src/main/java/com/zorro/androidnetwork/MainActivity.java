package com.zorro.androidnetwork;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zorro.networking.AndroidNetworking;
import com.zorro.networking.error.ANError;
import com.zorro.networking.interceptors.HttpLoggingInterceptor;
import com.zorro.networking.interceptors.RetryInterceptor;
import com.zorro.networking.interfaces.OkHttpResponseAndStringRequestListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new RetryInterceptor(2, 3000))
                .build();
        AndroidNetworking.initialize(this, client);
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }

    //请求登录接口
    public void request() {
        AndroidNetworking.get("http://publicobject.com/helloworld.txt")
                .setTag("login")
                .build()
                .getAsOkHttpResponseAndString(new OkHttpResponseAndStringRequestListener() {
                    @Override
                    public void onResponse(Response okHttpResponse, String response) {
                        text.setText(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
