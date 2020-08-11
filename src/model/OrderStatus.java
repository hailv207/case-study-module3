package model;

import java.time.LocalDateTime;

public class OrderStatus {
    private int id;
    private int orderID;
    private LocalDateTime date;
    private String status;
    private String comment;
    private int statusValue;

    public OrderStatus(int orderID, LocalDateTime date, String status, String comment, int statusValue) {
        this.orderID = orderID;
        this.date = date;
        this.status = status;
        this.comment = comment;
        this.statusValue = statusValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(int statusValue) {
        this.statusValue = statusValue;
    }

    public OrderStatus(int id, int orderID, LocalDateTime date, String status, String comment, int statusValue) {
        this.id = id;
        this.orderID = orderID;
        this.date = date;
        this.status = status;
        this.comment = comment;
        this.statusValue = statusValue;
    }
}
