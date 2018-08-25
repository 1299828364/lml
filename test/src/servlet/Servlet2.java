package servlet;

import dao.TestDao;
import model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {

    private static final long serialVersionUID=1L;

    private InfoDao infoDao = InfoDao.getInfoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");

        String age=request.getParameter("age");

        String sex=request.getParameter("sex");

        String grade=request.getParameter("grade");

        String ID=request.getParameter("ID");

        PrintWriter out =response.getWriter();
        String condition="ID="+ID;
        Info info=new Info();
        info.setAge(Integer.valueOf(age));
        info.setName(name);
        info.setSex(sex);
        info.setGrade(Integer.valueOf(grade));
        try {
            if(TestDao.updateSQL(info,condition)){
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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
