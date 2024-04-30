package controller.api.admin.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProcessOrderAdmin", value = "/api/admin/order")
public class ProcessOrderAdmin extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        request.setAttribute("action", action);

        RequestDispatcher requestDispatcher = null;
        switch (action){
            case "seeDetail" -> {
                String orderId = request.getParameter("orderId");
                request.setAttribute("orderId", orderId);
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/detail");
            }

            case "showDialogUpdate" -> {
                String orderId = request.getParameter("orderId");
                request.setAttribute("orderId", orderId);
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/update-dialog");
            }

            case "saveUpdateStatus" -> {
                String orderId = request.getParameter("orderId");
                request.setAttribute("orderId", orderId);
                String orderStatusId = request.getParameter("orderStatusId");
                String transactionStatusId = request.getParameter("transactionStatusId");
                request.setAttribute("orderStatusId", orderStatusId);
                request.setAttribute("transactionStatusId", transactionStatusId);
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/update-status");
            }

            case "removeOrder" -> {
                String[] multipleOrderId = request.getParameterValues("multipleOrderId");
                request.setAttribute("multipleOrderId", multipleOrderId);
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/remove");
            }

            case "cancelOrder" -> {
                String[] multipleOrderId = request.getParameterValues("multipleOrderId");
                request.setAttribute("multipleOrderId", multipleOrderId);
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/cancel");
            }
        }

        if (requestDispatcher != null){
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}