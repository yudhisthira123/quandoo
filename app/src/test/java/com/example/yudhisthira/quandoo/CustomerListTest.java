package com.example.yudhisthira.quandoo;

import com.example.yudhisthira.quandoo.data.Customer;
import com.example.yudhisthira.quandoo.model.ICustomerModel;
import com.example.yudhisthira.quandoo.presenter.CustomerPresenterImpl;
import com.example.yudhisthira.quandoo.presenter.ICustomerPresenter;
import com.example.yudhisthira.quandoo.view.ICustomerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by yudhisthira
 */

public class CustomerListTest {
    @Mock
    ICustomerView customerView;

    @Mock
    ICustomerModel customerModel;


    ICustomerPresenter customerPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerPresenter = new CustomerPresenterImpl(customerModel);
        customerPresenter.attachView(customerView);
    }

    @After
    public void tearDown() {
        customerPresenter.dettachView();
    }

    @Test
    public void getSuccessAndShowContent() {
        Mockito.when(customerModel.fetchCustomer()).then(invocation -> createTestList());
        customerPresenter.fetchCustomer();
        Mockito.verify(customerView, Mockito.times(1)).showCustomerListView();
    }

    @Test
    public void getFailureAndShowError() {
        Mockito.when(customerModel.fetchCustomer()).then(invocation -> createTestError());
        customerPresenter.fetchCustomer();
        Mockito.verify(customerView, Mockito.times(1)).showError();
    }

    private Maybe<List<Customer>> createTestList() {
        List<Customer> customerList = new ArrayList<>(10);

        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));
        customerList.add(new Customer(1, "Yudhisthira", "Attry"));

        return Maybe.just(customerList);
    }

    private Maybe createTestError() {
        return Maybe.error(new Exception());
    }
}
