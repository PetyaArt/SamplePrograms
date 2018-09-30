package com.example.petya.photogallery;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoPageFragment extends VisibleFragment {

    private static final String ARG_URI = "photo_page_url";

    private Uri mUri;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static PhotoPageFragment newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        PhotoPageFragment fragment = new PhotoPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_page, container, false);

        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        mProgressBar.setMax(100); // Значения в диапазоне 0-100

        mWebView = (WebView) v.findViewById(R.id.web_view);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setSubtitle(title);
            }
        });

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        getActivity().onBackPressed();
                    }
                    return true;
                }
                return false;
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                openUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://www.apple.com/ru/itunes/download/");
        return v;
    }

    private void openUrl(String url) {
        Uri uri = Uri.parse(url);

        if (uri.getScheme().equals("http") || uri.getScheme().equals("https"))
            mWebView.loadUrl(url);
        else if (uri.getScheme().equals("intent")) {
            String appPackage = getAppPackageFromUri(uri);

            if (appPackage != null) {
                PackageManager manager = getContext().getPackageManager();
                Intent appIntent = manager.getLaunchIntentForPackage(appPackage);

                if (appIntent != null) {
                    getActivity().startActivity(appIntent);
                } else {
                    openExternalWebsite("https://play.google.com/store/apps/details?id=" + appPackage);
                }
            }
        }
    }

    private String getAppPackageFromUri(Uri intentUri) {
        Pattern pattern = Pattern.compile("package=(.*?);");
        Matcher matcher = pattern.matcher(intentUri.getFragment());

        if (matcher.find())
            return matcher.group(1);

        return null;
    }

    private void openExternalWebsite(String url) {
        Intent webeIntent = new Intent(Intent.ACTION_VIEW);
        webeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        webeIntent.setData(Uri.parse(url));
        getActivity().startActivity(webeIntent);
    }
}
