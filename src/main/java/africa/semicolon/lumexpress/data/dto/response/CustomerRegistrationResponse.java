package africa.semicolon.lumexpress.data.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegistrationResponse {
    private Long userId;
    private Long addressId;
    private String message;
    private int code;
}
