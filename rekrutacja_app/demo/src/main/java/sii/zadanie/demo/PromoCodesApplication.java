package sii.zadanie.demo;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import sii.zadanie.demo.model.Product;
import sii.zadanie.demo.model.PromoCode;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
public class PromoCodesApplication {

    public static final SQLiteDatabase database = new SQLiteDatabase();

    public static void main(String[] args) {

        List.of(new Product("bułka", "mleczna", 1, "PLN"),
                new Product("brokuł", "zielony", 5, "PLN"),
                new Product("jogurt", "tłusty", 6, "PLN"),
                new Product("cydr", "jabłkowy", 20, "PLN"),
                new Product("szynka", "z kurczaka", 7, "PLN")
               ).forEach(database::insertProduct);

        final var expiration = Date.valueOf(LocalDate.now().plusMonths(1));
        List.of(new PromoCode(UUID.randomUUID().toString(), "PLN", 15, 1, expiration),
                new PromoCode(UUID.randomUUID().toString(), "USD", 40, 2, expiration),
                new PromoCode(UUID.randomUUID().toString(), "EUR", 20, 1, expiration),
                new PromoCode(UUID.randomUUID().toString(), "PLN", 10, 4, expiration),
                new PromoCode(UUID.randomUUID().toString(), "EUR", 10, 2, expiration)
               ).forEach(database::insertPromoCode);

        try {
            SpringApplication.run(PromoCodesApplication.class, args);
        } catch (Exception e) {
            System.err.println("Failed to start the Spring Boot application.\n");
            e.printStackTrace();
        }
    }

    @PreDestroy
    private static void shutdown() {
        database.close();
    }
}

