package com.example.petya.lesson51simpleadapteraddanddel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView listView;
    SimpleAdapter simpleAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        data = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
           m = new HashMap<>();
           m.put(ATTRIBUTE_NAME_TEXT, "sometext" + i);
           m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
           data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};

        int[] to = {R.id.textView, R.id.imageView};

        simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        listView = findViewById(R.id.lvSimple);
        listView.setAdapter(simpleAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(adapterContextMenuInfo.position);
            simpleAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    public void onButtonClick(View v) {
        m = new HashMap<>();
        m.put(ATTRIBUTE_NAME_TEXT, "sometext" + (data.size() + 1));
        m.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
        data.add(m);
        simpleAdapter.notifyDataSetChanged();
    }
}
