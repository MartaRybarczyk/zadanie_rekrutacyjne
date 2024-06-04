package sii.zadanie.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.sql.DriverManager;
import java.util.List;
import java.sql.*;

@SpringBootApplication
@RestController
public class PromoCodesApplication {

  public static final SQLiteDatabase database = new SQLiteDatabase();

	public static void main(String[] args) {
    //final Connection database = DriverManager.getConnection(null);
		//SpringApplication.run(PromoCodesApplication.class, args);
    // try {
    //   SpringApplication.run(PromoCodesApplication.class, args);
    // } catch (Exception e) {
    //   System.err.println("Failed to start the Spring Boot application.\n");
    //   e.printStackTrace();
    // }

    List<Product> products = List.of(
            new Product("bułka", "mleczna", 1, "PLN"),
            new Product("brokuł", "zielony", 5, "PLN"),
            new Product("jogurt", "tłusty", 6, "PLN"),
            new Product("cydr", "jabłkowy", 11, "PLN"),
            new Product("szynka", "z kurczaka", 7, "PLN"));

    products.forEach(database::insertProduct);

    database.getProducts().forEach(System.out::println);
    try {
      SpringApplication.run(PromoCodesApplication.class, args);
    } catch (Exception e) {
      System.err.println("Failed to start the Spring Boot application.\n");
      e.printStackTrace();
  }

  }
      

	
	
	
}

