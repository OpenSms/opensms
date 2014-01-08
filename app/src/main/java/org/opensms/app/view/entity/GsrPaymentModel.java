package org.opensms.app.view.entity;

/**
 * Created by dewmal on 1/8/14.
 */
public class GsrPaymentModel {

    private String gsrOrder;
    private String amount;
    private String paymentmethod;

    public String getGsrOrder() {
        return gsrOrder;
    }

    public void setGsrOrder(String gsrOrder) {
        this.gsrOrder = gsrOrder;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
