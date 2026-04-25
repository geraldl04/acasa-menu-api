package al.acasa.acasa_menu_api.controller;

import al.acasa.acasa_menu_api.dto.*;
import al.acasa.acasa_menu_api.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class PublicMenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getFullMenu() {
        return ResponseEntity.ok(menuService.getFullMenu());
    }

//    @GetMapping("/featured")
//    public ResponseEntity<List<MenuItemDto>> getFeatured() {
//        return ResponseEntity.ok(menuService.getFeaturedItems());
//    }
}