package com.rohan.manatkar.webviewapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent secondIntent = getIntent();
        String search = secondIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("en.wikipedia.org")
                .appendPath("wiki")
                .appendPath(search);

        String myUrl = builder.build().toString();

        myWebView = new WebView(this);
        setContentView(myWebView);
        //WebView here = (WebView) findViewById(R.id.web_activity);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(myUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}

