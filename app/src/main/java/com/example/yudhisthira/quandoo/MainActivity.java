package com.example.yudhisthira.quandoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.yudhisthira.quandoo.data.Customer;
import com.example.yudhisthira.quandoo.di.MainComponent;
import com.example.yudhisthira.quandoo.presenter.ICustomerPresenter;
import com.example.yudhisthira.quandoo.ui.CustomerAdapter;
import com.example.yudhisthira.quandoo.view.ICustomerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements ICustomerView, CustomerAdapter.ICustomerClickListener {

    @BindView(R.id.main_view_flipper)
    ViewFlipper mainViewFlipper;

    @BindView(R.id.customer_list)
    RecyclerView customerList;

    @BindView(R.id.errorLyt)
    View errorLayout;

    @BindView(R.id.loadingLyt)
    View loadingLayout;

    @Inject
    ICustomerPresenter customerPresenter;

    private CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        customerPresenter.attachView(this);

        setupViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quandoo_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        initSearchView(item, searchView);
        return true;
    }

    @Override
    public void showError() {
        mainViewFlipper.setDisplayedChild(0);
    }

    @Override
    public void showProgress() {
        mainViewFlipper.setDisplayedChild(1);
    }

    @Override
    public void setCustomerList(List<Customer> customerList) {
        customerAdapter.setCustomerList(customerList);
    }

    @Override
    public void showCustomerListView() {
        mainViewFlipper.setDisplayedChild(2);
    }

    @Override
    public void onCustomerItemClicked(int position, Customer customer) {

        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(Customer.CUSTOMER_PARCEL, customer);
        startActivity(intent);
    }

    @OnClick(R.id.button_refresh)
    void onRefresh() {
        customerPresenter.fetchCustomer();
    }

    private void setupViews() {
        ActionBar actionBar = getSupportActionBar();

        if(null != actionBar) {
            actionBar.setTitle("Customers....");
        }

        initListView();
        customerPresenter.fetchCustomer();
    }

    private void initListView() {
        customerAdapter = new CustomerAdapter();
        customerList.setAdapter(customerAdapter);

        customerAdapter.setOnCustomerClickedListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        customerList.setLayoutManager(layoutManager);
    }

    private void initSearchView(MenuItem item, SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customerAdapter.getSearchFilter().filter(newText);
                return false;
            }
        });
    }

    private MainComponent getMainComponent() {
        return getApp().getMainComponent();
    }

    private MyApplication getApp() {
        return  (MyApplication) getApplication();
    }
}
