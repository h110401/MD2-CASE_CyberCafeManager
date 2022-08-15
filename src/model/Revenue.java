package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Revenue implements Serializable {

    private int id;
    private int revenueOfDay;
    private LocalDate date;

    public Revenue() {
    }

    public Revenue(int id, int revenueOfDay, LocalDate date) {
        this.id = id;
        this.revenueOfDay = revenueOfDay;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRevenueOfDay() {
        return revenueOfDay;
    }

    public void setRevenueOfDay(int revenueOfDay) {
        this.revenueOfDay = revenueOfDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "revenueOfDay=" + revenueOfDay +
                ", date=" + date +
                '}';
    }
}
