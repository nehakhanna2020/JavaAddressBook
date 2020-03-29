package com.au.address.bean;

import java.io.Serializable;

public class ContactDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;

    public ContactDetails(String name, String primaryPhoneNumber) {
        this.name = name;
        this.phoneNumber = primaryPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}


