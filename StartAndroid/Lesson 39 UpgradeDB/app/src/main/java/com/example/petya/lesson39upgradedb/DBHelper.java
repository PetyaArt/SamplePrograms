package com.example.petya.lesson39upgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.petya.lesson39upgradedb.MainActivity.LOG_TAG;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*Log.d(LOG_TAG, " --- onCreate database --- ");

        String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
                "Борис", "Костя", "Игорь" };
        String[] people_positions = { "Программер", "Бухгалтер",
                "Программер", "Программер", "Бухгалтер", "Директор",
                "Программер", "Охранник" };

        ContentValues cv = new ContentValues();

        sqLiteDatabase.execSQL("create table people ("
                + "id integer primary key autoincrement,"
                + "name text, position text);");

        for (int i = 0; i < people_name.length; i++) {
            cv.clear();
            cv.put("name", people_name[i]);
            cv.put("position", people_positions[i]);
            sqLiteDatabase.insert("people", null, cv);
        }*/

        Log.d(LOG_TAG, " --- onCreate database --- ");
        String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
                "Борис", "Костя", "Игорь" };
        int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

        // данные для таблицы должностей
        int[] position_id = { 1, 2, 3, 4 };
        String[] position_name = { "Директор", "Программер", "Бухгалтер",
                "Охранник" };
        int[] position_salary = { 15000, 13000, 10000, 8000 };

        ContentValues contentValues = new ContentValues();

        // создаем таблицу должностей
        sqLiteDatabase.execSQL("create table position (" + "id integer primary key,"
                + "name text, salary integer" + ");");


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
                + "name text, posid integer);");

        // заполняем ее
        for (int i = 0; i < people_name.length; i++) {
            contentValues.clear();
            contentValues.put("name", people_name[i]);
            contentValues.put("posid", people_posid[i]);
            sqLiteDatabase.insert("people", null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");

        if (oldVersion == 1 && newVersion == 2) {

            ContentValues contentValues = new ContentValues();
            // данные для таблицы должностей
            int[] position_id = { 1, 2, 3, 4 };
            String[] position_name = { "Директор", "Программер",
                    "Бухгалтер", "Охранник" };
            int[] position_salary = { 15000, 13000, 10000, 8000 };

            sqLiteDatabase.beginTransaction();
            try {
                sqLiteDatabase.execSQL("create table position ("
                        + "id integer primary key,"
                        + "name text, salary integer);");

                for (int i = 0; i < position_id.length; i++) {
                    contentValues.clear();
                    contentValues.put("id", position_id[i]);
                    contentValues.put("name", position_name[i]);
                    contentValues.put("salary", position_salary[i]);
                    sqLiteDatabase.insert("position", null, contentValues);
                }

                sqLiteDatabase.execSQL("alter table people add column posid integer;");

                for (int i = 0; i < position_id.length; i++) {
                    contentValues.clear();
                    contentValues.put("posid", position_id[i]);
                    sqLiteDatabase.update("people", contentValues, "position = ?",
                            new String[] { position_name[i] });
                }

                sqLiteDatabase.execSQL("create temporary table people_tmp ("
                        + "id integer, name text, position text, posid integer);");

                sqLiteDatabase.execSQL("insert into people_tmp select id, name, position, posid from people;");
                sqLiteDatabase.execSQL("drop table people;");

                sqLiteDatabase.execSQL("create table people ("
                        + "id integer primary key autoincrement,"
                        + "name text, posid integer);");

                sqLiteDatabase.execSQL("insert into people select id, name, posid from people_tmp;");
                sqLiteDatabase.execSQL("drop table people_tmp;");

                sqLiteDatabase.setTransactionSuccessful();
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }

    }
}
