-- Categories (Breakfast, Drinks, Brunch, etc.)
CREATE TABLE category (
                          id          BIGSERIAL PRIMARY KEY,
                          name_al     VARCHAR(100) NOT NULL,
                          name_en     VARCHAR(100),
                          display_order INT NOT NULL DEFAULT 0,
                          is_active   BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Menu items
CREATE TABLE menu_item (
                           id              BIGSERIAL PRIMARY KEY,
                           category_id     BIGINT NOT NULL REFERENCES category(id) ON DELETE CASCADE,
                           name_al         VARCHAR(150) NOT NULL,
                           name_en         VARCHAR(150),
                           description_al  TEXT,
                           description_en  TEXT,
                           price           NUMERIC(8, 2) NOT NULL,
                           image_url       VARCHAR(500),
                           is_available    BOOLEAN NOT NULL DEFAULT TRUE,
                           is_featured     BOOLEAN NOT NULL DEFAULT FALSE,
                           is_vegan        BOOLEAN NOT NULL DEFAULT FALSE,
                           is_gluten_free  BOOLEAN NOT NULL DEFAULT FALSE,
                           display_order   INT NOT NULL DEFAULT 0,
                           created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
                           updated_at      TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Admin users
CREATE TABLE admin_user (
                            id            BIGSERIAL PRIMARY KEY,
                            username      VARCHAR(80) NOT NULL UNIQUE,
                            password_hash VARCHAR(255) NOT NULL,
                            created_at    TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Seed the first admin (password: admin123 — change immediately after first login)
-- BCrypt hash of "admin123"
INSERT INTO admin_user (username, password_hash)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

-- Seed some starter categories for aCasa
INSERT INTO category (name_al, name_en, display_order) VALUES
                                                           ('Mëngjes', 'Breakfast', 1),
                                                           ('Brunch', 'Brunch', 2),
                                                           ('Pije', 'Drinks', 3),
                                                           ('Ëmbëlsira', 'Desserts', 4);