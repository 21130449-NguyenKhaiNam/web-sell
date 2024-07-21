package dao;

import models.Address;

import java.util.List;

public class AddressDAO implements IAddressDAO {
    @Override
    public List<Address> getAddress(int userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, province, ward, district, detail FROM address WHERE userId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Address.class, userId);
    }

    public Integer insertAddress(Address address) {
        String sql = "INSERT INTO address (userId, province, district, ward, detail) VALUES (?, ?,?,?,?)";
        return GeneralDao.executeInsert(sql, address.getUserId(), address.getProvince(), address.getDistrict(), address.getWard(), address.getDetail());
    }

    public void updateAddress(Address address) {
        String sql = "UPDATE address SET province = ?, district = ?, ward = ?, detail = ? WHERE userId = ? AND id = ";
        List<Address> list = GeneralDao.executeQueryWithSingleTable(sql.toString(), Address.class, address.getUserId());
        GeneralDao.executeAllTypeUpdate(sql, address.getProvince(), address.getDistrict(), address.getWard(), address.getDetail(), address.getUserId(), address.getId());
    }

    @Override
    public void deleteAddress(int addressId) {
        String sql = "DELETE FROM address WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(sql, addressId);
    }
}