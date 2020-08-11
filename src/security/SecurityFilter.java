package security;

import model.User;
import util.DataUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/uhvhgfjhgxj*")
public class SecurityFilter implements Filter {
    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Thông tin người dùng đã được lưu trong Session
        // (Sau khi đăng nhập thành công).
        User loginedUser = DataUtil.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getPhone();

            // Các vai trò (Role).
            List<String> roles = loginedUser.getRoles();

            // Gói request cũ bởi một Request mới với các thông tin userName và Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        // Các trang bắt buộc phải đăng nhập.
        if (SecurityUtil.isSecurityPage(request)) {

            // Nếu người dùng chưa đăng nhập,
            // Redirect (chuyển hướng) tới trang đăng nhập.
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Lưu trữ trang hiện tại để redirect đến sau khi đăng nhập thành công.
                int redirectId = DataUtil.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Kiểm tra người dùng có vai trò hợp lệ hay không?
            boolean hasPermission = SecurityUtil.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/view/accessDenied.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
