package servlet;

import dao.TestDao;
import model.UInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet4")
public class Servlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user=request.getParameter("ID");
        String password=request.getParameter("password");
        UInfo uInfo=new UInfo();
        uInfo.setUser(user);
        uInfo.setCode(password);
        PrintWriter out =response.getWriter();
        if (TestDao.add(uInfo)){
            out.print(true);
        }
        else {
            out.print(false);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
