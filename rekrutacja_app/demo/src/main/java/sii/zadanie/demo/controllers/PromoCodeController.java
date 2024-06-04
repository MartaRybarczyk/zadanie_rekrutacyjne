package sii.zadanie.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sii.zadanie.demo.PromoCodesApplication;
import sii.zadanie.demo.model.PromoCode;
import sii.zadanie.demo.services.PromoCodeService;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/promocode")
public class PromoCodeController {

    private final PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeController(PromoCodeService promoCodeService) {
        this.promoCodeService = promoCodeService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createPromoCode(@RequestParam Map<String, String> params) {
        if (!params.containsKey("id"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE ID!");
        else if (PromoCodesApplication.database.searchProductByName(params.get("id")) != null)
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("PRODUCT ALREADY EXISTS!");
        if (!params.containsKey("currency"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE CURRENCY!");
        if (!params.containsKey("discount"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE DISCOUNT!");
        if (!params.containsKey("usages"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE AMOUNT OF USAGES!");
        if (!params.containsKey("expiration_date"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE EXPIRATION DATE!");
        try {
            final PromoCode newPromoCode = new PromoCode(
                    UUID.randomUUID().toString(),
                    params.get("currency"),
                    Float.parseFloat(params.get("discount")),
                    Integer.parseInt(params.get("usages")),
                    Date.valueOf(params.get("expiration_date"))
            );
            promoCodeService.createPromoCode(newPromoCode);
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("OK");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DID SPECIFY THE EXPIRATION DATE, BUT YOU SPELLED IT WRONG!");
        }
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<PromoCode>> getAllPromoCodes() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(promoCodeService.getAllPromoCodes());
    }
}
