package com.example.yudhisthira.quandoo.presenter;

import com.example.yudhisthira.quandoo.data.Table;
import com.example.yudhisthira.quandoo.model.ITableModel;
import com.example.yudhisthira.quandoo.view.ITableView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yudhisthira
 */

public class TablePresenterImpl implements ITablePresenter {

    private ITableView tableView;
    private ITableModel tableModel;

    @Inject
    public TablePresenterImpl(ITableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public void attachView(ITableView tableView) {
        this.tableView = tableView;
    }

    @Override
    public void dettachView() {
        this.tableView = null;
    }

    @Override
    public void fetchCustomer() {
        tableView.showProgress();
        tableModel.fetchCustomer()
                .subscribe(tableList -> {
                    tableView.setTableList(tableList);
                    tableView.showTableListView();
                }, throwable -> {
                    tableView.showError();
                });
    }

    @Override
    public void updateTableBooking(List<Table> tableList) {
        tableModel.saveTableList(tableList)
                .subscribe(tableList1 -> {
                    tableView.showSuccessMessage();
                    tableView.closeView();
                }, throwable -> {
                    tableView.showFailureMessage();
                });
    }
}
