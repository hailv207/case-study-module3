package DAO;

import model.Shipper;

import java.sql.Connection;
import java.util.List;

public class ShipperSDAO implements SDAO<Shipper> {


    @Override
    public List<Shipper> getAllItems() {
        return null;
    }

    @Override
    public Shipper getItem(String id) {
        return null;
    }

    @Override
    public boolean insertItem(Shipper item) {
        return false;
    }

    @Override
    public boolean updateItem(Shipper item) {
        return false;
    }


    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
