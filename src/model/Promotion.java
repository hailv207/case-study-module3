package model;

import java.sql.Date;
import java.time.LocalDate;

public class Promotion {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String menuTypeID;
    private double discountPercent;

    public Promotion(int id, String name, LocalDate startDate, LocalDate endDate, String menuTypeID, double discountPercent) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.menuTypeID = menuTypeID;
        this.discountPercent = discountPercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMenuTypeID() {
        return menuTypeID;
    }

    public void setMenuTypeID(String menuTypeID) {
        this.menuTypeID = menuTypeID;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
