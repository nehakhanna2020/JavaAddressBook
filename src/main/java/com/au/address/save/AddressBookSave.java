package com.au.address.save;

import com.au.address.bean.AddressBook;
import com.au.address.bean.ContactDetails;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

public class AddressBookSave {
    public static Logger logger = Logger.getLogger(AddressBookSave.class.getName());

    private List<AddressBook> addressBooks;
    public AddressBookSave() {
        addressBooks = new ArrayList<AddressBook>();
        addressBooks = readAddressBook();
    }
    public static void main(String[] args) {
        logger.info("Process  Started -------------------");
    }


    /**
     * Read the file and load into Objects
     * @return
     */
    public  static List<AddressBook> readAddressBook() {
        List<AddressBook> addressBooks = new ArrayList<AddressBook>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    "AddressBooks.txt"));
            if(ois.readObject() != null){
                addressBooks = (List<AddressBook>) ois.readObject();
            }
            ois.close();
        } catch (EOFException ex) {
            logger.info("Exception found: "+ex);
        }catch (FileNotFoundException ex) {
            logger.info("No address books stored");
        } catch (Exception e) {
            logger.info("Exception found: "+e);
        }

           return addressBooks;
    }

    /**
     * store the address in the file with name and contact details
     * @param addressBooks
     */
    public void storeAddressBooks(List<AddressBook> addressBooks) {
        try {
            FileOutputStream fos = new FileOutputStream("AddressBooks.txt");
            OutputStreamWriter oos = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            for (AddressBook addressBook : addressBooks) {
                oos.write(String.valueOf(addressBook.getName()));
                oos.write("\t");
                for (ContactDetails c : addressBook.getContacts()) {
                    oos.write(String.valueOf(c.getName()));
                    oos.write("\t");
                }
                oos.write("\n");
            }

            oos.close();
        } catch (IOException e) {
            logger.info("Exception found: "+e);
        }

    }

    public void addAddressBook(AddressBook addressbook) {
        if(!addressBooks.contains(addressbook)){
            addressBooks.add(addressbook);
            storeAddressBooks(addressBooks);
        }

    }


    public void removeAllAddressBooks(){
        addressBooks.clear();
        storeAddressBooks(addressBooks);

    }

    /**
     * Get uniqueContacts from addressBook
     * @param addressBook
     * @return Collection<ContactDetails>
     */
    public Collection<ContactDetails> getUniqueContacts(List<AddressBook> addressBook)
    {
        Set<ContactDetails> commonContacts = new HashSet<ContactDetails>();
        Set<ContactDetails> uniqueContacts = new HashSet<ContactDetails>();
        for (AddressBook addressBookObj : addressBooks) {
            List<ContactDetails> contacts = addressBookObj.getContacts();
            List<ContactDetails> allContacts = new ArrayList<ContactDetails>();
            allContacts.addAll(uniqueContacts);
            allContacts.addAll(contacts);
            contacts.retainAll(uniqueContacts);
            commonContacts.addAll(contacts);
            allContacts.removeAll(commonContacts);

            uniqueContacts.clear();
            uniqueContacts.addAll(allContacts);
        }
        return uniqueContacts;



}

    public List<AddressBook> getAddressBooks() {
        return addressBooks;
    }
}
