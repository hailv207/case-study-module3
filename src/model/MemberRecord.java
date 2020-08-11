package model;

import java.time.LocalDate;

public class MemberRecord {
    private int id;
    private int customerID;
    private int orderID;
    private LocalDate date;
    private int pointsChange;
    private int pointsGained;
    private int pointsBalance;
    private String comment;

    public MemberRecord(int id, int customerID, int orderID, LocalDate date, int pointsChange, int pointsGained, int pointsBalance, String comment) {
        this.id = id;
        this.customerID = customerID;
        this.orderID = orderID;
        this.date = date;
        this.pointsChange = pointsChange;
        this.pointsGained = pointsGained;
        this.pointsBalance = pointsBalance;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPointsChange() {
        return pointsChange;
    }

    public void setPointsChange(int pointsChange) {
        this.pointsChange = pointsChange;
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }

    public int getPointsBalance() {
        return pointsBalance;
    }

    public void setPointsBalance(int pointsBalance) {
        this.pointsBalance = pointsBalance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
