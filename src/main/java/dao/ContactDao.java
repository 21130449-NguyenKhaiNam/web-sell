package dao;

import models.Contacts;
import models.User;

import java.util.List;

public class ContactDao {

    public List<Contacts> getListUserContacts(){
        return GeneralDao.executeQueryWithSingleTable("SELECT id, fullName, phone, email, `subject` FROM contacts", Contacts.class);
    }

    public void addNewRecordUserContact(int userId, String fullName, String phone, String email, String subject, String message){
        GeneralDao.executeAllTypeUpdate("INSERT INTO contacts(userId, fullName, phone, email, subject, message) VALUES(?,?,?,?,?,?)", userId, fullName, phone, email, subject, message);
    }

}
