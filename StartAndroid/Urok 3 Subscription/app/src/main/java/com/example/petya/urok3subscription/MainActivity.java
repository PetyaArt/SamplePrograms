package com.example.petya.urok3subscription;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.UnicastSubject;

import static android.support.v4.os.HandlerCompat.postDelayed;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    Subscription subscription1;
    Subscription subscription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Observable<String> observable = Observable.from(new String[]{"one", "two", "three"});

        Observer<String> observer = new Observer<String>() {


            @Override
            public void onNext(String s) {
                Log.d("myLogs","onNext " + s );
            }

            @Override
            public void onCompleted() {
                Log.d("myLogs","onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("myLogs","onError" + e );
            }

        };

        //observable.subscribe(observer);

        Observable<Long> observable1 = Observable.interval(500, TimeUnit.MILLISECONDS);

        final Observer<Long> observer1 = new Observer<Long>() {

            @Override
            public void onNext(Long s) {
                Log.d("myLogs","onNext " + s );
            }

            @Override
            public void onCompleted() {
                Log.d("myLogs","onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("myLogs","onError" + e );
            }
        };

        //observable1.subscribe(observer1);

        /*Observable.fromCallable(new CallableLongAction("7"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("myLogs",  "onNext " + integer);
                    }
                });*/


        /*Observable<Long> observable2 = Observable.interval(1, TimeUnit.SECONDS);

        Action1<Long> action1 = new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d("myLogs", "onNext: " + aLong);
            }
        };

        final Subscription subscription = observable2.subscribe(action1);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "unsubscribe");
                subscription.unsubscribe();
            }
        }, 4500);*/

        /*Observable.OnSubscribe<Integer> onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onNext(i);
                }
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            }
        };

        Observable<Integer> observable2 = Observable.create(onSubscribe).subscribeOn(Schedulers.io());

        Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "omCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }
        };

        final Subscription subscription = observable2.subscribe(observer2);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "unsubscribe");
                subscription.unsubscribe();
            }
        }, 4500);*/

        //Cold observable
        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = : " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value = : " + aLong);
            }
        };

        Log.d(TAG, "observableCreate");
        final Observable<Long> observable2 = Observable.interval(1, TimeUnit.SECONDS).take(5);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer1 subscribe");
                observable2.subscribe(observer2);
            }
        }, 3000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer2 subscribe");
                observable2.subscribe(observer3);
            }
        }, 5500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = : " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value = : " + aLong);
            }
        };

        final ConnectableObservable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(6)
                .publish();


        Log.d(TAG, "observer1 subscribe");
        observable2.subscribe(observer2);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observableCreate");
                observable2.connect();

            }
        }, 2500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer2 subscribe");
                observable2.subscribe(observer3);
            }
        }, 6000);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = : " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value = : " + aLong);
            }
        };

        final ConnectableObservable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(6)
                .replay();


        Log.d(TAG, "observableCreate");
        observable2.connect();

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer1 subscribe");
                observable2.subscribe(observer2);

            }
        }, 2500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer2 subscribe");
                observable2.subscribe(observer3);
            }
        }, 4500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = : " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value = : " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(6)
                .publish()
                .refCount();

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                subscription1 = observable2.subscribe(observer2);
            }
        }, 1500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subscription2 = observable2.subscribe(observer3);
            }
        }, 3000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 unsubscribe");
                subscription1.unsubscribe();
            }
        }, 5000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 unsubscribe");
                subscription2.unsubscribe();
            }
        }, 6000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                observable2.subscribe(observer2);
            }
        }, 6500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10)
                .cache();

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                subscription1 = observable2.subscribe(observer2);
            }
        }, 1500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subscription2 = observable2.subscribe(observer3);
            }
        }, 4000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 unsubscribe");
                subscription1.unsubscribe();
            }
        }, 5500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 unsubscribe");
                subscription2.unsubscribe();
            }
        }, 6500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                observable2.subscribe(observer2);
            }
        }, 7500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        //final PublishSubject<Long> subject = PublishSubject.create();
        final ReplaySubject<Long> subject = ReplaySubject.create();

        Log.d(TAG, "subject subscribe");
        observable2.subscribe(subject);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                subject.subscribe(observer2);
            }
        }, 3500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subject.subscribe(observer3);
            }
        }, 5500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                subject.onNext(100L);
            }
        }, 7500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final BehaviorSubject<Long> subject = BehaviorSubject.create(-1L);

        Log.d(TAG, "observer1 subscribe");
        subject.subscribe(observer2);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "subject subscribe");
                observable2.subscribe(subject);
            }
        }, 2000);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "observer2 subscribe");
                subject.subscribe(observer3);
            }
        }, 7500);*/

        /*final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(4);

        final AsyncSubject<Long> subject = AsyncSubject.create();

        Log.d(TAG, "subject subscribe");
        observable2.subscribe(subject);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                subject.subscribe(observer2);
            }
        }, 1500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subject.subscribe(observer3);
            }
        }, 7500);*/

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer3 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG,"observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(20);

        final UnicastSubject<Long> subject = UnicastSubject.create();

        Log.d(TAG, "subject subscribe");
        observable2.subscribe(subject);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 subscribe");
                subscription1 = subject.subscribe(observer2);
            }
        }, 2500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subject.subscribe(observer3);
            }
        }, 4500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer1 unsubscribe");
                subscription1.unsubscribe();
            }
        }, 6500);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"observer2 subscribe");
                subject.subscribe(observer3);
            }
        }, 8500);

    }

    /*private int longAction(String text) {
        Log.d("myLogs", "longAction");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(text);
    }

    class CallableLongAction implements Callable<Integer> {

        private final String data;

        public CallableLongAction(String data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            return longAction(data);
        }
    }*/
}
