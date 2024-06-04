package sii.zadanie.demo.services;

import org.springframework.stereotype.Service;
import sii.zadanie.demo.PromoCodesApplication;
import sii.zadanie.demo.model.Product;
import sii.zadanie.demo.model.PromoCode;
import sii.zadanie.demo.model.Purchase;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseService {

    public String createPurchase(Date purchaseDate, Product product, PromoCode promoCode) {
        float discountAmount = 0;
        String warning = null;
        try {
            discountAmount = calculateDiscountAmount(product, promoCode);
        } catch (IllegalArgumentException e) {
            warning = "Discount calculation failed: " + e.getMessage();
        }
        if (promoCode != null && promoCode.getExpirationDate().before(purchaseDate)) {
            warning = "Discount calculation failed: Promo code expired";
            discountAmount = 0;
        }
        final Purchase purchase = new Purchase(UUID.randomUUID().toString(), purchaseDate, product.getName(), product.getPrice(), discountAmount);
        PromoCodesApplication.database.insertPurchase(purchase);
        return warning;
    }

    private float calculateDiscountAmount(Product product, PromoCode promoCode) {
        if (promoCode == null) return 0;
        if (!product.getCurrency().equals(promoCode.getCurrency())) throw new IllegalArgumentException("Currency mismatch");
        return Math.min(promoCode.getDiscountAmount(), product.getPrice());
    }

    public List<Purchase> getAllPurchases() {
        return PromoCodesApplication.database.getPurchases();
    }
}
