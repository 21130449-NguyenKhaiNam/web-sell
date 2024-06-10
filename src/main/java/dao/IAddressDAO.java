package dao;

import models.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressDAO {
    List<Address> getAddress(int userId);

    void insertAddress(Address address);

    void updateAddress(Address address);

    void deleteAddress(int addressId);
}
