package controller;

import DAO.UserSDAO;
import model.User;
import util.DataUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserSDAO userSDAO;

    public LoginServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        userSDAO = new UserSDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/user/login.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        if (userSDAO.getUserAuthorized(username, password)) {
            user = userSDAO.getItem(username);
        }
        if (user == null) {
            String errorMessage = "Invalid username or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp");

            dispatcher.forward(request, response);
            return;
        }

        DataUtil.storeLoginedUser(request.getSession(), user);
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = DataUtil.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Mặc định sau khi đăng nhập thành công
            // chuyển hướng về trang /userInfo
            response.sendRedirect("/home");
        }

    }

}
