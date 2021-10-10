package repository;

import model.IVehicle;

public interface IRepository {
    void Add(IVehicle v);

    void Delete(int id);

    IVehicle[] GetAll();
}
