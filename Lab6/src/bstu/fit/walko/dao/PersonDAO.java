package bstu.fit.walko.dao;

import bstu.fit.walko.entity.Latter;
import bstu.fit.walko.entity.Person;

import java.util.List;

public interface PersonDAO {
    //create
    void add(Person person);
    //find
    List<Person> findAll();
    Person getById(int id);
    //update
    void update(Person person);
    //delete
    void remove(Person person);
}
