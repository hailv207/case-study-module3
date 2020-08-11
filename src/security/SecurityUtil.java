package security;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;
import java.util.Set;

public class SecurityUtil {
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtil.getUrlPattern(request);
        Set<String> roles = SecurityConfig.getAllRoles();
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPattern(role);
            if (urlPattern != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtil.getUrlPattern(request);
        Set<String> roles = SecurityConfig.getAllRoles();
        for (String role : roles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPattern(role);
            if (urlPattern != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

}
