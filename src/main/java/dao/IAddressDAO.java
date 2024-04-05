package dao;

import models.Address;

public interface IAddressDAO {
    public Address getAddress(int userId);
}
