package org.opensms.app.view.entity;

import org.opensms.app.db.entity.UserContactDetail;

/**
 * Created by dewmal on 1/1/14.
 */
public class Customer {

    private int user_id;
    private String username;
    private String nicNumber;
    private String contactNumber;
    private String email;
    private String firstName;


    public void setCustomerDetails(org.opensms.app.db.entity.Customer customer, UserContactDetail contactDetail) {
        if (customer != null) {
            username = customer.getUser().getUsername();
            firstName = customer.getName();
            user_id = customer.getUserId();

        }

        if (contactDetail != null) {
            contactNumber = contactDetail.getPhoneNumber();
            email = contactDetail.getEmail();
        }

    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
