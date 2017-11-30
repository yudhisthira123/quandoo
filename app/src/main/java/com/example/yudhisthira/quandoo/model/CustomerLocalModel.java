package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Customer;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by yudhisthira
 */

public class CustomerLocalModel {
    private static final String CUSTOMER_KEY = "CUSTOMER_KEY";

    public Flowable<List<Customer>> getCustomerList(){
        List<Customer> customerList = Hawk.get(CUSTOMER_KEY);

        if(null != customerList) {
            return Flowable.just(customerList);
        }

        return Flowable.just(new ArrayList<>());
    }

    public Flowable<List<Customer>> updateCustomerList(List<Customer> customerList) {
        return Flowable.create(e -> {
            try {
                Hawk.delete(CUSTOMER_KEY);
                Hawk.put(CUSTOMER_KEY, customerList);
                e.onNext(customerList);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<List<Customer>> addCustomerList(List<Customer> customerList) {
        return Flowable.create(e -> {
            try {
                Hawk.put(CUSTOMER_KEY, customerList);
                e.onNext(customerList);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<Void> deleteCustomerList() {
        return Flowable.create(e -> {
            try {
                Hawk.delete(CUSTOMER_KEY);
                e.onNext(null);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }
}
