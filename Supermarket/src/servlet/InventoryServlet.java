package servlet;

import dao.InventoryDao;
import dao.InventoryDaoImpl;
import entity.Inventory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 权限检查
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        InventoryDao inventoryDao = new InventoryDaoImpl();

        try {
            if ("list".equals(action)) {
                List<Inventory> inventoryList = inventoryDao.findAll();
                request.setAttribute("inventoryList", inventoryList);
                request.getRequestDispatcher("inventory_list.jsp").forward(request, response);

            } else if ("warning".equals(action)) {
                List<Inventory> lowStock = inventoryDao.findLowStock(10);
                request.setAttribute("lowStockItems", lowStock);
                request.getRequestDispatcher("inventory_warning.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("库存查询失败", e);
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
        InventoryDao inventoryDao = new InventoryDaoImpl();

        try {
            if ("update".equals(action)) {
                Inventory inventory = new Inventory();
                inventory.setProductID(Integer.parseInt(request.getParameter("productId")));
                inventory.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                inventoryDao.updateInventory(inventory);
                response.sendRedirect("inventory?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException("库存更新失败", e);
        }
    }
}