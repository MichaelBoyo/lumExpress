package africa.semicolon.lumexpress.service.cartService;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest);

    Cart getCartById(long id);
}
