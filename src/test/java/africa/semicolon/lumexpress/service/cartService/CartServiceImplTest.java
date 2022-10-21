package africa.semicolon.lumexpress.service.cartService;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.service.productService.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    private Cart cart;
    private Product product;
    private CartRequest cartRequest;


    @BeforeEach
    void setUp() {
        cart = cartService.getCartById(1L);
        product = productService.getProductById(1L);
        cartRequest = CartRequest.builder()
                .productId(product.getId())
                .cartId(cart.getId())
                .build();
    }

    @Test
    void addProductToCart() {
        CartResponse cartResponse = cartService.addProductToCart(cartRequest);
        assertThat(cartResponse).isNotNull();
        assertThat(cartResponse.cart().getSubTotal().doubleValue())
                .isEqualTo(cart.getSubTotal().add(product.getPrice()).doubleValue());
    }
}