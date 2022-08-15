package controller;

import model.Computer;
import service.computer.ComputerServiceIMPL;
import service.computer.IComputerService;

import java.util.List;

public class ComputerController {

    IComputerService computerService = new ComputerServiceIMPL();

    public List<Computer> getComputerList() {
        return computerService.findAll();
    }

    public void saveComputer(Computer computer) {
        computerService.save(computer);
    }

    public void deleteComputer(int id) {
        computerService.remove(id);
    }

    public Computer findComputerByID(int id) {
        return computerService.findById(id);
    }

    public int countMoney(Computer computer) {
        return computerService.countMoney(computer);
    }

    public void editPlayTimePrice(int newPrice) {
        computerService.editPricePerHour(newPrice);
    }
}
