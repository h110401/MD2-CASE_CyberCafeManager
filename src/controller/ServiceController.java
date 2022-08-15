package controller;

import model.Service;
import service.service.IServiceService;
import service.service.ServiceServiceIMPL;

import java.util.List;

public class ServiceController {

    IServiceService serviceService = new ServiceServiceIMPL();

    public List<Service> getServiceList() {
        return serviceService.findAll();
    }

    public void createService(Service service) {
        serviceService.save(service);
    }

}
