package service.computer;

import config.Config;

import static data.Path.*;

import model.Computer;

import java.util.ArrayList;
import java.util.List;

public class ComputerServiceIMPL implements IComputerService {

    //    static final List<Computer> computerList = config.read(PATH_COMPUTER_LIST);
    Config<List<Computer>> config = new Config<>();
    static List<Computer> computerList = new ArrayList<>();

    static int pricePerHour = 10000;

    static {
        computerList.add(new Computer(1, "computer 1"));
        computerList.add(new Computer(2, "computer 2"));
        computerList.get(0).turnOn();
    }


    @Override
    public List<Computer> findAll() {
        return computerList;
    }

    @Override
    public void save(Computer c) {
        computerList.add(c);
        config.write(computerList, PATH_COMPUTER_LIST);
    }

    @Override
    public void remove(int id) {
        computerList.remove(findById(id));
        config.write(computerList, PATH_COMPUTER_LIST);
    }

    @Override
    public Computer findById(int id) {
        return computerList.stream().filter(computer -> computer.getId() == id).findAny().orElse(null);
    }

    @Override
    public int countMoney(Computer computer) {
        double totalNumber;
        long elapsed = System.currentTimeMillis() - computer.getStartTime();

        double playTimeMoney = pricePerHour * (elapsed / (60.0 * 60 * 1000));

        totalNumber = computer.getServiceMoney() + (playTimeMoney < 5000 ? 5000 : playTimeMoney);

        return (int) totalNumber;
    }

    @Override
    public void editPricePerHour(int price) {
        pricePerHour = price;
    }

}
