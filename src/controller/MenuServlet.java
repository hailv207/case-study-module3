package controller;

import DAO.MenuSDAO;
import DAO.MenuTypeSDAO;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import model.Menu;
import model.MenuType;
import model.User;
import util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {
    private static MenuSDAO menuSDAO;
    private static MenuTypeSDAO menuTypeSDAO;

    static {
        menuSDAO = new MenuSDAO();
        menuTypeSDAO = new MenuTypeSDAO();
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
                createMenu(request, response);
                break;
            case "edit":
                editMenu(request, response);
                break;
            case "delete":
                deleteMenu(request, response);
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
            case "create":
                showCreateMenu(request, response);
                break;
            case "edit":
                showEditMenu(request, response);
                break;
            case "delete":
                showDeleteMenu(request, response);
                break;
            default:
                showAllMenu(request, response);
                break;
        }
    }

    public void showAllMenu(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        List<Menu> list = menuSDAO.getAllItems();
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("menuList", list);
            request.getRequestDispatcher("WEB-INF/admin/menu/menu-management.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditMenu(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String id = request.getParameter("id");
        Menu selectedMenu = menuSDAO.getItem(id);
        List<MenuType> menuTypes = menuTypeSDAO.getAllItems();
        if (selectedMenu == null) {
            return;
        }
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("menuTypeList", menuTypes);
            request.setAttribute("selectedMenu", selectedMenu);
            request.getRequestDispatcher("WEB-INF/admin/menu/editMenu.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateMenu(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        List<MenuType> list = menuTypeSDAO.getAllItems();
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("menuTypeList", list);
            request.getRequestDispatcher("WEB-INF/admin/menu/createMenu.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteMenu(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String id = request.getParameter("id");
        Menu selectedMenu = menuSDAO.getItem(id);
        List<MenuType> menuTypes = menuTypeSDAO.getAllItems();
        try {
            request.setAttribute("loginedUser", loginedUser);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("menuTypeList", menuTypes);
            request.setAttribute("selectedMenu", selectedMenu);
            request.getRequestDispatcher("WEB-INF/admin/menu/deleteMenu.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void editMenu(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("menuType"));
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("unit"));
        System.out.println(request.getParameter("price"));
        System.out.println(request.getParameter("imageURL"));
        System.out.println(request.getParameter("createDate"));
        System.out.println(request.getParameter("status"));
        System.out.println(request.getParameter("description"));
        System.out.println(LocalDate.parse(request.getParameter("createDate")));
        Menu menu = new Menu(
                request.getParameter("id"),
                request.getParameter("menuType"),
                request.getParameter("name"),
                request.getParameter("unit"),
                Double.parseDouble(request.getParameter("price")),
                request.getParameter("imageURL"),
                LocalDate.parse(request.getParameter("createDate")),
                request.getParameter("status"),
                request.getParameter("description")
        );
        menuSDAO.updateItem(menu);
        try {
            response.sendRedirect("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMenu(HttpServletRequest request, HttpServletResponse response) {
        Menu menu = new Menu(
                request.getParameter("id"),
                request.getParameter("menuType"),
                request.getParameter("name"),
                request.getParameter("unit"),
                Double.parseDouble(request.getParameter("price")),
                request.getParameter("imageURL"),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                request.getParameter("status"),
                request.getParameter("description")
        );
        menuSDAO.insertItem(menu);
        try {
            response.sendRedirect("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteMenu(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        menuSDAO.deleteItem(id);
        try {
            response.sendRedirect("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

