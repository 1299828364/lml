package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    private InfoDao infoDao = InfoDao.getInfoDao();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String ck = req.getParameter("ck");
        if ("on".equals(ck)) {

            // 构造Cookie对象
            // 添加到Cookie中
            Cookie cookie = new Cookie("userinfo", username + "-" + password);

            // 设置过期时间
            cookie.setMaxAge(600);

            // 存储
            resp.addCookie(cookie);
//            List<Info> infos=null;
//            try {
//                infos=infoDao.findAll();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            req.setAttribute("infos",infos);
//            req.getRequestDispatcher("jsp/information.jsp").forward(req,resp);
        }



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
