package com.example.petya.lesson37queriesfromlinkedtables;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.petya.lesson37queriesfromlinkedtables.MainActivity.LOG_TAG;

class DBHelper extends SQLiteOpenHelper{

    // данные для таблицы должностей
    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 13000, 10000, 8000 };

    // данные для таблицы людей
    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        ContentValues contentValues = new ContentValues();

        sqLiteDatabase.execSQL("create table position ("
                + "id integer primary key,"
                + "name text," + "salary integer"
                + ");");

        // заполняем ее
        for (int i = 0; i < position_id.length; i++) {
            contentValues.clear();
            contentValues.put("id", position_id[i]);
            contentValues.put("name", position_name[i]);
            contentValues.put("salary", position_salary[i]);
            sqLiteDatabase.insert("position", null, contentValues);
        }

        // создаем таблицу людей
        sqLiteDatabase.execSQL("create table people ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "posid integer"
                + ");");

        // заполняем ее
        for (int i = 0; i < people_name.length; i++) {
            contentValues.clear();
            contentValues.put("name", people_name[i]);
            contentValues.put("posid", people_posid[i]);
            sqLiteDatabase.insert("people", null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
