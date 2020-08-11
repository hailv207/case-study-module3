package model;


import java.text.DecimalFormat;
import java.time.LocalDate;


public class Menu {
    private String id;
    private String type;
    private String name;
    private String unit;
    private double price;
    private String imageURL;
    private LocalDate createDate;
    private String status;
    private String description;
    private double discount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Menu(String id, String type, String name, String unit, double price, String imageURL, LocalDate createDate, String status, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.imageURL = imageURL;
        this.createDate = createDate;
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Menu(String id, String type, String name, String unit, double price, String imageURL, LocalDate createDate, String status, String description, double discount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.imageURL = imageURL;
        this.createDate = createDate;
        this.status = status;
        this.description = description;
        this.discount = discount;
    }

    public double getDiscountPrice() {
     double price = this.price - (this.price * this.discount);
     return price;
    }
    public String getFormattedPrice(){
        String number = null;
        DecimalFormat formatter = new DecimalFormat("#,###");
        number = formatter.format(this.price);
        return number;
    }

    public String getFormattedDiscountPrice() {
        String number = null;
        DecimalFormat formatter = new DecimalFormat("#,###");
        number = formatter.format(this.price - (this.price * this.discount));
        return number;
    }

}
