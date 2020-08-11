package controller;

import DAO.OrderIDAO;
import DAO.OrderStatusIDAO;
import com.sun.tools.corba.se.idl.constExpr.Or;
import model.Order;
import model.OrderStatus;
import model.User;
import util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {
    private static OrderIDAO orderIDAO;
    private static OrderStatusIDAO orderStatusIDAO;
    private DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        orderIDAO = new OrderIDAO();
        orderStatusIDAO = new OrderStatusIDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createOrder(request, response);
                break;
            case "edit":
                updateOrder(request, response);
                break;
            default:
                showAllOrder(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateOrder(request, response);
                break;
            case "edit":
                showUpdateOrder(request, response);
                break;
            default:
                showAllOrder(request, response);
                break;
        }
    }

    public void createOrder(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String customerID = request.getParameter("customerID");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"), datetimeFormatter);
        double total = Double.parseDouble(request.getParameter("total"));
        String type = request.getParameter("type");
        String shipperID = request.getParameter("shipperID");
        Order order = new Order(id, customerID, date, total, type, shipperID);
        orderIDAO.insertItem(order);
        try {
            request.getRequestDispatcher("WEB-INF/admin/orders/orders-management.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(HttpServletRequest request, HttpServletResponse response) {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        LocalDateTime dateTime = LocalDateTime.now();
        String newStatus = request.getParameter("newStatus");
        int statusValue = OrderStatusIDAO.getStatusValue(newStatus);
        String comment = request.getParameter("comment");
        OrderStatus orderStatus = new OrderStatus(orderID, dateTime, newStatus, comment, statusValue);
        orderStatusIDAO.addOrderStatus(orderStatus);
        try {
            response.sendRedirect("/orders?action=edit&id=" + orderID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllOrder(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        List<Order> list = orderIDAO.getAllItems();
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("orderList", list);
            request.getRequestDispatcher("WEB-INF/admin/order/orders-management.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUpdateOrder(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        int id = Integer.parseInt(request.getParameter("id"));
        Order selectedOrder = orderIDAO.getItem(id);
        List<String> availableStatus = new ArrayList<>();
        int currentStatusValue = orderStatusIDAO.getStatusValueOfOrder(id);
        for (int k : OrderStatusIDAO.getOrderStatus().keySet()) {
            if (k >= currentStatusValue) {
                availableStatus.add(OrderStatusIDAO.getOrderStatus().get(k));
            }
        }
        List<OrderStatus> orderStatusList = orderStatusIDAO.getStatusHistoryOfOrder(id);
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("availableStatus", availableStatus);
            request.setAttribute("selectedOrder", selectedOrder);
            request.setAttribute("orderStatusList", orderStatusList);
            request.getRequestDispatcher("WEB-INF/admin/order/updateOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCreateOrder(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.getRequestDispatcher("WEB-INF/admin/order/createOrder.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
