package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private String customerID;
    private LocalDateTime date;
    private double total;
    private String type;
    private String shipperID;

    public Order(int id, String customerID, LocalDateTime date, double total, String type, String shipperID) {
        this.id = id;
        this.customerID = customerID;
        this.date = date;
        this.total = total;
        this.type = type;
        this.shipperID = shipperID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShipperID() {
        return shipperID;
    }

    public void setShipperID(String shipperID) {
        this.shipperID = shipperID;
    }
    public String getFormattedTotal(){
        String number = null;
        DecimalFormat formatter = new DecimalFormat("#,###");
        number = formatter.format(this.total);
        return number;
    }
}
