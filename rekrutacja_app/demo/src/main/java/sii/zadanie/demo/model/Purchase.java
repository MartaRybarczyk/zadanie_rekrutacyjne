package sii.zadanie.demo.model;

import java.sql.Date;

public class Purchase {

    private String id;
    private Date purchaseDate;
    private String name;
    private float regularPrice;
    private float discountAmount;

    public Purchase(String id, Date purchaseDate, String name, float regularPrice, float discountAmount) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.name = name;
        this.regularPrice = regularPrice;
        this.discountAmount = discountAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(float regularPrice) {
        this.regularPrice = regularPrice;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
