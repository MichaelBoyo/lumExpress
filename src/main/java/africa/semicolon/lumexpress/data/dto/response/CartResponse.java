package africa.semicolon.lumexpress.data.dto.response;

import africa.semicolon.lumexpress.data.models.Cart;
import lombok.Builder;


public record CartResponse(String message, Cart cart) {

}
