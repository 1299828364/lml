package servlet;

import dao.TestDao;
import model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletTemp")
public class ServletTemp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition=null;
        if(request.getParameter("condition")!=null){
            condition=new String(request.getParameter("condition").getBytes("ISO-8859-1"),"UTF-8");
            condition=" name LIKE '%"+condition+"%'";
        }


        List<Info> infos=null;
        infos= TestDao.findAll(Info.class,condition);

        request.setAttribute("infos",infos);
        request.getRequestDispatcher("jsp/information.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
