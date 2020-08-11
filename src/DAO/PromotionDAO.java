package DAO;

import model.Promotion;
import util.ConnectionUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO implements IDAO<Promotion> {
    private final String SELECT_ALL = "select * from promotion";
    private final String SELECT_AVAILABLE = "select * from promotion where startDate<=? and endDate>=?";
    private final String SELECT_BY_ID = "select * from promotion where id=?";
    private final String INSERT = "insert into menuList (id, name, startDate, endDate, menuTypeID, discountPercent) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "update menuList name=?, startDate=?, endDate=?, menuTypeID=?, discountPercent=? where id=?";
    private final String DELETE = "delete from promotion where id=?";

    @Override
    public List<Promotion> getAllItems() {
        List<Promotion> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Promotion(
                        rs.getInt("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("startDate")),
                        LocalDate.parse(rs.getString("endDate")),
                        rs.getString("menuTypeID"),
                        rs.getDouble("discountPercent")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Promotion> getAllAvailableItems(LocalDate date) {
        List<Promotion> list = new ArrayList<>();
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(SELECT_AVAILABLE)){
            ps.setString(1,date.toString());
            ps.setString(2,date.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Promotion(
                        rs.getInt("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("startDate")),
                        LocalDate.parse(rs.getString("endDate")),
                        rs.getString("menuTypeID"),
                        rs.getDouble("discountPercent")
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
    public Promotion getItem(int id) {
        return null;
    }

    @Override
    public boolean insertItem(Promotion item) {
        return false;
    }

    @Override
    public boolean updateItem(Promotion item) {
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        return false;
    }
}
