package com.example.petya.lesson35sqlitemethodupdateanddelete;

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

    static final String LOG_TAG = "myLogs";

    Button buttonAdd, buttonClear, buttonRead, buttonUpdate, buttonDelete;
    EditText editTextID, editTextName, editTextEmail;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonClear = findViewById(R.id.buttonRead);
        buttonUpdate = findViewById(R.id.buttonClear);
        buttonAdd.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextID = findViewById(R.id.editTextID);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);

        dbHelper = new DBHelper(this, "mydb", null, 1);

    }

    @Override
    public void onClick(View view) {
        ContentValues cv = new ContentValues();

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String id = editTextID.getText().toString();

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.buttonAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");

                cv.put("name", name);
                cv.put("email", email);

                long rowID = sqLiteDatabase.insert("mytable", null, cv);
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
                                "ID = " + cursor.getInt(idColIndex) + ", name = "
                                        + cursor.getString(nameColIndex) + ", email = "
                                        + cursor.getString(emailColIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                cursor.close();
                break;
            case R.id.buttonClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                int clearCount = sqLiteDatabase.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
            case R.id.buttonUpdate:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "--- Update mytable: ---");
                cv.put("name", name);
                cv.put("email", email);

                int updateCount = sqLiteDatabase.update("mytable", cv, "id = ?", new String[] {id});
                Log.d(LOG_TAG, "updated rows count = " + updateCount);
                break;
            case R.id.buttonDelete:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                int delCount = sqLiteDatabase.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        dbHelper.close();
    }
}
