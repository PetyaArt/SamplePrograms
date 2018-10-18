package com.example.petya.urok6errorhandling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> stringData = Observable.just("1", "2", "a", "4", "5");

        Observable<Long> observable = stringData
                .map(new Func1<String, Long>() {
                    @Override
                    public Long call(String s) {
                        return Long.parseLong(s);
                    }
                })
                /*.onErrorResumeNext(new Func1<Throwable, Observable<? extends Long>>() {
                    @Override
                    public Observable<? extends Long> call(Throwable throwable) {
                        Log.d(TAG, "onErrorResume " + throwable);
                        return Observable.just(8L, 9L, 10L);
                    }
                })*/
                /*.retry(2);*/
                .retry(new Func2<Integer, Throwable, Boolean>() {
                    @Override
                    public Boolean call(Integer integer, Throwable throwable) {
                        Log.d(TAG, "retry retryCount " + integer + ", throwable = " + throwable);
                        return integer < 3;
                    }
                });
                /*.onErrorReturn(new Func1<Throwable, Long>() {
                    @Override
                    public Long call(Throwable throwable) {
                        Log.d(TAG, "onErrorReturn" + throwable);
                        return 0L;
                    }
                });*/


        observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext " + aLong);
            }
        });

        /*observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d(TAG, "onNext " + aLong);
            }
        });*/
    }
}
