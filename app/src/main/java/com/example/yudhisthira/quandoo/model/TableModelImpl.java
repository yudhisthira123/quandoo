package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Table;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yudhisthira
 */

public class TableModelImpl implements ITableModel {

    private TableLocalModel tableLocalModel;
    private TableListClient tableListClient;

    @Inject
    public TableModelImpl(TableLocalModel tableLocalModel, TableListClient tableListClient) {
        this.tableLocalModel = tableLocalModel;
        this.tableListClient = tableListClient;
    }

    @Override
    public Maybe<List<Table>> fetchCustomer() {

        Flowable<List<Table>> localTableList = getTableFromLocal();
        Flowable<List<Table>> remoteTableList = getTableFromRemote();

        return Flowable.concatArray(localTableList, remoteTableList)
                .filter(tableList -> !tableList.isEmpty())
                .firstElement();
    }

    @Override
    public Flowable<List<Table>> saveTableList(List<Table> tableList) {
        return tableLocalModel.updateTableList(tableList);
    }

    private Flowable<List<Table>> getTableFromLocal() {
        return tableLocalModel.getTableList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<List<Table>> getTableFromRemote() {
        return tableListClient.fetchTableList()
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(reservedTables -> {
                    List<Table> tables = new ArrayList<>();
                    for (int i = 0; i < reservedTables.size(); i++) {
                        Boolean reservation = reservedTables.get(i);
                        Table table = new Table();
                        table.setTableNumber(i + 1);
                        table.setBooked(reservation);
                        tables.add(table);
                    }
                    return tables;
                })
                .concatMap(tableList -> tableLocalModel.updateTableList(tableList));

    }
}
