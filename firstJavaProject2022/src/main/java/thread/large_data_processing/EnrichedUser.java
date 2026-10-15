package thread.large_data_processing;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EnrichedUser {

    private int id;
    private String name;
    private String email;

    private int creditScore;
}
