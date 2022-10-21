package africa.semicolon.lumexpress.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CartRequest {
    private Long productId;
    private Long cartId;
}
