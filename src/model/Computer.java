package model;

import java.io.Serializable;

public class Computer implements Serializable {

    private int id;
    private String name;
    private boolean status;
    private long startTime;
    private int serviceMoney;

    public Computer(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(int serviceMoney) {
        this.serviceMoney = serviceMoney;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public void turnOn() {
        startTime = System.currentTimeMillis();
        status = true;
    }

    public void turnOff() {
        startTime = 0;
        serviceMoney = 0;
        status = false;
    }

//    public int countMoney() {
//        double totalNumber;
//        long elapsed = System.currentTimeMillis() - this.getStartTime();
//
//        double playTimeMoney = Computer.pricePerHour * (elapsed / (60.0 * 60 * 1000));
//
//        totalNumber = getServiceMoney() + (playTimeMoney < 5000 ? 5000 : playTimeMoney);
//
//        return (int) totalNumber;
//    }

}

