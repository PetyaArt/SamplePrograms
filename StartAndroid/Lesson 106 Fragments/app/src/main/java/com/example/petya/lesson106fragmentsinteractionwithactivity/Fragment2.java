package com.example.petya.lesson106fragmentsinteractionwithactivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment {

    public interface onSomeEventListener {
        void someEvent(String s);
    }

    onSomeEventListener mOnSomeEventListener;

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.onAttach(context);
            Log.d(LOG_TAG, "onArrach Context");
            try {
                mOnSomeEventListener = (onSomeEventListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(LOG_TAG, "onArrach Activity");
            try {
                mOnSomeEventListener = (onSomeEventListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
            }
        }
    }


    final String LOG_TAG = "myLogs";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.d(LOG_TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment2, null);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSomeEventListener.someEvent("Test text to Fragment1");
            }
        });

        return view;
    }
}
