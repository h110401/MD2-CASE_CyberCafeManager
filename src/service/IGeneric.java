package service;

import model.Computer;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();

    void save(T e);

    void remove(int id);

    T findById(int id);

    void saveData();
}
