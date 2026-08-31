package servlet;

import dao.ProductDao;
import dao.ProductDaoImpl;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 权限检查
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        ProductDao productDao = new ProductDaoImpl();

        try {
            if ("list".equals(action)) {
                List<Product> products = productDao.findAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("product_list.jsp").forward(request, response);
            } else if ("add".equals(action)) {
                request.getRequestDispatcher("product_add.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productDao.findById(id);
                request.setAttribute("product", product);
                request.getRequestDispatcher("product_edit.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("商品操作失败", e);
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
        ProductDao productDao = new ProductDaoImpl();
//        对货品信息表的操作包括增、删、改业务
        try {
            if ("add".equals(action)) {
                Product product = new Product();
                product.setProductName(request.getParameter("name"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                product.setCategory(request.getParameter("category"));
                productDao.addProduct(product);
                response.sendRedirect("product?action=list");
            } else if ("update".equals(action)) {
                Product product = new Product();
                product.setProductID(Integer.parseInt(request.getParameter("id")));
                product.setProductName(request.getParameter("name"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                product.setCategory(request.getParameter("category"));
                productDao.updateProduct(product);
                response.sendRedirect("product?action=list");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                productDao.deleteProduct(id);
                response.sendRedirect("product?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException("商品操作失败", e);
        }
    }
}