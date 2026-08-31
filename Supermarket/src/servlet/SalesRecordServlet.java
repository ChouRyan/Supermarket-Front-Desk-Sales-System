package servlet;

import dao.*;
import entity.Inventory;
import entity.Member;
import entity.Product;
import entity.SalesRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/sales")
public class SalesRecordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 权限检查
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        SalesRecordDao salesDao = new SalesRecordDaoImpl();

        try {
            if ("list".equals(action)) {
                List<SalesRecord> records = salesDao.findAll();
                request.setAttribute("salesRecords", records);
                request.getRequestDispatcher("sales_list.jsp").forward(request, response);

            } else if ("return".equals(action)) {
                int salesId = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("salesId", salesId);
                request.getRequestDispatcher("sales_return.jsp").forward(request, response);
            } else if ("create".equals(action)) {
                // 显示创建销售页面
                ProductDao productDao = new ProductDaoImpl();
                MemberDao memberDao = new MemberDaoImpl();
                List<Product> products = productDao.findAllProducts();
                List<Member> members = memberDao.findAll();
                request.setAttribute("products", products);
                request.setAttribute("members", members);
                request.getRequestDispatcher("sales_create.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("销售记录查询失败", e);
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
        SalesRecordDao salesDao = new SalesRecordDaoImpl();
        InventoryDao inventoryDao = new InventoryDaoImpl();

        try {
            if ("create".equals(action)) {
                // 创建销售记录（需要事务）
                int productId = Integer.parseInt(request.getParameter("productId"));
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // 1. 检查库存
                Inventory inv = inventoryDao.findByProductId(productId);
                if (inv.getQuantity() < quantity) {
                    throw new ServletException("库存不足");
                }

                // 2. 添加销售记录
                SalesRecord record = new SalesRecord();
                // 需要生成唯一ID，这里简单使用当前时间戳
                record.setSalesID((int) (System.currentTimeMillis() % 1000000));
                record.setProductID(productId);
                record.setMemberID(memberId);
                record.setQuantity(quantity);
                record.setSaleDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                salesDao.addSalesRecord(record);

                // 3. 更新库存
                inv.setQuantity(inv.getQuantity() - quantity);
                inventoryDao.updateInventory(inv);

                // 4. 更新会员积分（非会员memberId=0）
                if (memberId > 0) {
                    MemberDao memberDao = new MemberDaoImpl();
                    Member member = memberDao.findById(memberId);
                    // 假设每消费10元积1分
                    double total = inv.getProductID(); // 需要ProductService获取价格
                    int points = (int) (total / 10);
                    member.setPoints(member.getPoints() + points);
                    memberDao.updateMember(member);
                }

                response.sendRedirect("sales?action=list");

            } else if ("return".equals(action)) {
                // 处理退货
                int salesId = Integer.parseInt(request.getParameter("salesId"));
                SalesRecord record = salesDao.findById(salesId);

                if (record != null) {
                    // 1. 恢复库存
                    Inventory inv = inventoryDao.findByProductId(record.getProductID());
                    inv.setQuantity(inv.getQuantity() + record.getQuantity());
                    inventoryDao.updateInventory(inv);

                    // 2. 删除销售记录
                    salesDao.deleteSalesRecord(salesId);
                }

                response.sendRedirect("sales?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException("销售操作失败", e);
        }
    }
}