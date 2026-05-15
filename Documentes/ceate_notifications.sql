CREATE TABLE notifications (
       id BIGSERIAL PRIMARY KEY,

-- Защита от дублей
       kafka_event_id VARCHAR(100) NOT NULL UNIQUE,

-- Кому
       user_id BIGINT NOT NULL,

-- Что отправляем
       payload JSONB,                    -- переменные для шаблона
       content TEXT,                     -- готовый текст после генерации

-- Аудит
       created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
       sent_at TIMESTAMPTZ               -- NULL = не отправлено / ошибка
);