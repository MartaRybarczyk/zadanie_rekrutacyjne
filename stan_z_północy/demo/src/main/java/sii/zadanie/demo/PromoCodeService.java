package sii.zadanie.demo;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromoCodeService {

    public List<PromoCode> getAllPromoCodes() {
      return List.of(
		new PromoCode(
			"PLN",
			25,
            4
		)
	  );
    }

    public float calculateDiscountPriceFromPercent(float price, float percent){
        return price - (price * percent);
    }

    public float calculateDiscountPriceFromAmount(float price, float discoutPrice){
        return price - discoutPrice;
    }

    public void updateListOfPromoCodes(PromoCode promocode){

    }
}
