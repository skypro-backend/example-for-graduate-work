-- =============================================
-- Таблица ролей
-- =============================================
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- =============================================
-- Таблица пользователей
-- =============================================
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(255),
    image VARCHAR(255), -- аватар пользователя
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    role_id INTEGER,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Индекс для ускорения поиска по email
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);

-- =============================================
-- Таблица объявлений
-- =============================================
CREATE TABLE IF NOT EXISTS advertisements (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price INTEGER,
    image VARCHAR(255), -- основная картинка
    author_id INTEGER,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_advertisements_author FOREIGN KEY (author_id) REFERENCES users (id)
);

-- Индекс для ускорения поиска объявлений по автору
CREATE INDEX IF NOT EXISTS idx_ads_author ON advertisements(author_id);

-- =============================================
-- Таблица комментариев
-- =============================================
CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    author_id INTEGER,
    ad_id INTEGER,
    CONSTRAINT fk_comments_author FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_comments_ad FOREIGN KEY (ad_id) REFERENCES advertisements (id)
);

-- Индексы для ускорения поиска по объявлениям и авторам
CREATE INDEX IF NOT EXISTS idx_comments_ad ON comments(ad_id);
CREATE INDEX IF NOT EXISTS idx_comments_author ON comments(author_id);

-- =============================================
-- Таблица дополнительных изображений для объявлений
-- =============================================
CREATE TABLE IF NOT EXISTS advertisement_images (
    id SERIAL PRIMARY KEY,
    ad_id INTEGER NOT NULL REFERENCES advertisements(id) ON DELETE CASCADE,
    path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

-- Индекс для ускорения поиска изображений по объявлению
CREATE INDEX IF NOT EXISTS idx_ad_images_ad ON advertisement_images(ad_id);