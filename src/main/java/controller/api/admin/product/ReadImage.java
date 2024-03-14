package controller.api.admin.product;


import properties.PathProperties;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "readImage", value = "/read-image")
public class ReadImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String) request.getParameter("name");
        if (name != null) {
            String urlImg = getPathServer(request) + PathProperties.getINSTANCE().getPathProductWeb() + name;

            URL url = new URL(urlImg);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response.setContentType("application/octet-stream");

                InputStream inputStream = connection.getInputStream();
                OutputStream outputStream = response.getOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            connection.disconnect();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String getPathServer(HttpServletRequest request) {
        // Get the protocol, server name, and port number
        String protocol = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();

        // Get the context path
        String contextPath = request.getContextPath();

        // Construct the base URL including protocol, domain, port, and context path
        String baseURL = protocol + "://" + serverName + ":" + port + contextPath + "/";
        return baseURL;
    }
}