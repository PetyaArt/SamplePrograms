package com.example.petya.urok6subscribeonandobserveon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "observer onNext value = " + integer);
            }
        };

        Func1<Integer, Integer> func1 = new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                Log.d(TAG, "func " + integer);
                return integer * 10;
            }
        };

        Observable.OnSubscribe onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d(TAG, "call");
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        };

        Observable observable = Observable
                .create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(func1)
                .observeOn(AndroidSchedulers.mainThread());

        Log.d(TAG, "subscribe");
        observable.subscribe(observer);
        Log.d(TAG, "done");
    }

}
