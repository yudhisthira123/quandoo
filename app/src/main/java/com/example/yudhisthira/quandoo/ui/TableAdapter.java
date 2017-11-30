package com.example.yudhisthira.quandoo.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yudhisthira.quandoo.R;
import com.example.yudhisthira.quandoo.data.Table;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yudhisthira
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ItemHolder>{

    private List<Table> tableList = null;
    private ITableClickListener tableClickListener;

    public TableAdapter() {
        tableList = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_list_item, parent, false);

        return new TableAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Table table = tableList.get(position);

        holder.tableNumber.setText("Table #" + table.getTableNumber());
        Context context = holder.itemView.getContext();
        if (true == table.isBooked()) {
            holder.booking_status.setText("BOOKED");
            holder.booking_status.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
        else {
            holder.booking_status.setText("AVAILABLE");
            holder.booking_status.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        holder.itemView.setOnClickListener(view -> tableClickListener.onTableClicked(position, table));
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public void setOnTableClickedListener(ITableClickListener tableClickListener) {
        this.tableClickListener = tableClickListener;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList.clear();
        this.tableList.addAll(tableList);
        notifyDataSetChanged();
    }

    public List<Table> getTables() {
        return tableList;
    }

    public interface ITableClickListener {
        void onTableClicked(int position, Table table);
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private View itemView;

        @BindView(R.id.table_number)
        TextView tableNumber;

        @BindView(R.id.booking_status)
        TextView booking_status;

        public ItemHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}
