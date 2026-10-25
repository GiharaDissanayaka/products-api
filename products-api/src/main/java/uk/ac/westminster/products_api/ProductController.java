import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.westminster.products_api.Product;
import uk.ac.westminster.products_api.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    //Spring creates the ProductService and hands it over.
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    // ---- in ProductService.java ----
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p ->p.getId().equals(id))
                .findFirst();
    }
    // ---- in ProductController.java ----
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElse(null);
    }
    @PostMapping("/products")
    public Product addProduct (@RequestBody Product product) {
        return productService.addProduct(product);
    }
    // ---- in ProductService.java ----
    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
    // ---- in ProductController.java ----
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}