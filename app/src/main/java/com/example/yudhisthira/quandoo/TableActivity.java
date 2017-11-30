package com.example.yudhisthira.quandoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.yudhisthira.quandoo.data.Customer;
import com.example.yudhisthira.quandoo.data.Table;
import com.example.yudhisthira.quandoo.di.MainComponent;
import com.example.yudhisthira.quandoo.presenter.ITablePresenter;
import com.example.yudhisthira.quandoo.ui.TableAdapter;
import com.example.yudhisthira.quandoo.utils.Utils;
import com.example.yudhisthira.quandoo.view.ITableView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yudhisthira
 */

public class TableActivity extends AppCompatActivity implements ITableView, TableAdapter.ITableClickListener{

    @BindView(R.id.table_view_flipper)
    ViewFlipper tableViewFlipper;

    @BindView(R.id.table_list)
    RecyclerView tableList;

    @BindView(R.id.errorLyt)
    View errorLayout;

    @BindView(R.id.loadingLyt)
    View loadingLayout;

    @Inject
    ITablePresenter tablePresenter;

    TableAdapter tableAdapter;

    private Customer customer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);
        getMainComponent().inject(this);

        Intent intent = getIntent();
        if(null != intent) {
            customer = intent.getParcelableExtra(Customer.CUSTOMER_PARCEL);
        }

        tablePresenter.attachView(this);
        setupViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError() {
        tableViewFlipper.setDisplayedChild(0);
    }

    @Override
    public void showProgress() {
        tableViewFlipper.setDisplayedChild(1);
    }

    @Override
    public void setTableList(List<Table> tableList) {
        tableAdapter.setTableList(tableList);
    }

    @Override
    public void showTableListView() {
        tableViewFlipper.setDisplayedChild(2);
    }

    @Override
    public void closeView() {
        finish();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Your Table booked successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureMessage() {
        Toast.makeText(this, "Failed to book table", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTableClicked(int position, Table table) {

        if(table.isBooked()) {
            Utils.showUnavailableAlert(this, table);
        }
        else {
            Utils.showAvailableAlert(this, table, ((dialogInterface, i) -> {
                table.setBooked(true);
                table.setCustomer(customer);
                tablePresenter.updateTableBooking(tableAdapter.getTables());
            }) );
        }

    }

    private void setupViews() {
        ActionBar actionBar = getSupportActionBar();

        if(null != actionBar) {
            actionBar.setTitle("Book Table for " + customer.getFullName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        initTableView();
        tablePresenter.fetchCustomer();
    }

    private void initTableView() {

        tableAdapter = new TableAdapter();
        tableList.setAdapter(tableAdapter);

        tableAdapter.setOnTableClickedListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tableList.setLayoutManager(gridLayoutManager);
    }

    private MainComponent getMainComponent() {
        return getApp().getMainComponent();
    }

    private MyApplication getApp() {
        return  (MyApplication) getApplication();
    }

}
