package sii.zadanie.demo.model;

import java.sql.Date;
import java.util.UUID;

public class PromoCode {

    private String id;
    private String currency;
    private float discountAmount;
    private int maxAllowedUsages;
    private Date expirationDate;

    public PromoCode(String id, String currency, float discountAmount, int maxAllowedUsages, Date expires) {
        this.id = id;
        this.currency = currency;
        this.discountAmount = discountAmount;
        this.maxAllowedUsages = maxAllowedUsages;
        this.expirationDate = expires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getMaxAllowedUsages() {
        return maxAllowedUsages;
    }

    public void setMaxAllowedUsages(int maxAllowedUsages) {
        this.maxAllowedUsages = maxAllowedUsages;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
