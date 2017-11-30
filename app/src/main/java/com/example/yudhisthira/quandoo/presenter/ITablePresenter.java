package com.example.yudhisthira.quandoo.presenter;

import com.example.yudhisthira.quandoo.data.Table;
import com.example.yudhisthira.quandoo.view.ITableView;

import java.util.List;

/**
 * Created by yudhisthira
 */

public interface ITablePresenter {
    public void attachView(ITableView tableView);
    public void dettachView();
    public void fetchCustomer();
    public void updateTableBooking(List<Table> tableList);
}
