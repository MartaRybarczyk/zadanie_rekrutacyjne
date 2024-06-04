package sii.zadanie.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createProduct(@RequestParam Map<String, String> params) {
        if (!params.containsKey("name"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE NAME YOU FOOL");
        if (!params.containsKey("price"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE PRICE YOU FOOL");
        if (!params.containsKey("currency"))
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("YOU DIDN'T SPECIFY THE CURRENCY YOU FOOL");
        final Product newProduct = new Product(
                params.get("name"),
                params.getOrDefault("description", null),
                Float.parseFloat(params.get("price")),
                params.get("currency")
        );
        productService.createProduct(newProduct);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("OK");
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productService.getAllProducts());
    }

    @GetMapping("/update/{productName}")
    public ResponseEntity<String> updateProduct(@PathVariable String productName, @RequestParam Map<String, String> params) {
        Product searched = PromoCodesApplication.database.searchByName(productName);
        if (searched == null)
            return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body("NO SUCH PRODUCT YOU FOOL");
        final Product updated = new Product(
                params.getOrDefault("name", searched.getName()),
                params.getOrDefault("description", searched.getDescription()),
                Float.parseFloat(params.getOrDefault("price", String.valueOf(searched.getPrice()))),
                params.getOrDefault("currency", searched.getCurrency())
        );
        productService.updateProduct(productName, updated);
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("OK");
    }
}