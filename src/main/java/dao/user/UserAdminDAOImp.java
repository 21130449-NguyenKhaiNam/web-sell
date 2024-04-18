package dao.user;

import dao.general.GeneralDAO;
import models.User;

import java.util.List;

public class UserAdminDAOImp implements IUserAdminDAO {
    @Override
    public List<User> selectByEmail(String email, String isVerify) {
        String query;
        if (isVerify == null) {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ?";
            return GeneralDAO.executeQueryWithSingleTable(query, User.class, email);
        } else {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ? AND isVerify = ?";
            return GeneralDAO.executeQueryWithSingleTable(query, User.class, email, isVerify);
        }
    }

    @Override
    public List<User> selectALl() {
        String query ="Select id, username, email, fullname, gender, phone, address, birthDay, role from users ";
        return GeneralDAO.executeQueryWithSingleTable(query, User.class);
    }

    @Override
    public List<User> searchUsersByName(String search) {
        String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE LOWER(username) LIKE ? OR LOWER(email) LIKE ? ";
        return GeneralDAO.executeQueryWithSingleTable(query, User.class, "%" + search.toLowerCase() + "%", "%" + search.toLowerCase() + "%");
    }

    @Override
    public List<User> getUserByIdProductDetail(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT users.id, users.fullName ")
                .append("FROM users JOIN (orders JOIN order_details ON orders.id = order_details.orderId) ON users.id = orders.userId ")
                .append("WHERE order_details.id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), User.class, orderDetailId);
    }
}
