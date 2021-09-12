package fit.bstu.walko.Lab13.repository;

import fit.bstu.walko.Lab13.exception.RepositoryError;

import java.sql.Connection;
import java.util.List;

public interface Repository<T> {
    //create
    void add(T entity) throws RepositoryError;
    //find
    List<T> findAll() throws RepositoryError;
    T getById(int id);
    //update
    void update(T entity);
    //delete
    void remove(T entity) throws RepositoryError;
}
