package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    List<T> getAllItems();
    T getItem(int id);
    boolean insertItem(T item);
    boolean updateItem(T item);
    boolean deleteItem(int id);
}
