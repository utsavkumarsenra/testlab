package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.R;

public class WebView extends AppCompatActivity {
    private android.webkit.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        webView = (android.webkit.WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}