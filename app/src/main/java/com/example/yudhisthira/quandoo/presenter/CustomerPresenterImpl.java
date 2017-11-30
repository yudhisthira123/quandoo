package com.example.yudhisthira.quandoo.presenter;

import com.example.yudhisthira.quandoo.model.ICustomerModel;
import com.example.yudhisthira.quandoo.view.ICustomerView;

import javax.inject.Inject;

/**
 * Created by yudhisthira
 */

public class CustomerPresenterImpl implements ICustomerPresenter {

    private ICustomerView customerView;

    private ICustomerModel customerModel;

    @Inject
    public CustomerPresenterImpl(ICustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    @Override
    public void attachView(ICustomerView customerView){
        this.customerView = customerView;
    }

    @Override
    public void dettachView(){
        this.customerView = null;
    }

    @Override
    public void fetchCustomer() {
        customerView.showProgress();

        customerModel.fetchCustomer()
                .subscribe(customerList -> {
                    customerView.setCustomerList(customerList);
                    customerView.showCustomerListView();
                }, throwable -> {
                    customerView.showError();
                });
    }
}
