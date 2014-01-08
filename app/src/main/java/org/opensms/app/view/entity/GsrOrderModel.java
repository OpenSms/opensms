package org.opensms.app.view.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by dewmal on 1/3/14.
 */
public class GsrOrderModel {

    private List<String> preOrders;

    private Map<String,Double> itemList;

    private String customer;

    private String sales_person;


    public List<String> getPreOrders() {
        return preOrders;
    }

    public void setPreOrders(List<String> preOrders) {
        this.preOrders = preOrders;
    }

    public Map<String, Double> getItemList() {
        return itemList;
    }

    public void setItemList(Map<String, Double> itemList) {
        this.itemList = itemList;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSales_person() {
        return sales_person;
    }

    public void setSales_person(String sales_person) {
        this.sales_person = sales_person;
    }

    @Override
    public String toString() {
        return "GsrOrderModel{" +
                "preOrders=" + preOrders +
                ", itemList=" + itemList +
                ", customer='" + customer + '\'' +
                ", sales_person='" + sales_person + '\'' +
                '}';
    }
}
