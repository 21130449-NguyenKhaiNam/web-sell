package dao.general;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.Contact;
import models.SubjectContact;

import java.util.List;

public interface IContactDAO extends IDAO {
    //    Lấy ra danh sách tất cả các liên hệ của người dùng
    List<Contact> getListUserContacts();

    //    Lấy ra danh sách tất cả các chủ đề thắc mắc được người dùng quy định trước (table contact_subjects)
    List<SubjectContact> getListContactSubjects();

    //    Lấy ra id của chủ đề thắc mắc dựa vào tên chủ đề (table contact_subjects)
    int getIdContactSubjectByName(String subjectName);

}
