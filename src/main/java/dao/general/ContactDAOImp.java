package dao.general;

import annotations.LogTable;
import models.Contact;
import models.SubjectContact;

import java.util.List;

public class ContactDAOImp implements IContactDAO {
    @Override
    public <T> int insert(T o) {
        if(o instanceof Contact) {
            Contact c = (Contact) o;
            String query = "INSERT INTO contact(userId, fullName, phone, email, subjectId, message) VALUES(?,?,?,?,?,?)";
            GeneralDAOImp.executeAllTypeUpdate(query, c.getUserId(), c.getFullName(), c.getPhone(), c.getEmail(), c.getSubject(), c.getMessage());
            return 1;
        } else {
            throw  new UnsupportedOperationException("ContactDAOImp >> Phương thức insert không hỗ trợ kiểu tham số khác");
        }
    }

    public List<Contact> getListUserContacts() {
        String query = "SELECT id, fullName, phone, email, `subject` FROM contacts";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Contact.class);
    }

    public List<SubjectContact> getListContactSubjects() {
        String sql = "SELECT id, subjectName FROM contact_subjects";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, SubjectContact.class);
    }


    public int getIdContactSubjectByName(String subjectName) {
        String query = "SELECT id FROM contact_subjects WHERE subjectName = ?";
        return (int) GeneralDAOImp.executeQueryWithJoinTables(query, subjectName).get(0).get("id");
    }
}
