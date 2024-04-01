package dao;

import models.Contact;
import models.SubjectContact;

import java.util.List;

public interface IContactDAO {
    List<Contact> getListUserContacts();

    List<SubjectContact> getListContactSubjects();

    int getIdContactSubjectByName(String subjectName);

    void addNewRecordUserContact(Integer userId, String fullName, String phone, String email, int subjectId, String message);
}
