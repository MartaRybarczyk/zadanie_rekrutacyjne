package sii.zadanie.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public void createProduct(Product product) {
        PromoCodesApplication.database.insertProduct(product);
    }

    public List<Product> getAllProducts() {
        return PromoCodesApplication.database.getProducts();
    }

    public void updateProduct(String productName, Product newState) {
        PromoCodesApplication.database.editProduct(productName, newState);
    }
}