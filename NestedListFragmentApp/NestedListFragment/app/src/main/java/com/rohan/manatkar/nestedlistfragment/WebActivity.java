package com.rohan.manatkar.nestedlistfragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent secondIntent = this.getIntent();
        String search = secondIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        SecondActivityFragment2 frag = new SecondActivityFragment2();
        frag.init(search);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, frag).commit();
    }
}
