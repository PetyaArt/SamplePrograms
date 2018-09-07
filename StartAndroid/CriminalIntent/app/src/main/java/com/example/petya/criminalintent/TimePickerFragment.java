package com.example.petya.criminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME =
            "com.example.petya.criminalintent.time";

    private static final String ARG_DATE = "time";

    private TimePicker mTimePicker;
    private Calendar calendar;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        mTimePicker = view.findViewById(R.id.dialog_time_picker);
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int hour, minute;
                        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                            hour = mTimePicker.getHour();
                            minute = mTimePicker.getMinute();
                        } else {
                            hour = mTimePicker.getCurrentHour();
                            minute = mTimePicker.getCurrentMinute();
                        }
                        calendar.set(Calendar.HOUR, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        Log.d("myLogs", calendar.getTime().toString());
                        sendResult(Activity.RESULT_OK, calendar.getTime());
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, Date date) {

        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
