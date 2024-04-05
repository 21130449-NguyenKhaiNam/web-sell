package dao;

import models.Address;

public class AddressDAOImpDAO implements IAddressDAO {
    @Override
    public Address getAddress(int userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT provinceId, districtId, wardId, detail FROM address WHERE userId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Address.class, userId).get(0);
    }
}
