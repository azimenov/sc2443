package ag.selm.manager.controller;

import ag.selm.manager.controller.payload.NewProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findProducts() {
        return productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody NewProductPayload payload) {
        Product product = productService.createProduct(payload.title(), payload.details());
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
