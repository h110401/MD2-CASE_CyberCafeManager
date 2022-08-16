package controller;

import model.Revenue;
import service.revenue.IRevenueService;
import service.revenue.RevenueServiceIMPL;

import java.time.LocalDate;
import java.util.List;

public class RevenueController {

    IRevenueService revenueService = new RevenueServiceIMPL();

    public List<Revenue> getRevenueList() {
        return revenueService.findAll();
    }

    public void saveRevenue(Revenue revenue) {
        revenueService.save(revenue);
    }

    public long getTotalRevenue() {
        return revenueService.getTotalRevenue();
    }

    public Revenue findByLocalDate(LocalDate date) {
        return revenueService.findByLocalDate(date);
    }

}
