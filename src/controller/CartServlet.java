package controller;

import DAO.MenuSDAO;
import DAO.OrderIDAO;
import DAO.PromotionDAO;
import com.sun.tools.corba.se.idl.constExpr.Or;
import model.Cart;
import model.Menu;
import model.Promotion;
import model.User;
import util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.NumberFormatter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private static MenuSDAO menuSDAO;
    private static OrderIDAO orderIDAO;
    private static PromotionDAO promotionDAO;
    static {
        menuSDAO = new MenuSDAO();
        orderIDAO = new OrderIDAO();
        promotionDAO = new PromotionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "additem":
                addItem(request, response);
                break;
            case "removeitem":
                removeItem(request, response);
                break;
            case "checkout":
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showCart(request, response);
    }

    public void addItem(HttpServletRequest request, HttpServletResponse response) {
        String menuID = request.getParameter("menuID");
        Cart cart = DataUtil.getCart(request.getSession());
        if (cart == null) {
            cart = new Cart();
        }
        cart.addItem(menuID, 1);
        DataUtil.storeCart(request.getSession(), cart);
        try {
            response.sendRedirect("/home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(HttpServletRequest request, HttpServletResponse response) {
        String menuID = request.getParameter("menuID");
        Cart cart = DataUtil.getCart(request.getSession());
        cart.removeItem(menuID);
        try {
            response.sendRedirect("/cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseQuantity(HttpServletRequest request, HttpServletResponse response) {
        String menuID = request.getParameter("menuID");
        Cart cart = DataUtil.getCart(request.getSession());
        cart.increaseQuantity(menuID);
        try {
            response.sendRedirect("/cart?action=checkout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decreaseQuantity(HttpServletRequest request, HttpServletResponse response) {
        String menuID = request.getParameter("menuID");
        Cart cart = DataUtil.getCart(request.getSession());
        cart.decreaseQuantity(menuID);
        try {
            response.sendRedirect("/cart?action=checkout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCart(HttpServletRequest request, HttpServletResponse response) {
        User loginedUser = DataUtil.getLoginedUser(request.getSession());
        boolean isLogined = loginedUser != null;
        Cart cart = DataUtil.getCart(request.getSession());
        List<Menu> menuList = new ArrayList<>();
        boolean cartNotNull = true;
        if (cart != null) {
            for (String k : cart.getCartList().keySet()) {
                if (menuSDAO.getItem(k) != null) {
                    menuList.add(menuSDAO.getItem(k));
                }
            }
            setDiscount(menuList);
        } else {
            cartNotNull = false;
        }
        double total = getCartTotal(cart, menuList);
        try {
            request.setAttribute("total", total);
            request.setAttribute("cartNotNull", cartNotNull);
            request.setAttribute("isLogined", isLogined);
            request.setAttribute("menuList", menuList);
            request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getCartTotal(Cart cart, List<Menu> list) {
        double total = 0;
        if (cart == null) {
            return 0;
        }
        for (String k : cart.getCartList().keySet()) {
            double discountPrice = 0;
            for (Menu m : list) {
                if (m.getId().equals(k)) {
                    discountPrice = Math.round(m.getDiscountPrice());
                    break;
                }
            }
            int quantity = cart.getCartList().get(k);
            total += Math.round(discountPrice * quantity);
        }

        return Math.round(total);
    }

    public void setDiscount(List<Menu> list) {
        LocalDate today = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        List<Promotion> promoList = promotionDAO.getAllAvailableItems(today);
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
