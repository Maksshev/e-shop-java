package dao;

import java.util.List;

public interface Dao<T> {
    T get(int id);
    List<T> getAll();
    void remove(int id);
    void add(T item);
}
