package al.acasa.acasa_menu_api.service;

import al.acasa.acasa_menu_api.dto.*;
import al.acasa.acasa_menu_api.entity.*;
import al.acasa.acasa_menu_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    // ── Public ──────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<CategoryDto> getFullMenu() {
        return categoryRepository
                .findByIsActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(this::toCategoryDto)
                .toList();
    }



    // ── Admin: Categories ────────────────────────────────────

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(this::toCategoryDtoNoItems).toList();
    }

    public CategoryDto createCategory(CategoryRequest req) {
        Category cat = Category.builder()
                .nameAl(req.getNameAl())
                .nameEn(req.getNameEn())
                .displayOrder(req.getDisplayOrder())
                .isActive(req.getIsActive())
                .createdAt(LocalDateTime.now())
                .build();
        return toCategoryDtoNoItems(categoryRepository.save(cat));
    }

    public CategoryDto updateCategory(Long id, CategoryRequest req) {
        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        cat.setNameAl(req.getNameAl());
        cat.setNameEn(req.getNameEn());
        cat.setDisplayOrder(req.getDisplayOrder());
        cat.setIsActive(req.getIsActive());
        return toCategoryDtoNoItems(categoryRepository.save(cat));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // ── Admin: Items ─────────────────────────────────────────

    public MenuItemDto createItem(MenuItemRequest req) {
        Category cat = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        MenuItem item = buildItem(req, cat);
        return toItemDto(menuItemRepository.save(item));
    }

    public MenuItemDto updateItem(Long id, MenuItemRequest req) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        Category cat = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        item.setCategory(cat);
        item.setNameAl(req.getNameAl());
        item.setDescriptionAl(req.getDescriptionAl());
        item.setPrice(req.getPrice());
        item.setImageUrl(req.getImageUrl());
        item.setIsAvailable(req.getIsAvailable());
        item.setDisplayOrder(req.getDisplayOrder());
        return toItemDto(menuItemRepository.save(item));
    }

    public void deleteItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    // ── Mappers ──────────────────────────────────────────────

    private CategoryDto toCategoryDto(Category cat) {
        return CategoryDto.builder()
                .id(cat.getId())
                .nameAl(cat.getNameAl())
                .nameEn(cat.getNameEn())
                .displayOrder(cat.getDisplayOrder())
                .isActive(cat.getIsActive())
                .items(cat.getItems() == null ? List.of() :
                        cat.getItems().stream().filter(i -> i.getIsAvailable()).map(this::toItemDto).toList())
                .build();
    }

    private CategoryDto toCategoryDtoNoItems(Category cat) {
        return CategoryDto.builder()
                .id(cat.getId())
                .nameAl(cat.getNameAl())
                .nameEn(cat.getNameEn())
                .displayOrder(cat.getDisplayOrder())
                .isActive(cat.getIsActive())
                .build();
    }

    private MenuItemDto toItemDto(MenuItem item) {
        return MenuItemDto.builder()
                .id(item.getId())
                .categoryId(item.getCategory().getId())
                .nameAl(item.getNameAl())
                .descriptionAl(item.getDescriptionAl())
                .price(item.getPrice())
                .imageUrl(item.getImageUrl())
                .isAvailable(item.getIsAvailable())
                .displayOrder(item.getDisplayOrder())
                .build();
    }

    private MenuItem buildItem(MenuItemRequest req, Category cat) {
        return MenuItem.builder()
                .category(cat)
                .nameAl(req.getNameAl())
                .descriptionAl(req.getDescriptionAl())
                .price(req.getPrice())
                .imageUrl(req.getImageUrl())
                .isAvailable(req.getIsAvailable())
                .displayOrder(req.getDisplayOrder())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}