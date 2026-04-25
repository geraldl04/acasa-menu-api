package al.acasa.acasa_menu_api.controller;

import al.acasa.acasa_menu_api.dto.*;
import al.acasa.acasa_menu_api.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/items")
@RequiredArgsConstructor
public class AdminItemController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItemDto> create(@Valid @RequestBody MenuItemRequest req) {
        return ResponseEntity.ok(menuService.createItem(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> update(@PathVariable Long id,
                                              @Valid @RequestBody MenuItemRequest req) {
        return ResponseEntity.ok(menuService.updateItem(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}