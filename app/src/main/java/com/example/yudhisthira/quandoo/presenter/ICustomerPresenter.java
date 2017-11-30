package com.example.yudhisthira.quandoo.presenter;

import com.example.yudhisthira.quandoo.view.ICustomerView;

/**
 * Created by yudhisthira
 */

public interface ICustomerPresenter {
    public void attachView(ICustomerView customerView);
    public void dettachView();
    public void fetchCustomer();
}
