package al.acasa.acasa_menu_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoryRequest {

    @NotBlank
    private String nameAl;

    private String nameEn;
    private Integer displayOrder = 0;
    private Boolean isActive = true;
}