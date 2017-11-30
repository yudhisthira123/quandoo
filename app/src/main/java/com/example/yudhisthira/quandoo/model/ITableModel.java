package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Table;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by yudhisthira
 */

public interface ITableModel {
    public Maybe<List<Table>> fetchCustomer();
    public Flowable<List<Table>> saveTableList(List<Table> tableList);

}
