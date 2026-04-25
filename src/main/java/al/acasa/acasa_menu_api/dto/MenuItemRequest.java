package al.acasa.acasa_menu_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MenuItemRequest {

    @NotNull
    private Long categoryId;

    @NotBlank
    private String nameAl;

    private String descriptionAl;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    private String imageUrl;
    private Boolean isAvailable = true;
    private Integer displayOrder = 0;
}