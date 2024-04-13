package dao.general;

import annotations.LogTable;
import models.Contact;
import models.SubjectContact;

import java.util.List;
@LogTable(LogTable.CONTACT)
public class ContactDAOImp implements IContactDAO {

    public List<Contact> getListUserContacts(){
        return GeneralDAOImp.executeQueryWithSingleTable("SELECT id, fullName, phone, email, `subject` FROM contacts", Contact.class);
    }

    public List<SubjectContact> getListContactSubjects(){
        String sql = "SELECT id , subjectName FROM contact_subjects";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, SubjectContact.class);
    }


    public int getIdContactSubjectByName(String subjectName){
        return (int) GeneralDAOImp.executeQueryWithJoinTables("SELECT id FROM contact_subjects WHERE subjectName = ?", subjectName).get(0).get("id");
    }

    public void addNewRecordUserContact(Integer userId, String fullName, String phone, String email, int subjectId, String message){
        GeneralDAOImp.executeAllTypeUpdate("INSERT INTO contact(userId, fullName, phone, email, subjectId, message) VALUES(?,?,?,?,?,?)", userId, fullName, phone, email, subjectId, message);
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
