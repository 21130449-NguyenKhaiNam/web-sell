package controller.api.admin.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import properties.CloudinaryProperties;
import services.image.CloudinaryUploadServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/admin/generateSignature")
public class CreateSignature extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberOfFiles = Integer.parseInt(req.getParameter("numberOfFiles"));
        String[] publicIds = req.getParameterValues("publicId[]");
        String[] folders = req.getParameterValues("folder[]");
        if (publicIds == null || publicIds.length != numberOfFiles || folders == null || folders.length != numberOfFiles) {
            resp.getWriter().write(gson.toJson("Invalid publicIds"));
            return;
        }
        Map<String, Object> paramsToSign = new HashMap<>();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("api_key", CloudinaryProperties.getApiKey());
        jsonObject.addProperty("cloud_name", CloudinaryProperties.getCloudName());
        JsonArray jsonArray = new JsonArray();
        String timestamp;
        for (int i = 0; i < numberOfFiles; i++) {
            timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            paramsToSign.put("public_id", publicIds[i]);
            paramsToSign.put("folder", folders[i]);
            paramsToSign.put("timestamp", timestamp);
            JsonObject file = new JsonObject();
            try {
                String signature = CloudinaryUploadServices.getINSTANCE().generateSignature(paramsToSign);
                file.addProperty("signature", signature);
                file.addProperty("timestamp", timestamp);
            } catch (Exception e) {
                file.addProperty("error", "Fail to generate signature");
            }
            jsonArray.add(file);
        }
        jsonObject.add("signs", jsonArray);
        resp.getWriter().write(gson.toJson(jsonObject));
    }
}
