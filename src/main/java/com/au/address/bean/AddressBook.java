package com.au.address.bean;

import com.au.address.bean.ContactDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<ContactDetails> contacts;

    public AddressBook(String name) {
        this(name, new ArrayList<ContactDetails>());
    }

    public AddressBook(String name, List<ContactDetails> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    public void addContact(ContactDetails contact) {
        if (contacts != null) {
            contacts.add(contact);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContactDetails> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDetails> contacts) {
        this.contacts = contacts;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AddressBook) {
            AddressBook addressBook = (AddressBook) obj;
            return name.equals(addressBook.getName());
        }

        return false;
    }

    public int hashCode() {
        return (name.length());
    }

}
