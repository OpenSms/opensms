package org.opensms.app.view.entity;

import java.util.Date;

/**
 * Created by dewmal on 1/1/14.
 */
public class PreOrder {

    private static final long serialVersionUID = 1L;

    private Long preOrderId;
    private Date preOrderDate;
    private int priority;
    private boolean isOpen;
    private long iisOrder_id;
    private int customer_id;

    public void updatePreOrder(org.opensms.app.db.entity.PreOrder preOrder){
        preOrderId=preOrder.getPreOrderId();
        preOrderDate=preOrder.getPreOrderDate();
        priority=preOrder.getPriority();
        isOpen=preOrder.getIsOpen();
        if (preOrder.getIisOrder()!=null) {
            iisOrder_id=preOrder.getIisOrder().getIisOrderId();
        }
        customer_id=preOrder.getCustomer().getId();
    }

    public Long getPreOrderId() {
        return preOrderId;
    }
    public void setPreOrderId(Long preOrderId) {
        this.preOrderId = preOrderId;
    }
    public Date getPreOrderDate() {
        return preOrderDate;
    }
    public void setPreOrderDate(Date preOrderDate) {
        this.preOrderDate = preOrderDate;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public long getIisOrder_id() {
        return iisOrder_id;
    }
    public void setIisOrder_id(long iisOrder_id) {
        this.iisOrder_id = iisOrder_id;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
