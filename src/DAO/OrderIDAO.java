package DAO;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import model.Order;
import util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderIDAO implements IDAO<Order> {
private DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<Order> getAllItems() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getString("customerID"),
                        LocalDateTime.parse(rs.getString("date"),datetimeFormatter),
                        rs.getDouble("total"),
                        rs.getString("type"),
                        rs.getString("shipperID")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getOrderByStatus(String orderStatus) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("status").equals(orderStatus)) {
                    list.add(new Order(
                            rs.getInt("id"),
                            rs.getString("customerID"),
                            LocalDateTime.parse(rs.getString("date"),datetimeFormatter),
                            rs.getDouble("total"),
                            rs.getString("type"),
                            rs.getString("shipperID")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public Order getItem(int id) {
        String sql = "select * from orders where id=?";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Order(
                        rs.getInt("id"),
                        rs.getString("customerID"),
                        LocalDateTime.parse(rs.getString("date"),datetimeFormatter),
                        rs.getDouble("total"),
                        rs.getString("type"),
                        rs.getString("shipperID")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean insertItem(Order item) {
        String sql = "insert into orders() values(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setInt(1,item.getId());
            ps.setString(2,item.getCustomerID());
            ps.setString(3,item.getDate().toString());
            ps.setDouble(4,item.getTotal());
            ps.setString(5,item.getType());
            ps.setString(6,item.getShipperID());

           return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(Order item) {
        String sql = "update orders set total=?, type=?, shipperID=? where id=?";
        try(PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
            ps.setDouble(1, item.getTotal());
            ps.setString(2,item.getType());
            ps.setString(3,item.getShipperID());
            ps.setInt(4,item.getId());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        String sql = "delete from orders where id=?";
        try(PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)){
           ps.setInt(1,id);
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
