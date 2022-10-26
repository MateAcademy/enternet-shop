package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDTO {
    private Long id_code;
    private String value;
    private Long userId;

    public CodeDTO(String value, Long userId) {
        this.value = value;
        this.userId = userId;
    }
}
