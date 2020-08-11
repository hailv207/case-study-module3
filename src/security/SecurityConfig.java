package security;

import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SecurityConfig {
    public static Set<String> getAllRoles() {
        Set<String> set = new HashSet<>();
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery("select id from roles");
            while (rs.next()){
                set.add(rs.getString("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return set;
    }
    public static List<String> getUrlPattern(String role){
        String sql= "select urlPattern from scuredPages where roleID=?";
        List<String> urlPatternList = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setString(1,role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                urlPatternList.add(rs.getString("urlPattern"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return urlPatternList;
    }

}
