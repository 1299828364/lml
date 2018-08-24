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
import java.sql.Statement;
import java.util.List;

import static util.DBUtil.getConnection;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {

    private static final long serialVersionUID=1L;

    private InfoDao infoDao = InfoDao.getInfoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
//        byte[] data=name.getBytes("ISO-8859-1");
//        name=new String(data,"utf-8");

        String age=request.getParameter("age");
//        byte[] sss=age.getBytes("ISO-8859-1");
//        age=new String(sss,"utf-8");

        String sex=request.getParameter("sex");
//        byte[] bbb=sex.getBytes("ISO-8859-1");
//        sex=new String(bbb,"utf-8");

        String grade=request.getParameter("grade");

        String ID=request.getParameter("ID");

        PrintWriter out =response.getWriter();

        if(InfoDao.change(name,age,sex,ID,grade)){
            List<Info> infos=null;
            try {
                infos=infoDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.print(true);
        }else {
            out.print(false);
        }



    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
