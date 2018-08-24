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

@WebServlet(name = "ServletTEmp1")
public class ServletTemp1 extends HttpServlet {
    private InfoDao infoDao = InfoDao.getInfoDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Info> infos=null;
        try {
            infos=infoDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out =response.getWriter();
        out.print(infos);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
