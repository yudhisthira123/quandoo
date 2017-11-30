package com.example.yudhisthira.quandoo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.example.yudhisthira.quandoo.R;
import com.example.yudhisthira.quandoo.data.Customer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yudhisthira
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ItemHolder> implements View.OnClickListener {

    private List<Customer> customerList = null;
    private List<Customer> filteredCustomerList = null;
    private SearchFilter searchFilter;
    private ICustomerClickListener customerClickListener;

    public CustomerAdapter() {
        customerList = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_list_item, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        Customer customer = filteredCustomerList.get(position);
        holder.customerName.setText(customer.getFullName());

        holder.view.setOnClickListener( view -> customerClickListener.onCustomerItemClicked(position, customer) );

    }

    @Override
    public int getItemCount() {
        if(null != filteredCustomerList && filteredCustomerList.size() > 0) {
            return filteredCustomerList.size();
        }
        return 0;
    }

    public void setOnCustomerClickedListener(ICustomerClickListener customerClickListener) {
        this.customerClickListener = customerClickListener;
    }

    public void setCustomerList(List<Customer> customers) {
        this.customerList.clear();
        this.customerList.addAll(customers);
        getSearchFilter().filter("");
        notifyDataSetChanged();
    }

    public Filter getSearchFilter() {
        if(null == searchFilter) {
            searchFilter = new SearchFilter();
        }

        return searchFilter;
    }

    @Override
    public void onClick(View view) {

    }

    private class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() > 0) {
                ArrayList<Customer> filterList = new ArrayList<>();
                for (int i = 0; i < customerList.size(); i++) {
                    Customer customer = customerList.get(i);
                    if ((customer.getFullName().toUpperCase()).contains(charSequence.toString().toUpperCase())) {
                        filterList.add(customer);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = customerList.size();
                results.values = customerList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredCustomerList = (List<Customer>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    public interface ICustomerClickListener {
        void onCustomerItemClicked(int position, Customer customer);
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View view;

        @BindView(R.id.customer_name)
        TextView customerName;

        public ItemHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Customer customer = filteredCustomerList.get(pos);

            customerClickListener.onCustomerItemClicked(pos, customer);
        }
    }
}
