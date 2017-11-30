package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Table;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by yudhisthira
 */

public class TableLocalModel {
    private static final String TABLE_KEY = "TABLE_KEY";

    public Flowable<List<Table>> getTableList(){
        List<Table> customerList = Hawk.get(TABLE_KEY);

        if(null != customerList) {
            return Flowable.just(customerList);
        }

        return Flowable.just(new ArrayList<>());
    }

    public Flowable<List<Table>> updateTableList(List<Table> tableList) {
        return Flowable.create(e -> {
            try {
                Hawk.delete(TABLE_KEY);
                Hawk.put(TABLE_KEY, tableList);
                e.onNext(tableList);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<List<Table>> addTableList(List<Table> tableList) {
        return Flowable.create(e -> {
            try {
                Hawk.put(TABLE_KEY, tableList);
                e.onNext(tableList);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<Void> deleteTableList() {
        return Flowable.create(e -> {
            try {
                Hawk.delete(TABLE_KEY);
                e.onNext(null);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }
}
