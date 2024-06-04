package sii.zadanie.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sii.zadanie.demo.PromoCodesApplication;
import sii.zadanie.demo.model.Product;
import sii.zadanie.demo.model.PromoCode;
import sii.zadanie.demo.model.Purchase;
import sii.zadanie.demo.services.PurchaseService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "purchase")
public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createPurchase(@RequestParam Map<String, String> params) {
        if (!params.containsKey("product"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE PRODUCT!");
        if (params.containsKey("promo_code") && PromoCodesApplication.database.searchPromoCodeById(params.get("promo_code")) == null)
            return ResponseEntity.notFound().build();
        var date = Date.valueOf(LocalDate.now());
        if (params.containsKey("date"))
            try {
                date = Date.valueOf(LocalDate.parse(params.get("date")));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DID SPECIFY THE DATE, BUT YOU SPELLED IT WRONG!");
            }
        final Product product = PromoCodesApplication.database.searchProductByName(params.get("product"));
        final PromoCode promoCode = PromoCodesApplication.database.searchPromoCodeById(params.get("promo_code"));final String warning = purchaseService.createPurchase(date, product, promoCode);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(warning == null ? "OK" : warning);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Purchase>> getAllProducts() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(purchaseService.getAllPurchases());
    }
}
