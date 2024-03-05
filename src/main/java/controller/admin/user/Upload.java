package controller.admin.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "upload", value = "/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class Upload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePath = request.getPart("file");
        String fileName = filePath.getSubmittedFileName();
        ServletContext servletContext = getServletContext();
        File root = new File(servletContext.getRealPath("/") + "data/");
        if (!root.exists()) root.mkdirs();
//        Move
        for (Part part : request.getParts()) {
            part.write(root.getAbsolutePath() + "/" + fileName);
        }
        response.getWriter().println("Success");
    }
}