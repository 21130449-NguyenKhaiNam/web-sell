package dao.general;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.Contact;
import models.SubjectContact;

import java.util.List;

public interface IContactDAO extends IDAO {
    @Override
    default <T> T selectById(Object id) {
        throw new UnsupportedOperationException("IContactDAO >> Phương thức SELECT không được hỗ trợ");
    }

    @Override
    default <T> int insertAll(List<T> list) {
        throw new UnsupportedOperationException("IContactDAO >> Phương thức INSERT nhiều phần tử không được hỗ trợ");
    }

    @Override
    default int update(Object o) {
        throw new UnsupportedOperationException("IContactDAO >> Phương thức UPDATE từng phần tử không được hỗ trợ");
    }

    //    Lấy ra danh sách tất cả các liên hệ của người dùng
    List<Contact> getListUserContacts();

    //    Lấy ra danh sách tất cả các chủ đề thắc mắc được người dùng quy định trước (table contact_subjects)
    List<SubjectContact> getListContactSubjects();

    //    Lấy ra id của chủ đề thắc mắc dựa vào tên chủ đề (table contact_subjects)
    @WriteLog(WriteLog.SELECT)
    int getIdContactSubjectByName(@LogParam("subject-name") String subjectName);

}
