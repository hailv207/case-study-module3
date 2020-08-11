package DAO;

import model.User;
import util.ConnectionUtil;

import javax.swing.border.AbstractBorder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserSDAO implements SDAO<User> {
    private String jdbcURL = "jdbc:mysql://localhost:3306/seafoodDB?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mysql123456";

    private static final String INSERT_USER = "INSERT INTO users" + "  (id ,name, phone, email, address, password) VALUES " +
            " (?, ?, ?, ?, ?, ?);";


    private static final String SELECT_ALL_USER = "select * from users";
    private static final String DELETE_USER = "delete from users where id = ?;";
    private static final String UPDATE_USER = "update users set name = ?, phone = ?, email = ?, address = ? where id = ?";
    private static final String UPDATE_USER_PASSWORD = "update users set password=? where id=?";


    @Override
    public List<User> getAllItems() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_ALL_USER)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User getItem(String id) {
        User user = null;
        for (User u : getAllItems()) {
            if (u.getId().equals(id)) {
                user = u;
            }
        }
        return user;
    }

    @Override
    public boolean insertItem(User item) {
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(INSERT_USER)) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getPhone());
            ps.setString(4, item.getEmail());
            ps.setString(5, item.getAddress());
            ps.setString(6, item.getPassword());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(User item) {

        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(UPDATE_USER)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getPhone());
            ps.setString(3, item.getEmail());
            ps.setString(4, item.getAddress());
            ps.setString(5, item.getId());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteItem(String id) {

        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(DELETE_USER)) {
            ps.setString(1, id);
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String id, String oldPassword, String newPassword) {
        if (!getUserAuthorized(id, oldPassword)) {
            return false;
        }
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(UPDATE_USER_PASSWORD)) {
            ps.setString(1, newPassword);
            ps.setString(2, id);
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetPassword(String id) {
        String sql = "update users password = '123' where id=?";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getUserAuthorized(String id, String password) {
        User c = getItem(id);
        if (c.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static User findUser(Connection conn, //
                                String userName, String password) throws SQLException {

        String sql = "Select * from users where id = ? and password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String id) throws SQLException {

        String sql = "Select a.id, a.name, a.phone,a.email,a.address,a.password from users a "//
                + " where a.id = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));
            return user;
        }
        return null;
    }

    public boolean checkExistPhone(String phone) {
        String sql = "select phone from users";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkFormatPhone(String phone) {
        Pattern pattern = Pattern.compile("[0]d{9}");
        return pattern.matcher(phone).matches();
    }

    public boolean checkExistEmail(String email) {
        String sql = "select email from users";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkFormatEmail(String email) {
        Pattern pattern = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
        return pattern.matcher(email).matches();
    }

}
