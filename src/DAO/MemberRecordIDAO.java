package DAO;

import model.MemberRecord;

import java.sql.Connection;
import java.util.List;

public class MemberRecordIDAO implements IDAO<MemberRecord> {
    private String jdbcURL = "jdbc:mysql://localhost:3306/seafoodDB?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mysql123456";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO memberHistory" + "  (customerID, date, orderID, pointsChange, pointsGained, pointsBalance, comment) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "select * from memberHistory where customerID = ?";
    private static final String SELECT_CUSTOMER_USERS = "select * from memberHistory";
    private static final String DELETE_CUSTOMER_SQL = "delete from memberHistory where id = ?;";


    @Override
    public List<MemberRecord> getAllItems() {
        return null;
    }

    @Override
    public MemberRecord getItem(int id) {
        return null;
    }



    @Override
    public boolean insertItem(MemberRecord item) {
        return false;
    }

    @Override
    public boolean updateItem(MemberRecord item) {
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        return false;
    }

    public List<MemberRecord> getAllRecordsOfCustomer(int customerID){

        return null;
    }
}
