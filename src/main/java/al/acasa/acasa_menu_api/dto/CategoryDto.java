package al.acasa.acasa_menu_api.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryDto {
    private Long id;
    private String nameAl;
    private String nameEn;
    private Integer displayOrder;
    private Boolean isActive;
    private List<MenuItemDto> items;
}