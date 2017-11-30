package com.example.yudhisthira.quandoo.view;

import com.example.yudhisthira.quandoo.data.Customer;

import java.util.List;

/**
 * Created by yudhisthira
 */

public interface ICustomerView {
    public void showError();
    public void showProgress();
    public void setCustomerList(List<Customer> customerList);
    public void showCustomerListView();
}
