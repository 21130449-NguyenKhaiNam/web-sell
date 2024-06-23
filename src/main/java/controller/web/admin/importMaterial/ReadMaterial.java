package controller.web.admin.importMaterial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import models.Material;
import org.json.JSONObject;
import services.admin.AdminMaterialServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@WebServlet("/readMaterial")
public class ReadMaterial extends HttpServlet implements Serializable {
    private final int PER_PAGE = 10;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer draw = Integer.parseInt(request.getParameter("draw"));
        Integer start = Integer.parseInt(request.getParameter("start"));
        String searchValue = request.getParameter("search[value]");// từ khóa tìm kiếm
        String orderColumn = request.getParameter("order[0][column]"); // index của cột khi ấn vào sort
        String orderDir = request.getParameter("order[0][dir]"); // kiểu sort

        String[] columns = {"id", "name", "remain", "createdAt"};

        String orderBy = orderDir != null ? orderDir : "asc";
        String chooseColumn = orderColumn != null ? columns[Integer.parseInt(orderColumn)] : columns[0];

        List<Map<String, Object>> materialList = AdminMaterialServices.getINSTANCE().findPerPage(PER_PAGE, start, searchValue, chooseColumn, orderBy);
        long size = AdminMaterialServices.getINSTANCE().countAll();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("draw", draw);
        jsonObject.put("recordsTotal", size);
        jsonObject.put("recordsFiltered", size);
        jsonObject.put("data", materialList);

        // Send response
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(jsonObject.toMap()));
        writer.flush();
    }
}
