package com.au.address.save;

import com.au.address.bean.AddressBook;
import com.au.address.bean.ContactDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;



import java.util.*;
import java.util.logging.Logger;


public class AddressBookSaveTest {

    public Logger logger = Logger.getLogger(AddressBookSaveTest.class.getName());
    private ContactDetails c1, c2, c3, c4, c5, c6;
    private AddressBookSave addressBookMain;

    @Before
    public void setUp() {
        addressBookMain = new AddressBookSave();
        createNewContacts();
    }

    @Test
    public void main() {
        AddressBookSaveTest test = new AddressBookSaveTest();
        test.setUp();
        test.sortContactByName();
        test.uniqueFriendsFromTwoAddressBooks();
        test.uniqueFriendsFromThreeAddressBooks();

    }



    private void createNewContacts() {
        c1 = new ContactDetails("Bob", "02 9218 5479");
        c2 = new ContactDetails("Mary", "04 9218 5479");
        c3 = new ContactDetails("Jane", "02 9 605 3147");
        c4 = new ContactDetails("John", "02 605 3147");
        c5 = new ContactDetails("Ruby", "03 9 605 3147");
        c6 = new ContactDetails("Seal", "03 9 605 3147");

    }

    private void addTwoAddressBooks() {

        AddressBook ab1 = new AddressBook("ab1");
        AddressBook ab2 = new AddressBook("ab2");
        List<ContactDetails> contactDetailsList1= new ArrayList<>();
        List<ContactDetails> contactDetailsList2= new ArrayList<>();


        contactDetailsList1.add(c1);
        contactDetailsList1.add(c2);
        contactDetailsList1.add(c3);

        contactDetailsList2.add(c2);
        contactDetailsList2.add(c4);
        contactDetailsList2.add(c3);

        ab1.setContacts(contactDetailsList1);
        ab2.setContacts(contactDetailsList2);
        addressBookMain.addAddressBook(ab1);
        addressBookMain.addAddressBook(ab2);



    }

    private void addThreeAddressBooks() {
        addressBookMain.removeAllAddressBooks();

        AddressBook ab1 = new AddressBook("ab1");
        AddressBook ab2 = new AddressBook("ab2");
        AddressBook ab3 = new AddressBook("ab3");
        List<ContactDetails> contactDetailsList1= new ArrayList<>();
        List<ContactDetails> contactDetailsList2= new ArrayList<>();
        List<ContactDetails> contactDetailsList3= new ArrayList<>();



        // AddContacts to the addressBooks
        contactDetailsList1.add(c1);
        contactDetailsList1.add(c2);
        contactDetailsList1.add(c3);

        contactDetailsList2.add(c2);
        contactDetailsList2.add(c4);
        contactDetailsList3.add(c3);

        contactDetailsList3.add(c1);
        contactDetailsList3.add(c5);
        contactDetailsList3.add(c6);

        ab1.setContacts(contactDetailsList1);
        ab2.setContacts(contactDetailsList2);
        ab3.setContacts(contactDetailsList3);

        addressBookMain.addAddressBook(ab1);
        addressBookMain.addAddressBook(ab2);
        addressBookMain.addAddressBook(ab3);

    }

    private void printInput(List<AddressBook> addressBooks){
        logger.info("==Input==");

        for (AddressBook addressBook : addressBooks) {
            logger.info("Address Book: " + addressBook.getName());
            logger.info("Friends:");
            for (ContactDetails c : addressBook.getContacts()) {
                logger.info(c.getName());
            }
            logger.info("");

        }
    }

    private void printOutput(List<AddressBook> addressBooks,Collection<ContactDetails> uniqueContacts) {
        logger.info("==Output==");
        logger.info("Address Books: ");
        String names = "";
        for (AddressBook addressBook : addressBooks) {
            names += addressBook.getName() + ", ";
        }

        if (names.length() > 0) {
            logger.info(names.substring(0, names.lastIndexOf(",")));
        }
        for (ContactDetails c : uniqueContacts) {
            logger.info(c.getName());
        }
        logger.info("\n");
    }
    @Test
    public void sortContactByName() {
        logger.info("========== Display the list of friends sorted by their name ==========");

        AddressBook addressBook = new AddressBook("ab1");
        addressBook.addContact(c5);
        addressBook.addContact(c1);
        addressBook.addContact(c4);
        addressBook.addContact(c2);
        addressBook.addContact(c3);


        logger.info("==Input==");
        logger.info("Address Book: " + addressBook.getName());
        logger.info("Friends:");
        for (ContactDetails contact : addressBook.getContacts()) {
            logger.info(contact.getName());
            logger.info(contact.getPhoneNumber());
        }

        addressBook.getContacts().sort((ContactDetails o1, ContactDetails o2)->o1.getName().compareTo(o2.getName()));
        logger.info("==Output==");
        logger.info("Address Book: " + addressBook.getName());
        logger.info("Friends:");
        for (ContactDetails contact : addressBook.getContacts()) {
            logger.info(contact.getName());
            logger.info(contact.getPhoneNumber());
        }
        logger.info("\n");

        // Sorted list
        assertTrue("Bob".equals(addressBook.getContacts().get(0).getName()));
        assertTrue("Jane".equals(addressBook.getContacts().get(1).getName()));
        assertTrue("John".equals(addressBook.getContacts().get(2).getName()));
        assertTrue("Mary".equals(addressBook.getContacts().get(3).getName()));
        assertTrue("Ruby".equals(addressBook.getContacts().get(4).getName()));

    }
    @Test
    public void uniqueFriendsFromTwoAddressBooks() {
        logger.info("========== Unique Friends from Two Address Books ==========");

        addTwoAddressBooks();

        List<AddressBook> addressBooks = addressBookMain.getAddressBooks();

        printInput(addressBooks);
        Collection<ContactDetails> uniqueContacts = addressBookMain.getUniqueContacts(addressBooks);
        printOutput(addressBooks, uniqueContacts);

        Set<ContactDetails> expected = new HashSet<ContactDetails>(Arrays.asList(c1, c4));

        assertTrue(uniqueContacts.equals(expected));
    }
    @Test
    public void uniqueFriendsFromThreeAddressBooks() {
        logger.info("========== Unique Friends from Three Address Books ==========");

        addThreeAddressBooks();

        List<AddressBook> addressBooks = addressBookMain.getAddressBooks();
        printInput(addressBooks);

        // Get unique contacts from three AddressBooks
        Collection<ContactDetails> uniqueContacts = addressBookMain.getUniqueContacts(addressBooks);

        printOutput(addressBooks, uniqueContacts);


        Set<ContactDetails> expected = new HashSet<ContactDetails>(Arrays.asList(c4, c5, c6));

        assertTrue(uniqueContacts.equals(expected));
    }
}