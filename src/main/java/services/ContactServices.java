package services;

import dao.ContactDao;
import models.Contact;
import models.SubjectContact;

import java.util.List;
import java.util.Map;

public class ContactServices {

    private ContactDao contactDao;

    private static ContactServices INSTANCE;

    public ContactServices() {
        contactDao = new ContactDao();
    }

    public static ContactServices getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ContactServices();
        }
        return INSTANCE;
    }

    public List<Contact> getListUserContacts(){
        return contactDao.getListUserContacts();
    }

    public List<SubjectContact> getListContactSubjects(){
        return contactDao.getListContactSubjects();
    }

    public int getIdContactSubjectByName(String subjectName){
        return contactDao.getIdContactSubjectByName(subjectName);
    }

    public void addNewRecordUserContact(Integer userId, String fullName, String phone, String email, int subjectId, String message){
        contactDao.addNewRecordUserContact(userId, fullName, phone, email, subjectId, message);
    }
}
