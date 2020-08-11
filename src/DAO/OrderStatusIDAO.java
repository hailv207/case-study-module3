package DAO;

import model.OrderStatus;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStatusIDAO {
    private static Map<Integer, String> orderStatus = new HashMap<>();
    private DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        orderStatus.put(1, "placed");
        orderStatus.put(2, "packed");
        orderStatus.put(3, "delivering");
        orderStatus.put(4, "done");
        orderStatus.put(5, "cancelled");
    }

    public List<OrderStatus> getStatusHistoryOfOrder(int orderID) {
        List<OrderStatus> list = new ArrayList<>();
        String sql = "select * from orderStatus where orderID=? order by id desc";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderStatus(
                        rs.getInt("id"),
                        rs.getInt("orderID"),
                        LocalDateTime.parse(rs.getString("date"),datetimeFormatter),
                        rs.getString("status"),
                        rs.getString("comment"),
                        rs.getInt("statusValue")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addOrderStatus(OrderStatus orderStatus) {
        String sql = "insert into orderStatus(orderID,date,status,comment,statusValue) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, orderStatus.getOrderID());
            ps.setString(2, orderStatus.getDate().toString());
            ps.setString(3, orderStatus.getStatus());
            ps.setString(4, orderStatus.getComment());
            ps.setInt(5, orderStatus.getStatusValue());
            return ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getStatusOfOrder(int id) {
        String sql = "select * from orderStatus where orderID =?";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int max = -1;
            while (rs.next()) {
                if (max < rs.getInt("statusValue")) {
                    max = rs.getInt("statusValue");
                }
            }
            if (max > -1) {
                return orderStatus.get(max);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getStatusValueOfOrder(int id){
        String sql = "select * from orderStatus where orderID =?";
        int max = -1;
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (max < rs.getInt("statusValue")) {
                    max = rs.getInt("statusValue");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return max;
    }

    public static int getStatusValue(String status) {
        for (int k: orderStatus.keySet()){
            if (orderStatus.get(k).equals(status)){
                return k;
            }
        }
        return -1;
    }

    public static Map<Integer, String> getOrderStatus() {
        return orderStatus;
    }
}

