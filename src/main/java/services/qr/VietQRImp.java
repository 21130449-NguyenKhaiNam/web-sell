package services.qr;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import properties.Map4dProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

public class VietQRImp implements IQR {
    private final static String URL_BANK = "https://api.vietqr.io/v2/banks";
    Gson gson = new Gson();

    @Override
    public String toQRCode(String addInfo, Double amount) {
        return "";
    }

    public String getBinBank(String bankName) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(URL_BANK).build();
        HttpResponse response = Request.Get(uri).execute().returnResponse();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder json = new StringBuilder();
            String line;
            if ((line = reader.readLine()) != null) {
                line = line.trim();
            }
            if (line.contains("code")) {
                StringTokenizer st = new StringTokenizer(line, ":");
                st.nextToken();
                if (st.hasMoreTokens()) {
                    String code = st.nextToken();
                    if (code.equalsIgnoreCase(bankName)) {
                        line = reader.readLine();
                        line = line.trim();
                        StringTokenizer st1 = new StringTokenizer(line, ":");
                        st1.nextToken();
                        if (st1.hasMoreTokens()) {
                            return st1.nextToken().trim();
                        }
                    }
                }

            }
            json.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
