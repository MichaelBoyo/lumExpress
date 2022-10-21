package africa.semicolon.lumexpress.service.productService;

import africa.semicolon.lumexpress.data.dto.request.AddProductRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dto.response.AddProductResponse;
import africa.semicolon.lumexpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.ProductRepository;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import africa.semicolon.lumexpress.service.cloud.CloudService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final CloudService cloudService;

    private ObjectMapper objectMapper;


    @Override
    public AddProductResponse addProduct(AddProductRequest request) throws IOException {
        Product product = mapper.map(request, Product.class);
        product.getCategories().add(
                Category.valueOf(request.getProductCategory().toUpperCase()));
//        String imageUrl =
//                cloudService.upload(request.getImage()
//                        .getBytes(), ObjectUtils.emptyMap());
//        product.setImageUrl(imageUrl);
        Product savedProduct = productRepository.save(product);
        return buildAddProductResponse(savedProduct);
    }

    private AddProductResponse buildAddProductResponse(Product savedProduct) {
        return AddProductResponse.builder()
                .code(201)
                .productId(savedProduct.getId())
                .message("product added successfully")
                .build();
    }

    @Override
    public UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch) throws ProductNotFoundException {
        Product foundProduct =
                productRepository.findById(productId)
                        .orElseThrow(ProductNotFoundException::new);

        Product updatedProduct = applyPatchToProduct(patch, foundProduct);
        assert updatedProduct != null;
        var savedProduct = productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }

    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct) {
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        try {
           var patchedProductNode = patch.apply(productNode);
            return objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode),
                    Product.class);
        } catch (IOException | JsonPatchException exception) {
            throw new RuntimeException(exception.getMessage(), exception.getCause());
        }
    }

    private static UpdateProductResponse buildUpdateResponse(Product savedProduct) {
        return UpdateProductResponse.builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("update successful")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageSpecs = PageRequest
                .of(getAllItemsRequest.getPageNumber() - 1,
                        getAllItemsRequest.getNumberOfItemsPerPage());
        return productRepository.findAll(pageSpecs);
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }


}
