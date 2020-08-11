package DAO;

import model.MenuType;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuTypeSDAO implements SDAO<MenuType>{
    @Override
    public List<MenuType> getAllItems() {
        List<MenuType> list = new ArrayList<>();
        String sql = "select * from menuType";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()){
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                list.add(new MenuType(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("imageURL")
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
    public MenuType getItem(String id) {
        MenuType menuType = null;
        String sql = "select * from menuType where id=?";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                menuType = new MenuType(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("imageURL")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menuType;
    }

    @Override
    public boolean insertItem(MenuType item) {
        String sql = "insert into menuType() values (?, ?, ?)";
        try(PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setString(1,item.getId());
            ps.setString(2,item.getName());
            ps.setString(3,item.getImageURL());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(MenuType item) {
        String sql = "update menuType set id=?, name=?, imageURL=? where id=?";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setString(1,item.getId());
            ps.setString(2,item.getName());
            ps.setString(3,item.getImageURL());
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
        String sql = "delete from menuType where id=?";
        try(PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setString(1,id);
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
