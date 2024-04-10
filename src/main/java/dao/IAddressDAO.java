package dao;

import models.Address;

import java.util.Optional;

public interface IAddressDAO {
    Optional<Address> getAddress(int userId);
}
