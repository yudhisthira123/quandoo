package com.example.yudhisthira.quandoo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yudhisthira
 */

public class Customer implements Parcelable {

    public final static String CUSTOMER_PARCEL = "CUSTOMER_PARCEL";

    @SerializedName("id")
    private int customerID;

    @SerializedName("customerFirstName")
    private String firstName;

    @SerializedName("customerLastName")
    private String lastName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(customerID);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
    }

    public Customer(int customerID, String firstName, String lastName) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Customer(Parcel source) {
        this.customerID = source.readInt();
        this.firstName = source.readString();
        this.lastName = source.readString();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return (firstName + " " + lastName);
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
