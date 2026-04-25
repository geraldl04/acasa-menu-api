package al.acasa.acasa_menu_api.config;

import al.acasa.acasa_menu_api.entity.AdminUser;
import al.acasa.acasa_menu_api.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${app.admin.username:admin}")
//    private String adminUsername;
//
//    @Value("${app.admin.password:admin123}")
//    private String adminPassword;

    @Override
    public void run(String... args) {
        if (adminUserRepository.findByUsername("admin").isEmpty()) {
            AdminUser admin = AdminUser.builder()
                    .username("admin")
                    .passwordHash(passwordEncoder.encode("admin123"))
                    .createdAt(LocalDateTime.now())
                    .build();
            adminUserRepository.save(admin);
            log.info("Admin user '{}' created.", "adminUsername");
        } else {
            log.info("Admin user '{}' already exists, skipping.", "adminUsername");
        }
    }
}