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

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");


        String age=request.getParameter("age");


        String sex=request.getParameter("sex");


        String grade;
        if(request.getParameter("grade")==null){
            grade="0";
        }else {
            grade=request.getParameter("grade");
        }

        String englishgrade;

        String mathgrade;

        if(request.getParameter("englishgrade")==null){
            englishgrade="0";
        }else {
            englishgrade=request.getParameter("englishgrade");
        }

        if(request.getParameter("mathgrade")==null){
            mathgrade="0";
        }else {
            mathgrade=request.getParameter("mathgrade");
        }

        Info info=new Info();
        info.setSex(sex);
        info.setName(name);
        info.setGrade(Integer.valueOf(grade));
        info.setAge(Integer.valueOf(age));
        info.setEnglishgrade(Integer.valueOf(englishgrade));
        info.setMathgrade(Integer.valueOf(mathgrade));
        System.out.println(name+" "+age);
        PrintWriter out =response.getWriter();
        if(TestDao.add(info)){
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
