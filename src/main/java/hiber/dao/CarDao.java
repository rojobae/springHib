package hiber.dao;

import hiber.model.*;

import java.util.List;

public interface CarDao {
    void add(Car car);
    List<Car> listCars();
}