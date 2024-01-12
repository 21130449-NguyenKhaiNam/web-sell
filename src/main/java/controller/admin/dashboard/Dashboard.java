package controller.admin.dashboard;

import dao.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = "/Dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getAttribute("charts");
        request.getAttribute("charts__card");
        request.getAttribute("bar__chart");
        request.getAttribute("area__chart");


        request.getRequestDispatcher("dashboard.jsp").forward(request,response);
    }

}