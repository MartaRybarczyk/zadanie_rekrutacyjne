package sii.zadanie.demo.services;

import org.springframework.stereotype.Service;
import sii.zadanie.demo.model.Product;
import sii.zadanie.demo.PromoCodesApplication;

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
