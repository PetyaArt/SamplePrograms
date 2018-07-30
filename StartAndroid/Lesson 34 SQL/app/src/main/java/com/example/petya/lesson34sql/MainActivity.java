package com.example.petya.lesson34sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final static String LOG_TAG = "myLogs";

    Button buttonAdd, buttonRead, buttonClear;
    EditText editTextName, editTextEmail;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonRead = findViewById(R.id.buttonRead);
        buttonClear = findViewById(R.id.buttonClear);

        buttonAdd.setOnClickListener(this);
        buttonRead.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);

        dbHelper = new DBHelper(this, "myDB", null, 1);
    }

    @Override
    public void onClick(View view) {
        ContentValues contentValues = new ContentValues();

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); // Позволяет работать с БД

        switch (view.getId()) {
            case R.id.buttonAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");

                contentValues.put("name", name);
                contentValues.put("email", email);

                long rowID = sqLiteDatabase.insert("mytable", null, contentValues);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);

                break;
            case R.id.buttonRead:
                Log.d(LOG_TAG, "--- Rows in mytable: ---");
                Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idColIndex = cursor.getColumnIndex("id");
                    int nameColIndex = cursor.getColumnIndex("name");
                    int emailColIndex = cursor.getColumnIndex("email");

                    do {
                        Log.d(LOG_TAG,
                                "ID = " + cursor.getInt(idColIndex) +
                                        ", name = " + cursor.getString(nameColIndex) +
                                        ", email = " + cursor.getString(emailColIndex));
                    } while (cursor.moveToNext());
                }
                else {
                    Log.d(LOG_TAG, "0 rows");
                }
                cursor.close();
                break;
            case R.id.buttonClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");

                int clearCount = sqLiteDatabase.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);

                break;
        }

        sqLiteDatabase.close();

    }
}
