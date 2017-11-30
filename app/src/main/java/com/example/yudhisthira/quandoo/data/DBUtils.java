package com.example.yudhisthira.quandoo.data;

import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * Created by yudhisthira
 */

public class DBUtils {
    private static final String CUSTOMER_KEY = "CUSTOMER_KEY";
    private static final String TABLE_KEY = "TABLE_KEY";

    public static void addCutomerList(List<Customer> customerList){
        Hawk.put(CUSTOMER_KEY, customerList);
    }

    public static void updateCutomerList(List<Customer> customerList){
        Hawk.delete(CUSTOMER_KEY);
        Hawk.put(CUSTOMER_KEY, customerList);
    }

    public static List<Customer> getCustomerList() {
        return Hawk.get(CUSTOMER_KEY);
    }

    public static void deleteCustomerList() {
        Hawk.delete(CUSTOMER_KEY);
    }

    public static void addTableList(List<Table> tableList) {
        Hawk.put(TABLE_KEY, tableList);
    }

    public static void updateTableList(List<Table> tableList) {
        Hawk.delete(TABLE_KEY);
        Hawk.put(TABLE_KEY, tableList);
    }

    public static List<Table> getTableList() {
        return Hawk.get(TABLE_KEY);
    }

    public static void deleteTableList() {
        Hawk.delete(TABLE_KEY);
    }

}
