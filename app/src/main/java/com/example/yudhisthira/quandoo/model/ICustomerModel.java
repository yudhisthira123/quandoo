package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Customer;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by yudhisthira
 */

public interface ICustomerModel {
    public Maybe<List<Customer>> fetchCustomer();
}
