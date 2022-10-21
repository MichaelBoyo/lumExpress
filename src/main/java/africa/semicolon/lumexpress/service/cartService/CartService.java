package africa.semicolon.lumexpress.service.cartService;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.exception.CartNotFoundException;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest) throws ProductNotFoundException, CartNotFoundException;

    Cart getCartById(long id) throws CartNotFoundException;
}
