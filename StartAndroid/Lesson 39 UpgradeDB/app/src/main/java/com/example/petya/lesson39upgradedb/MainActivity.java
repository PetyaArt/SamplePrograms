package com.example.petya.lesson39upgradedb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    static final String LOG_TAG  = "myLogs";

    static final String DB_NAME = "staff"; //name db
    //static final int DB_VERSION = 1; //version db
    static final int DB_VERSION = 2; //version db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this, DB_NAME, null, DB_VERSION);
        SQLiteDatabase sqLiteOpenHelper = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, " --- Staff db v." + sqLiteOpenHelper.getVersion() + " --- ");
        writeStaff(sqLiteOpenHelper);
        dbHelper.close();
    }

    private void writeStaff(SQLiteDatabase sqLiteOpenHelper) {
        Cursor cursor = sqLiteOpenHelper.rawQuery("SELECT * FROM PEOPLE", null);
        logCursor(cursor, "Table people");
        cursor.close();

        cursor = sqLiteOpenHelper.rawQuery("SELECT * FROM POSITION", null);
        logCursor(cursor, "Table position");
        cursor.close();

        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id ";
        cursor = sqLiteOpenHelper.rawQuery(sqlQuery, null);
        logCursor(cursor, "inner join");
        cursor.close();
    }

    private void logCursor(Cursor cursor, String table_people) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.d(LOG_TAG, table_people + ". " + cursor.getCount() + " rows");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : cursor.getColumnNames()) {
                        sb.append(cn + " = "
                                + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, sb.toString());
                } while (cursor.moveToNext());
            }
        } else
            Log.d(LOG_TAG, table_people + ". Cursor is null");
    }
}
