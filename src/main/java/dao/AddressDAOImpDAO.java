package dao;

import models.Address;

import java.util.List;
import java.util.Optional;

public class AddressDAOImpDAO implements IAddressDAO {
    @Override
    public Optional<Address> getAddress(int userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT provinceId, districtId, wardId, detail FROM address WHERE userId = ?");
        List<Address> addresses = GeneralDao.executeQueryWithSingleTable(sql.toString(), Address.class, userId);
        if (addresses.isEmpty()) {
            return Optional.empty(); // Return an empty Optional
        } else {
            return Optional.ofNullable(addresses.get(0)); // Wrap the first address in an Optional
        }
    }
}
