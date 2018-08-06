package com.example.petya.lesson55headerandfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    String[] mData = {"one", "two", "three", "four", "five"};
    ListView mListView;
    ArrayAdapter<String> mAdapter;

    View header1;
    View header2;

    View footer1;
    View footer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    private void fillList() {
        mListView.addHeaderView(header1);
        mListView.addHeaderView(header2, "some text for header 2", false);
        mListView.addFooterView(footer1);
        mListView.addFooterView(footer2, "some text for footer 2", false);
        mListView.setAdapter(mAdapter);
    }

    public void onclick(View v) {
        Object obj;
        HeaderViewListAdapter hvlAdapter = (HeaderViewListAdapter) mListView.getAdapter();
        obj = hvlAdapter.getItem(1);
        Log.d(LOG_TAG, "hvlAdapter.getItem(1) = " + obj.toString());
        obj = hvlAdapter.getItem(4);
        Log.d(LOG_TAG, "hvlAdapter.getItem(4) = " + obj.toString());

        ArrayAdapter<String> alAdapter = (ArrayAdapter<String>) hvlAdapter.getWrappedAdapter();
        obj = alAdapter.getItem(1);
        Log.d(LOG_TAG, "alAdapter.getItem(1) = " + obj.toString());
        obj = alAdapter.getItem(4);
        Log.d(LOG_TAG, "alAdapter.getItem(4) = " + obj.toString());
    }


    private View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return  v;
    }

    private View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return  v;
    }

}
