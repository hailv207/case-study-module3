package model;

public class Shipper {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String bikeLicense;

    public Shipper(String id, String name, String phone, String address, String bikeLicense) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.bikeLicense = bikeLicense;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBikeLicense() {
        return bikeLicense;
    }

    public void setBikeLicense(String bikeLicense) {
        this.bikeLicense = bikeLicense;
    }
}
