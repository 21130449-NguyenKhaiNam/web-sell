package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Contact;
import models.SubjectContact;

import java.util.List;

public interface IContactDAO extends IDAO {
    //    Lấy ra danh sách tất cả các liên hệ của người dùng
    List<Contact> getListUserContacts();

    //    Lấy ra danh sách tất cả các chủ đề thắc mắc được người dùng quy định trước (table contact_subjects)
    List<SubjectContact> getListContactSubjects();

    //    Lấy ra id của chủ đề thắc mắc dựa vào tên chủ đề (table contact_subjects)
    @WriteLog(WriteLog.SELECT)
    int getIdContactSubjectByName(@LogParam("subject-name") String subjectName);

    //    Thêm một liên hệ mới của người dùng
    @WriteLog(WriteLog.INSERT)
    void addNewRecordUserContact(@LogParam("id-user") Integer userId,@LogParam("full-name") String fullName,@LogParam("phone") String phone,@LogParam("email") String email,@LogParam("id-subject") int subjectId,@LogParam("message") String message);
}
