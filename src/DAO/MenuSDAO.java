package DAO;

import model.Menu;

import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuSDAO implements SDAO<Menu> {
    private final String SELECT_ALL = "select * from menuList where 1=1";
    private final String SELECT_BY_TYPE = "select * from menuList where menuType=?";
    private final String SELECT_BY_ID = "select * from menuList where id=?";
    private final String INSERT = "insert into menuList (id, menuType, name, unit, price,imageURL, createDate, status,description) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "update menuList set menuType=?, name=?, unit=?, price=?, imageURL=?, status=?, description=? where id=?";
    private final String DELETE = "delete from menuList where id=?";
    private final String AVAILABLE = " and status='selling'";


    @Override
    public List<Menu> getAllItems() {
        List<Menu> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu(rs.getString("id"),
                        rs.getString("menuType"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getLong("price"),
                        rs.getString("imageURL"),
                        LocalDate.parse (rs.getString("createDate")),
                        rs.getString("status"),
                        rs.getString("description")
                );
                list.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Menu getItem(String id) {
        Menu menu = null;
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_BY_ID)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menu = new Menu(rs.getString("id"),
                        rs.getString("menuType"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getLong("price"),
                        rs.getString("imageURL"),
                       LocalDate.parse( rs.getString("createDate")),
                        rs.getString("status"),
                        rs.getString("description")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public boolean insertItem(Menu item) {
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(INSERT)) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getType());
            ps.setString(3, item.getName());
            ps.setString(4, item.getUnit());
            ps.setDouble(5, item.getPrice());
            ps.setString(6, item.getImageURL());
            ps.setString(7, item.getCreateDate().toString());
            ps.setString(8, item.getStatus());
            ps.setString(9, item.getDescription());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(Menu item) {
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(UPDATE)) {
            ps.setString(1, item.getType());
            ps.setString(2, item.getName());
            ps.setString(3, item.getUnit());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getImageURL());
            ps.setString(6, item.getStatus());
            ps.setString(7, item.getDescription());
            ps.setString(8, item.getId());
            return  ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteItem(String id) {
        try {
            try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(DELETE)) {
                ps.setString(1, id);
                return ps.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Menu> getMenuByType(String menuType) {
        List<Menu> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_BY_TYPE)) {
            ps.setString(1, menuType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Menu(rs.getString("id"),
                        rs.getString("menuType"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price"),
                        rs.getString("imageURL"),
                        LocalDate.parse( rs.getString("createDate")),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getDouble("discount")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Menu> getAllAvailableMenu() {
        List<Menu> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_ALL  + AVAILABLE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Menu(rs.getString("id"),
                        rs.getString("menuType"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price"),
                        rs.getString("imageURL"),
                        LocalDate.parse( rs.getString("createDate")),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getDouble("discount")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;

    }
    public  List<Menu> getAvailableMenuByType(String menuType){
        List<Menu> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_BY_TYPE + AVAILABLE)) {
            ps.setString(1, menuType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Menu(rs.getString("id"),
                        rs.getString("menuType"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price"),
                        rs.getString("imageURL"),
                        LocalDate.parse( rs.getString("createDate")),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getDouble("discount")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


}
