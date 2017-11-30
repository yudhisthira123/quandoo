package com.example.yudhisthira.quandoo.view;

import com.example.yudhisthira.quandoo.data.Table;

import java.util.List;

/**
 * Created by yudhisthira
 */

public interface ITableView {
    public void showError();
    public void showProgress();
    public void setTableList(List<Table> tableList);
    public void showTableListView();
    public void showSuccessMessage();
    public void showFailureMessage();
    public void closeView();
}
