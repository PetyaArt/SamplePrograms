package com.example.petya.lesson53simplecursortreeadapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorTreeAdapter;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        Cursor cursor = db.getCompanyData();
        startManagingCursor(cursor);

        String[] groupFrom = { DB.COMPANY_COLUMN_NAME };
        int[] groupTo = { android.R.id.text1 };

        String[] childFrom = { DB.PHONE_COLUMN_NAME };
        int[] childTo = { android.R.id.text1 };

        SimpleCursorTreeAdapter simpleCursorAdapter = new MyAdapter(this, cursor,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, android.R.layout.simple_list_item_1, childFrom,
                childTo);

        expandableListView = findViewById(R.id.elvMain);
        expandableListView.setAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    class MyAdapter extends SimpleCursorTreeAdapter {


        public MyAdapter(Context context, Cursor cursor, int groupLayout,
                         String[] groupFrom, int[] groupTo, int childLayout,
                         String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childFrom, childTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor cursor) {
            int idColumn = cursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            return db.getPhoneData(cursor.getInt(idColumn));
        }
    }
}
