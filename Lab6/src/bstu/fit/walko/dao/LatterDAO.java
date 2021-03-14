package bstu.fit.walko.dao;

import bstu.fit.walko.entity.Latter;

import java.util.List;

public interface LatterDAO {
    //create
    void add(Latter latter);
    //find
    List<Latter> findAll();
    Latter getById(int id);
    //update
    void update(Latter latter);
    //delete
    void remove(Latter latter);
}
