package com.example.yudhisthira.quandoo.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yudhisthira
 */

public class Table implements Parcelable{
    private Boolean isBooked;
    private int tableNumber;

    private Customer customer;

    public Table() {
    }

    public Table(Parcel source) {
        this.isBooked = (Boolean) source.readValue(Boolean.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(isBooked);
    }

    public void setBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel source) {
            return new Table(source);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };
}
