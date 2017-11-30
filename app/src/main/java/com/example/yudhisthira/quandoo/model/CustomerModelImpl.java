package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Customer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yudhisthira
 */

public class CustomerModelImpl implements ICustomerModel {

    private CustomerLocalModel customerLocalModel;
    private CustomerListClient customerListClient;

    @Inject
    public CustomerModelImpl(CustomerLocalModel customerLocalModel, CustomerListClient customerListClient) {
        this.customerLocalModel = customerLocalModel;
        this.customerListClient = customerListClient;
    }

    @Override
    public Maybe<List<Customer>> fetchCustomer() {

        Flowable<List<Customer>> localCustomerList = getCustomerFromLocal();
        Flowable<List<Customer>> remoteCustomerList = getCustomerFromRemote();

        return Flowable.concatArray(localCustomerList, remoteCustomerList)
                .filter(customerList -> !customerList.isEmpty())
                .firstElement();
    }

    private Flowable<List<Customer>> getCustomerFromLocal() {
        return customerLocalModel.getCustomerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<List<Customer>> getCustomerFromRemote() {
        return customerListClient.fetchCustomerList()
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(customerList -> customerLocalModel.updateCustomerList(customerList));
    }
}
