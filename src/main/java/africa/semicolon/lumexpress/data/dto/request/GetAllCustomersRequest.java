package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCustomersRequest {
    private int numberOfCustomersPerPage;
    private int pageNumber;
}
