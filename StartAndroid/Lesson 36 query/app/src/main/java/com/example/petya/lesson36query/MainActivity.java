package com.example.petya.lesson36query;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final String LOG_TAG = "myLogs";

    String name[] = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    Button buttonAll, buttonFunction, buttonPopulation, buttonRegion, buttonRegion2, buttonSort;
    EditText editTextFunction, editTextPopulation, editTextRegion2;
    RadioGroup radioGroupSort;

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAll = findViewById(R.id.buttonAll);
        buttonFunction = findViewById(R.id.buttonFunction);
        buttonPopulation = findViewById(R.id.buttonPopulation);
        buttonRegion = findViewById(R.id.buttonRegion);
        buttonRegion2 = findViewById(R.id.buttonRegion2);
        buttonSort = findViewById(R.id.buttonSort);

        buttonAll.setOnClickListener(this);
        buttonFunction.setOnClickListener(this);
        buttonPopulation.setOnClickListener(this);
        buttonRegion.setOnClickListener(this);
        buttonRegion2.setOnClickListener(this);
        buttonSort.setOnClickListener(this);

        editTextFunction = findViewById(R.id.editTextFunction);
        editTextPopulation = findViewById(R.id.editTextPopulation);
        editTextRegion2 = findViewById(R.id.editTextRegion2);

        radioGroupSort = findViewById(R.id.radioSort);

        dbHelper = new DBHelper(this, "myDB", null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);

        if (cursor.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < name.length; i++) {
                contentValues.put("name", name[i]);
                contentValues.put("people", people[i]);
                contentValues.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + sqLiteDatabase.insert("mytable", null, contentValues));
            }
        }

        cursor.close();
        dbHelper.close();

        onClick(buttonAll);

    }

    @Override
    public void onClick(View view) {

        sqLiteDatabase = dbHelper.getWritableDatabase();

        String sFunc = editTextFunction.getText().toString();
        String sPeople = editTextPopulation.getText().toString();
        String sRegionPeople = editTextRegion2.getText().toString();

        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = null;

        switch (view.getId()) {
            case R.id.buttonAll:
                Log.d(LOG_TAG, "--- Все записи ---");

                cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);

                break;
            case R.id.buttonFunction:
                Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");

                columns = new String[] { sFunc };
                cursor = sqLiteDatabase.query("mytable", columns, null, null, null, null, null);

                break;
            case R.id.buttonPopulation:
                Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");

                selection = "people > ?";
                selectionArgs = new String[] { sPeople };
                cursor = sqLiteDatabase.query("mytable", null, selection, selectionArgs, null, null,
                        null);

                break;
            case R.id.buttonRegion:
                Log.d(LOG_TAG, "--- Население по региону ---");

                columns = new String[] {"region", "sum(people) as people"};
                groupBy = "region";
                cursor = sqLiteDatabase.query("mytable", columns, null, null, groupBy, null, null);

                break;
            case R.id.buttonRegion2:
                Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople + " ---");

                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                cursor = sqLiteDatabase.query("mytable", columns, null, null, groupBy, having, null);

                break;
            case R.id.buttonSort:
                // сортировка по
                switch (radioGroupSort.getCheckedRadioButtonId()) {
                    // наименование
                    case R.id.radioButton:
                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---");
                        orderBy = "name";
                        break;
                    // население
                    case R.id.radioButton2:
                        Log.d(LOG_TAG, "--- Сортировка по населению ---");
                        orderBy = "people";
                        break;
                    // регион
                    case R.id.radioButton3:
                        Log.d(LOG_TAG, "--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }
                cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, orderBy);
                break;

        }

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String string;
                do {
                    string = "";
                    for (String cn : cursor.getColumnNames()) {
                        string = string.concat(cn + " = "
                                + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, string);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        else {
            Log.d(LOG_TAG, "Cursor is null");
        }

        dbHelper.close();
    }
}
