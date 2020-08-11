package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SDAO<T> {
    List<T> getAllItems();
    T getItem(String id);
    boolean insertItem(T item);
    boolean updateItem(T item);
    boolean deleteItem(String id);
}
