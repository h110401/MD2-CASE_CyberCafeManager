package service.revenue;

import config.Config;

import static data.Path.*;

import model.Revenue;

import java.time.LocalDate;
import java.util.List;

public class RevenueServiceIMPL implements IRevenueService {

    static Config<List<Revenue>> config = new Config<>();
    static Config<Long> longConfig = new Config<>();
    static List<Revenue> revenueList;

    static long totalRevenue;

    static {
//        revenueList.add(new Revenue(1, 100000, LocalDate.of(2001, 1, 1)));
//        revenueList.add(new Revenue(2, 200000, LocalDate.of(2001, 1, 2)));
        revenueList = config.read(PATH_REVENUE_LIST);
        totalRevenue = longConfig.read(PATH_TOTAL_REVENUE);
    }

    @Override
    public List<Revenue> findAll() {
        return revenueList;
    }

    @Override
    public void save(Revenue e) {
        Revenue current = findByLocalDate(e.getDate());
        if (current == null) {
            revenueList.add(e);
        } else {
            current.setRevenueOfDay(current.getRevenueOfDay() + e.getRevenueOfDay());
        }
        totalRevenue += e.getRevenueOfDay();
        saveData();
        longConfig.write(totalRevenue, PATH_TOTAL_REVENUE);
    }

    @Override
    public Revenue findByLocalDate(LocalDate localDate) {
        return revenueList.stream().filter(revenue -> revenue.getDate().equals(localDate)).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        revenueList.remove(findById(id));
    }

    @Override
    public Revenue findById(int id) {
        return revenueList.stream().filter(revenue -> revenue.getId() == id).findAny().orElse(null);
    }

    @Override
    public void saveData() {
        config.write(revenueList, PATH_REVENUE_LIST);
    }

    @Override
    public long getTotalRevenue() {
        return totalRevenue;
    }
}
