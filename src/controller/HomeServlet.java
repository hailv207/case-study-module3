package controller;

import DAO.MenuSDAO;
import DAO.PromotionDAO;
import model.Menu;
import model.Promotion;
import model.User;
import util.DataUtil;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/", "/index"})
public class HomeServlet extends HttpServlet {
    private MenuSDAO menuSDAO;
    private PromotionDAO pIDAO;

    @Override
    public void init() throws ServletException {
        menuSDAO = new MenuSDAO();
        pIDAO = new PromotionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        showMenu(request, response);
    }

    public void showMenu(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        String catagory = request.getParameter("catagory");
        if (catagory == null) {
            catagory = "TATCA";
        }
        Date today = new Date(2020, 8, 5);
        List<Menu> menuList = new ArrayList<>();
        if (catagory.equals("TATCA")) {
            menuList = menuSDAO.getAllAvailableMenu();
        } else {
            menuList = menuSDAO.getAvailableMenuByType(catagory);
        }
        setDiscount(menuList);
        try {
            request.setAttribute("isLogined",isLogined);
            request.setAttribute("loginedUser",loginedUser);
            request.setAttribute("menuList", menuList);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/order/home.jsp");
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDiscount(List<Menu> list) {
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        List<Promotion> promoList = pIDAO.getAllAvailableItems(today);
        for (Menu m : list) {
            for (Promotion p : promoList) {
                if (m.getType().equals(p.getMenuTypeID())) {
                    m.setDiscount(p.getDiscountPercent());
                    break;
                }
            }
        }
    }
}
