package services;

import models.Address;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import properties.Map4dProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class AddressServices {
    private static AddressServices INSTANCE;

    private AddressServices() {
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

    Date calculateLeadTime(Address addressFrom, Address addressTo) {
//        URI uri = new URIBuilder(Map4dProperties.getINSTANCE().getUrl())
//                .addParameter("address", address)
//                .addParameter("key", Map4dProperties.getINSTANCE().getApiKey()).build();
//        HttpResponse response = Request.Get(uri).execute().returnResponse();
//        int statusCode = response.getStatusLine().getStatusCode();
//        return statusCode == 200;
        return new Date();
    }
}
