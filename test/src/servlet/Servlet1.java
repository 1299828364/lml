package servlet;

import dao.InfoDao;
import model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

    private InfoDao infoDao = InfoDao.getInfoDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");


        String age=request.getParameter("age");


        String sex=request.getParameter("sex");


        String grade=request.getParameter("grade");


        System.out.println(name+" "+age);
        PrintWriter out =response.getWriter();
        if(InfoDao.add(name,age,sex,grade)){
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
