package service.service;

import config.Config;

import static data.Path.*;

import model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceServiceIMPL implements IServiceService {

    //    private static final List<Service> serviceList = config.read(PATH_SERVICE_LIST);
    Config<List<Service>> config = new Config<>();
    static List<Service> serviceList = new ArrayList<>();

    static {
        serviceList.add(new Service(1, "Sting", 10000));
        serviceList.add(new Service(2, "MyTom", 15000));
        serviceList.add(new Service(3, "BimBim", 5000));
        serviceList.add(new Service(4, "Girl", 500000));
    }

    @Override
    public List<Service> findAll() {
        return serviceList;
    }

    @Override
    public void save(Service e) {
        serviceList.add(e);
        config.write(serviceList, PATH_SERVICE_LIST);
    }

    @Override
    public void remove(int id) {
        serviceList.remove(id - 1);
        config.write(serviceList, PATH_SERVICE_LIST);
    }

    @Override
    public Service findById(int id) {
        return serviceList.get(id - 1);
    }
}
