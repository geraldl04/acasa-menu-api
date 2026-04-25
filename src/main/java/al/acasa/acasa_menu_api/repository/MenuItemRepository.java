package al.acasa.acasa_menu_api.repository;

import al.acasa.acasa_menu_api.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryIdOrderByDisplayOrderAsc(Long categoryId);
//    List<MenuItem> findByIsFeaturedTrueAndIsAvailableTrue();
}
