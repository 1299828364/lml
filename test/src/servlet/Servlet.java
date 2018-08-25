package servlet;

import dao.TestDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    private TestDao testDao = TestDao.getTestDao();
    public Servlet(){
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password=request.getParameter("password");
        String jj=request.getParameter("check");

        PrintWriter out =response.getWriter();

        if(TestDao.check(name,password)){
            out.print(true);
            if (jj.equals("true")) {
            Cookie nameCookie = new Cookie("name" , name);
            nameCookie.setMaxAge(600);
            Cookie passwordCookie = new Cookie("password" , password);
            passwordCookie.setMaxAge(600);
//将Cookie写入客户端
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);

        }
        else {
            System.out.println(165165156);
        }


//            request.setAttribute("infos",infos);
//            request.getRequestDispatcher("jsp/information.jsp").forward(request,response);

        }else {
//            response.sendRedirect("jsp/logIn.jsp");
            out.print(false);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
