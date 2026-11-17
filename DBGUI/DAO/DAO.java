package DAO;
import java.util.LinkedList;

// This interface defines the basic CRUD (create, read, update, delete) operations for a Data Access Object (DAO) that handles entities of type T.
public interface DAO<T> {
    boolean insert(T entity);
    boolean update(T entity);
    boolean delete(int id);
    T select(int id);
    LinkedList<T> selectAll();
}
