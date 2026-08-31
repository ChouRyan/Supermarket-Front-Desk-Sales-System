package servlet;

import dao.MemberDao;
import dao.MemberDaoImpl;
import entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MemberDao memberDao = new MemberDaoImpl();
        try {
            // 根据用户名查找会员
            Member member = memberDao.findByName(username);
            if (member != null && member.getPassword().equals(password)) {
                // 登录成功，存储Session
                HttpSession session = request.getSession();
                session.setAttribute("user", member);
                response.sendRedirect("main.jsp");
            } else {
                // 登录失败
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("登录验证失败", e);
        }
    }
}