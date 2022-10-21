package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.service.productService.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/product")
public record ProductController(ProductService productService) {
    @PostMapping(path= "/{name}/{price}/{category}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProduct(@PathVariable String name, @PathVariable Double price,
                                        @PathVariable String category, @RequestPart("image") MultipartFile image)
            throws IOException {
        AddProductRequest request = AddProductRequest.builder()
                .name(name)
                .price(BigDecimal.valueOf(price))
                .image(image)
                .productCategory(category)
                .build();
        return ResponseEntity.ok(productService.addProduct(request));
    }
}
