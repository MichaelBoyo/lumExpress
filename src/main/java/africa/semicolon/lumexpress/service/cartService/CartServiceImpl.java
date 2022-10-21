package africa.semicolon.lumexpress.service.cartService;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Item;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.CartRepository;
import africa.semicolon.lumexpress.exception.CartNotFoundException;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import africa.semicolon.lumexpress.service.productService.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record CartServiceImpl(CartRepository cartRepository, ProductService productService) implements CartService {

    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) throws ProductNotFoundException, CartNotFoundException {
        Cart cart = cartRepository.findById(cartRequest.getCartId())
                .orElseThrow(CartNotFoundException::new);
        Product product = productService.getProductById(cartRequest.getProductId());
        Item item = Item.builder()
                .product(product)
                .quantity(1)
                .build();
        log.info("item initialised successfully ===> {}", item);
        cart.getItems().add(item);
        var items = cart.getItems();
        cart.setSubTotal(items.stream().map(i -> i.getProduct().getPrice())
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        log.info("cart ===> {}", cart);
        cartRepository.save(cart);
        return new CartResponse("item added successfully", cart);
    }

    @Override
    public Cart getCartById(long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

}
