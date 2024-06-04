package sii.zadanie.demo;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cglib.core.Local;

public class PromoCode {
    private String ID;
    private String currency;
    private float discount_amount;
    private int max_allowed_usages;
    private LocalDate expirationDate;  

    
    public PromoCode() {
    }


    public PromoCode(String currency, float discount_amount, int max_allowed_usages) {
        this.currency = currency;
        this.discount_amount = discount_amount;
        this.max_allowed_usages = max_allowed_usages;
        String ID = UUID.randomUUID().toString();
        expirationDate = LocalDate.now();
        expirationDate.plusMonths(1);
    }


    public PromoCode(String thisCurrency, float discount, int usages, LocalDate expires){
        currency = thisCurrency;
        discount_amount = discount;
        max_allowed_usages = usages;
        expirationDate = expires;
        String ID = UUID.randomUUID().toString();
    }


    public String getID() {
        return ID;
    }


    public void setID(String iD) {
        ID = iD;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public float getDiscount_amount() {
        return discount_amount;
    }


    public void setDiscount_amount(float discount_amount) {
        this.discount_amount = discount_amount;
    }


    public int getMax_allowed_usages() {
        return max_allowed_usages;
    }


    public void setMax_allowed_usages(int max_allowed_usages) {
        this.max_allowed_usages = max_allowed_usages;
    }


    public LocalDate getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    
}
