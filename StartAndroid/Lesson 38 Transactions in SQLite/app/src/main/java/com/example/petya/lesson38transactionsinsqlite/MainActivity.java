package com.example.petya.lesson38transactionsinsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    static final String LOG_TAG = "myLogs";
    private DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "--- onCreate Activity ---");
        dbHelper = new DBHelper(this, "myDB", null, 1);
        myActions();
    }

    private void myActions() {
        /*this.sqLiteDatabase = dbHelper.getWritableDatabase();
        delete(this.sqLiteDatabase, "mytable");
        insert(this.sqLiteDatabase, "mytable", "val1");
        read(this.sqLiteDatabase, "mytable");
        sqLiteDatabase.close();*/

        /*sqLiteDatabase = dbHelper.getWritableDatabase();
        delete(sqLiteDatabase, "mytable");
        sqLiteDatabase.beginTransaction();
        insert(sqLiteDatabase, "mytable", "val1");
        sqLiteDatabase.endTransaction();
        insert(sqLiteDatabase, "mytable", "val2");
        read(sqLiteDatabase, "mytable");
        sqLiteDatabase.close();*/

       /* sqLiteDatabase = dbHelper.getWritableDatabase();
        delete(sqLiteDatabase, "mytable");
        sqLiteDatabase.beginTransaction();
        insert(sqLiteDatabase, "mytable", "val1");
        sqLiteDatabase.setTransactionSuccessful();
        insert(sqLiteDatabase, "mytable", "val2");
        sqLiteDatabase.endTransaction();
        insert(sqLiteDatabase, "mytable", "val3");
        read(sqLiteDatabase, "mytable");
        dbHelper.close();*/

        try {
            sqLiteDatabase = dbHelper.getWritableDatabase();
            delete(sqLiteDatabase, "mytable");

            /*sqLiteDatabase.beginTransaction();*/
            insert(sqLiteDatabase, "mytable", "val1");

            Log.d(LOG_TAG, "create DBHelper");
            DBHelper dbh2 = new DBHelper(this, "myDB", null, 1);
            Log.d(LOG_TAG, "get db");
            SQLiteDatabase db2 = dbh2.getWritableDatabase();
            read(db2, "mytable");
            dbh2.close();

            /*sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();*/

            read(sqLiteDatabase, "mytable");
            dbHelper.close();

        } catch (Exception ex) {
            Log.d(LOG_TAG, ex.getClass() + " error: " + ex.getMessage());
        }

    }

    private void insert(SQLiteDatabase sqLiteDatabase, String mytable, String val1) {
        Log.d(LOG_TAG, "Insert in table " + mytable + " value = " + val1);
        ContentValues cv = new ContentValues();
        cv.put("val", val1);
        sqLiteDatabase.insert(mytable, null, cv);
    }

    void read(SQLiteDatabase db, String table) {
        Log.d(LOG_TAG, "Read table " + table);
        Cursor c = db.query(table, null, null, null, null, null, null);
        if (c != null) {
            Log.d(LOG_TAG, "Records count = " + c.getCount());
            if (c.moveToFirst()) {
                do {
                    Log.d(LOG_TAG, c.getString(c.getColumnIndex("val")));
                } while (c.moveToNext());
            }
            c.close();
        }
    }

    private void delete(SQLiteDatabase sqLiteDatabase, String mytable) {
        Log.d(LOG_TAG, "Delete all from table " + mytable);
        sqLiteDatabase.delete(mytable, null, null);
    }
}
