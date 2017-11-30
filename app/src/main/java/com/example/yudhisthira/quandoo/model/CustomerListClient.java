package com.example.yudhisthira.quandoo.model;

import com.example.yudhisthira.quandoo.data.Customer;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yudhisthira
 */

public interface CustomerListClient {
    @GET("customer-list.json")
    Flowable<List<Customer>> fetchCustomerList();
}
