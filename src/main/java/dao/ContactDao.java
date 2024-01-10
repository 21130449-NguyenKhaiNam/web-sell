package dao;

import models.Contact;

import java.util.List;

public class ContactDao {

    public List<Contact> getListUserContacts(){
        return GeneralDao.executeQueryWithSingleTable("SELECT id, fullName, phone, email, `subject` FROM contacts", Contact.class);
    }

    public void addNewRecordUserContact(int userId, String fullName, String phone, String email, String subject, String message){
        GeneralDao.executeAllTypeUpdate("INSERT INTO contacts(userId, fullName, phone, email, subject, message) VALUES(?,?,?,?,?,?)", userId, fullName, phone, email, subject, message);
    }

    public static void main(String[] args) {
        GeneralDao.executeAllTypeUpdate("UPDATE vouchers SET availableTurns = ? WHERE code = ?", 3, "MGG50%");
    }
}
