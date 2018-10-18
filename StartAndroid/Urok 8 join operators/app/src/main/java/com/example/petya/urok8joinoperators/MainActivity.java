package com.example.petya.urok8joinoperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(10);

        Observable<Long> observable2 = Observable.interval(700, TimeUnit.MILLISECONDS)
                .take(10)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong + 100;
                    }
                });

        Observable[] observableArray = new Observable[]{observable1, observable2};

        //observable1.merge(observableArray, 1)
        //observable1.mergeWith(observable2)
        //observable1.concatWith(observable2)
        //Observable.amb(observable1, observable2)
        /*observable1
                .zipWith(observable2, new Func2<Long, Long, String>() {
                    @Override
                    public String call(Long aLong, Long aLong2) {
                        return String.format("%s and %s", aLong, aLong2);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String next) {
                        Log.d(TAG, "onNext " + next);
                    }
                });
*/
        Observable<Long> observable21 = Observable.interval(300, TimeUnit.MILLISECONDS);

        List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        /*observable21
                .zipWith(strings, new Func2<Long, String, String>() {
                    @Override
                    public String call(Long aLong, String s) {
                        return String.format("%s and %s", aLong, s);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext " + s);
                    }
                });*/

        Observable<Long> observable22 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(10);

        Observable<Long> observable23 = Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(10)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong + 100;
                    }
                });

        Observable<Long> observable24 = Observable.interval(800, TimeUnit.MILLISECONDS)
                .take(10)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong + 1000;
                    }
                });

       /* Observable
                .zip(observable22, observable23, observable24, new Func3<Long, Long, Long, String>() {
                    @Override
                    public String call(Long aLong, Long aLong2, Long aLong3) {
                        return String.format("%s and %s and %s", aLong, aLong2, aLong3);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext " + s);
                    }
                });*/

       Observable<String> observable31 = Observable.just("A", "B", "C", "D", "E");
       Observable<Long> observable32 = Observable.interval(1000, TimeUnit.MILLISECONDS);

       /*Observable
               .zip(observable31, observable32, new Func2<String, Long, String>() {
                   @Override
                   public String call(String s, Long aLong) {
                       return s;
                   }
               })
               .subscribe(new Observer<String>() {
                   @Override
                   public void onCompleted() {
                       Log.d(TAG, "onCompleted");
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(String s) {
                       Log.d(TAG, "onNext " + s);
                   }
               });*/

       Observable<Long> observable41 = Observable.interval(300, TimeUnit.MILLISECONDS);

       Observable<Long> observable42 = Observable.interval(500, TimeUnit.MILLISECONDS)
               .take(10)
               .map(new Func1<Long, Long>() {
                   @Override
                   public Long call(Long aLong) {
                       return aLong + 100;
                   }
               });

        observable42
               //.combineLatest(observable41, observable42, new Func2<Long, Long, String>() {
               .withLatestFrom(observable41, new Func2<Long, Long, String>() {
                   @Override
                   public String call(Long aLong, Long aLong2) {
                       return String.format("%s and %s", aLong, aLong2);
                   }
               })
               .subscribe(new Observer<String>() {
                   @Override
                   public void onCompleted() {
                       Log.d(TAG, "onCompleted");
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(String s) {
                       Log.d(TAG, "onNext " + s);
                   }
               });


    }
}
