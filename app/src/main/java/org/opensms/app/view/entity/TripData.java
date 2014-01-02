package org.opensms.app.view.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dewmal on 12/31/13.
 */
public class TripData {

    private long issue_emp_id;
    private Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
    private List<PreOrder> preOrders = new ArrayList<PreOrder>();

    public TripData() {
    }

    public long getIssue_emp_id() {
        return issue_emp_id;
    }

    public void setIssue_emp_id(long issue_emp_id) {
        this.issue_emp_id = issue_emp_id;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getUser_id(), customer);
    }

    public void addPreOrder(PreOrder preOrder) {
        preOrders.add(preOrder);
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    public List<PreOrder> getPreOrders() {
        return preOrders;
    }

    public void setPreOrders(List<PreOrder> preOrders) {
        this.preOrders = preOrders;
    }
}
