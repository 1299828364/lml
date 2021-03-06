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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Info info=new Info();

        if (request.getParameter("name")!=null){
            info.setName(request.getParameter("name"));
        }

        if (request.getParameter("age")!=null){
            info.setAge(Integer.valueOf(request.getParameter("age")));
        }

        if (request.getParameter("sex")!=null){
            info.setSex(request.getParameter("name"));
        }

        if (request.getParameter("grade")!=null){
            info.setGrade(Integer.valueOf(request.getParameter("grade")));
        }

        if (request.getParameter("mathgrade")!=null){
            info.setMathgrade(Integer.valueOf(request.getParameter("mathgrade")));
        }

        if (request.getParameter("englishgrade")!=null){
            info.setEnglishgrade(Integer.valueOf(request.getParameter("englishgrade")));
        }


         String ID=request.getParameter("ID");



        PrintWriter out =response.getWriter();
        String condition="ID="+ID;
        try {
            if(TestDao.updateSQL(info,condition)){
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
