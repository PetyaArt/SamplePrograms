package com.example.petya.lesson68parcel;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    Parcel mParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeParcel();
        readParcel();

    }

    private void readParcel() {
        logReadInfo("before reading");
        mParcel.setDataPosition(0);
        logReadInfo("byte = " + mParcel.readByte());
        logReadInfo("int = " + mParcel.readInt());
        logReadInfo("long = " + mParcel.readLong());
        logReadInfo("float = " + mParcel.readFloat());
        logReadInfo("double = " + mParcel.readDouble());
        logReadInfo("String = " + mParcel.readString());

    }

    private void writeParcel() {
        mParcel = Parcel.obtain();

        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        logWriteInfo("before writing");
        mParcel.writeByte(b);
        logWriteInfo("byte");

        mParcel.writeInt(i);
        logWriteInfo("int");

        mParcel.writeLong(l);
        logWriteInfo("long");

        mParcel.writeFloat(f);
        logWriteInfo("float");

        mParcel.writeDouble(d);
        logWriteInfo("double");

        mParcel.writeString(s);
        logWriteInfo("String");
    }

    void logWriteInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataSize = " + mParcel.dataSize());
    }

    void logReadInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataPosition = " + mParcel.dataPosition());
    }
}
