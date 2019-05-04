package com.rohan.manatkar.nestedlistfragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondActivityFragment2 extends Fragment {

    private WebView myWebView;
    private String search;

    public SecondActivityFragment2() {
        // Required empty public constructor
    }

    public void init(String type) {
        search = type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second_activity_fragment2, container, false);

        myWebView = (WebView) v.findViewById(R.id.web_fragment);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("en.wikipedia.org")
                .appendPath("wiki")
                .appendPath(search);

        String myUrl = builder.build().toString();

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(myUrl);

        return v;
    }

    public void updateWebView(String type) {
        myWebView = (WebView) getView().findViewById(R.id.web_fragment);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("en.wikipedia.org")
                .appendPath("wiki")
                .appendPath(type);

        String myUrl = builder.build().toString();

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(myUrl);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.getActivity().onKeyDown(keyCode, event);
    }
}
