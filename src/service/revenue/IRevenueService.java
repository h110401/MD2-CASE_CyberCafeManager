package service.revenue;

import model.Revenue;
import service.IGeneric;

import java.time.LocalDate;

public interface IRevenueService extends IGeneric<Revenue> {
    int getTotalRevenue();

    Revenue findByLocalDate(LocalDate localDate);
}
