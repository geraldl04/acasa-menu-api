package al.acasa.acasa_menu_api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItemDto {
    private Long id;
    private Long categoryId;
    private String nameAl;
    private String descriptionAl;
    private BigDecimal price;
    private String imageUrl;
    private Boolean isAvailable;
    private Integer displayOrder;
}