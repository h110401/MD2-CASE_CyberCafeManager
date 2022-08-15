package service.computer;

import model.Computer;
import service.IGeneric;

public interface IComputerService extends IGeneric<Computer> {
    int countMoney(Computer computer);

    void editPricePerHour(int price);

}
