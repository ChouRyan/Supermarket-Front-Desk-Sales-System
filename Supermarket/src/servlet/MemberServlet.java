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
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 权限检查
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        MemberDao memberDao = new MemberDaoImpl();

        try {
            if ("register".equals(action)) {
                request.getRequestDispatcher("member_register.jsp").forward(request, response);

            } else if ("list".equals(action)) {
                List<Member> members = memberDao.findAll();
                request.setAttribute("members", members);
                request.getRequestDispatcher("member_list.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("会员查询失败", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 权限检查
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        MemberDao memberDao = new MemberDaoImpl();

        try {
            if ("register".equals(action)) {
                // 处理注册
                String name = request.getParameter("name");
                String password = request.getParameter("password");

                Member member = new Member();
                member.setMemberName(name);
                member.setPassword(password); // 明文存储
                member.setPoints(0); // 初始积分

                memberDao.addMember(member);
                response.sendRedirect("member?action=list");

            } else if ("update".equals(action)) {
                // 修改会员信息
                int id = Integer.parseInt(request.getParameter("id"));
                Member member = memberDao.findById(id);
                if (member != null) {
                    member.setMemberName(request.getParameter("name"));
                    member.setPoints(Integer.parseInt(request.getParameter("points")));
                    memberDao.updateMember(member);
                }
                response.sendRedirect("member?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException("会员操作失败", e);
        }
    }
}