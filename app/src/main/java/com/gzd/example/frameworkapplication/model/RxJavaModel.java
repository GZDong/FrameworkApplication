package com.gzd.example.frameworkapplication.model;

import com.gzd.example.frameworkapplication.bean.Image;
import com.gzd.example.frameworkapplication.bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gzd on 2019/2/26 0026
 */
public class RxJavaModel {
    List<Image> mImages = new ArrayList<>();
    List<Person> mPeople = new ArrayList<>();
    public void testRxJava(){
        Observable.fromIterable(mImages)
                .map(new Function<Image, String>() {
                    @Override
                    public String apply(Image image) throws Exception {

                        return image.getName();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

        Observable.fromIterable(mPeople)
                .flatMap(new Function<Person, ObservableSource<Image>>() {
                    @Override
                    public ObservableSource<Image> apply(Person person) throws Exception {

                        return Observable.fromIterable(person.getImages());
                    }
                })
                .flatMap(new Function<Image, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Image image) throws Exception {
                        return Observable.just(image.getName());
                    }
                }).buffer(2,1)                //设置缓冲之后，后面原本是String的变成了List<String>
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.fromIterable(mPeople)
                .scan(new BiFunction<Person, Person, Person>() {
                    @Override
                    public Person apply(Person person, Person person2) throws Exception {
                        return null;
                    }
                });

        Observable.concat(Observable.just(new Person()),Observable.just(new Person()))
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) throws Exception {

                    }
                });

        Observable.merge(Observable.interval(1,TimeUnit.SECONDS)
           .map(new Function<Long,String>(){
               @Override
               public String apply(Long aLong) throws Exception {
                   return aLong + "s";
               }
           }),Observable.interval(1,TimeUnit.SECONDS)
        .map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return aLong +"s";
            }
        })).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Observable.just(1,2,3)
                .startWith(2)
                .startWithArray(4,5,6)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> emitter) throws Exception {

            }
        }).doOnEach(new Consumer<Notification<Image>>() {    //做。。。之前/时/后 通知另一个观察者
            @Override
            public void accept(Notification<Image> imageNotification) throws Exception {
                imageNotification.isOnNext();
            }
        }).subscribe(new Consumer<Image>() {
            @Override
            public void accept(Image image) throws Exception {

            }
        });

        Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> emitter) throws Exception {

            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {

            }
        }).subscribe();

        Observable.just(1,2,3)
                .retry(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });

        Observable.just(2,3,4)
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return false;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });

        Observable.just(1,2,3)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        if (integer > 2)
                        return true;
                        return false;
                    }
                });

        Observable.just(12,2,3,"time")
                .ofType(String.class)
                .skip(2)
                .distinct()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

        Observable.just(1,2,3)
                .defaultIfEmpty(0)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return false;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

            }
        });
    }
}
