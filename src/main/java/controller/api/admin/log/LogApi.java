package controller.api.admin.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Log;
import org.json.JSONObject;
import services.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/admin/logAdmin")
public class LogApi extends HttpServlet {
    private LogService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer draw = Integer.parseInt(req.getParameter("draw"));
        Integer start = Integer.parseInt(req.getParameter("start"));
        Integer length = Integer.parseInt(req.getParameter("length"));

        List<Log> logs = service.getINSTANCE().getLog(start, length);
        long size = service.getINSTANCE().getTotal();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("draw", draw);
        jsonObject.put("recordsTotal", size);
        jsonObject.put("recordFiltered", size);
        jsonObject.put("data", logs);
        PrintWriter writer = resp.getWriter();
        writer.write(mapper.writeValueAsString(jsonObject.toMap()));
        writer.flush();
    }
}
