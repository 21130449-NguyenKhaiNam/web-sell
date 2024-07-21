package services;

import dao.AddressDAO;
import models.Address;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import properties.Map4dProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class AddressServices {
    private static AddressServices INSTANCE;
    private AddressDAO addressDAO;

    private AddressServices() {
        this.addressDAO = new AddressDAO();
    }

    public static AddressServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AddressServices();
        return INSTANCE;
    }

    boolean validateAddress(String address) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(Map4dProperties.getINSTANCE().getUrl())
                .addParameter("address", address)
                .addParameter("key", Map4dProperties.getINSTANCE().getApiKey()).build();
        HttpResponse response = Request.Get(uri).execute().returnResponse();
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode == 200;
    }

    public Integer insertAddress(Address address) throws URISyntaxException, IOException {
        if (!AddressServices.getINSTANCE().validateAddress(address.exportAddressString())) {
            return -1;
        }
        return addressDAO.insertAddress(address);
    }

    public boolean updateAddress(Address address) throws URISyntaxException, IOException {
        if (!AddressServices.getINSTANCE().validateAddress(address.exportAddressString())) {
            return false;
        }
        addressDAO.updateAddress(address);
        return true;
    }

    public List<Address> getAddress(int userId) {
        List<Address> addressList = addressDAO.getAddress(userId);
        if (addressList.isEmpty())
            return null;
        return addressList;
    }

    public boolean deleteAddress(int addressId, int userId) {
        List<Address> addressList = addressDAO.getAddress(userId);
        if (addressList.isEmpty())
            return false;
        if (addressList.stream().noneMatch(address -> address.getId() == addressId))
            return false;
        addressDAO.deleteAddress(addressId);
        return true;
    }
}