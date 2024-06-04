package sii.zadanie.demo.services;

import org.springframework.stereotype.Service;
import sii.zadanie.demo.PromoCodesApplication;
import sii.zadanie.demo.model.PromoCode;

import java.util.List;

@Service
public class PromoCodeService {

    public List<PromoCode> getAllPromoCodes() {
        return PromoCodesApplication.database.getPromoCodes();
    }

    public void createPromoCode(PromoCode newPromoCode) {
        PromoCodesApplication.database.insertPromoCode(newPromoCode);
    }

}
