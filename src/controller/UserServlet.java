package controller;

import DAO.UserSDAO;
import model.User;
import util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static UserSDAO userSDAO;

    static {
        userSDAO = new UserSDAO();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editUser(request, response);
                break;
            case "create":
                createUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "resetPassword":
                resetPassword(request, response);
                break;
            default:
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
            case "edit":
                showEditUser(request, response);
                break;
            case "create":
                showCreateUser(request, response);
                break;
            case "delete":
                showDeleteUser(request, response);
                break;
            case "resetPassword":
                showResetPassword(request, response);
                break;
            default:
                showAllUsers(request, response);
                break;
        }
    }

    public void showAllUsers(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        List<User> users = userSDAO.getAllItems();
        try {
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("isLogined",isLogined);
            request.setAttribute("userList", users);
            request.getRequestDispatcher("WEB-INF/admin/user/users-management.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditUser(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String id = request.getParameter("id");
        User selectedUser = userSDAO.getItem(id);
        if (selectedUser == null) {
            return;
        }
        try {
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("isLogined",isLogined);
            request.setAttribute("selectedUser", selectedUser);
            request.getRequestDispatcher("WEB-INF/admin/user/editUser.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        try {
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("isLogined",isLogined);
            request.getRequestDispatcher("WEB-INF/admin/user/createUser.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteUser(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String id = request.getParameter("id");
        User selectedUser = userSDAO.getItem(id);
        if (selectedUser == null) {
            return;
        }
        try {
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("isLogined",isLogined);
            request.setAttribute("selectedUser", selectedUser);
            request.getRequestDispatcher("WEB-INF/admin/user/deleteUser.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone").trim()
                .replace(".", "")
                .replace("-", "");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        User user = new User(id, name, phone, email, address);
        userSDAO.updateItem(user);
        try {
            response.sendRedirect("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id").replace(" ","").toUpperCase();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");
        if (!password.equals(confirmedPassword)) {
            return;
        }
        User user = new User(id, name, phone, email, address, password);
        userSDAO.insertItem(user);
        try {
            response.sendRedirect("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String message = "";
        if (userSDAO.deleteItem(id)) {
            message = "Xoá người dùng thành công.";
        } else {
            message = "Xoá người dùng thất bại.";
        }
        try {
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/admin/user/deleteUser.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResetPassword(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String id = request.getParameter("id");
        User selectedUser = userSDAO.getItem(id);
        if (selectedUser == null) {
            return;
        }
        try {
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("isLogined",isLogined);
            request.setAttribute("selectedUser", selectedUser);
            request.getRequestDispatcher("WEB-INF/admin/user/resetUserPassword.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetPassword(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        userSDAO.resetPassword(id);
        try {
            request.getRequestDispatcher("WEB-INF/admin/user/resetUserPassword.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
