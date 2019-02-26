package com.gzd.example.frameworkapplication.view;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gzd.example.frameworkapplication.R;
import com.gzd.example.frameworkapplication.adapter.PersonAdapter;
import com.gzd.example.frameworkapplication.bean.Person;
import com.gzd.example.frameworkapplication.listener.EndlessRecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private PersonAdapter mPersonAdapter;
    private List<Person> mPeople = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        getData(1);
    }

    private void initView(){
        mRecyclerView = findViewById(R.id.rv_main);
        mPersonAdapter = new PersonAdapter(mPeople);
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mPersonAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                final List<Person> personList = new ArrayList<>();
                for(int i=16;i<17;i++){
                    Person person = new Person();
                    person.setName("name" + i);
                    person.setAge(i);
                    personList.add(person);
                }
                Observable.fromArray(personList)
                        .delay(2,TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<Person>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Person> people) {
                                mPeople.remove(mPeople.size() - 1);  //关闭进度条
                                mPeople.addAll(people);
                                mPersonAdapter.notifyDataSetChanged();
                                setLoading(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getData(2);
    }

    private List<Person> getData(int flag){
        List<Person> personList = new ArrayList<>();
        if (flag == 1){
            mSwipeRefreshLayout.setRefreshing(true);
            for(int i=0;i<15;i++){
                Person person = new Person();
                person.setName("name" + i);
                person.setAge(i);
                personList.add(person);
            }
            Observable.fromArray(personList)
                    .delay(2,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Person>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<Person> people) {
                            mPeople.addAll(people);
                            mSwipeRefreshLayout.setRefreshing(false);
                            mPersonAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else if (flag ==2){
            for (int i=16;i<20;i++){
                Person person = new Person();
                person.setName("name" + i);
                person.setAge(i);
                personList.add(person);
            }
            Observable.fromArray(personList)
                    .delay(2,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Person>>() {
                        @Override
                        public void accept(List<Person> people) throws Exception {
                            mPeople.addAll(0,people);
                            if (mPeople.size() > 14){
                                List<Person> personList1 = new ArrayList<>();
                                for (int i = 14;i < mPeople.size();i++){
                                    personList1.add(mPeople.get(i));
                                }
                                mPeople.removeAll(personList1);
                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                            mPersonAdapter.notifyDataSetChanged();
                        }
                    });

        }
        return personList;
    }
}
