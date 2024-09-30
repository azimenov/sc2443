package ag.selm.manager.controller;

import ag.selm.manager.controller.payload.UpdateProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(("catalogue-api/products/{productId:\\d+}"))
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product) {
        return product;
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,
                                           @Valid @RequestBody UpdateProductPayload payload) {
        this.productService.updateProduct(productId, payload.title(), payload.details());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
