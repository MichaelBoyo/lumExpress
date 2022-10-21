package africa.semicolon.lumexpress.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateCustomerDetails {
    private Long customerId;;
    private String phoneNumber;
    private String imageUrl;

    private int buildingNumber;
    private String street;
    private String city;
    private String state;
}
