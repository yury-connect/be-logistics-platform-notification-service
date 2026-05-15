CREATE TABLE notifications (
       id BIGSERIAL PRIMARY KEY,
       kafka_event_id VARCHAR(100) NOT NULL UNIQUE,
       user_id BIGINT NOT NULL,
       payload JSONB,                    -- 👈 тип JSONB в PostgreSQL
       content TEXT,
       created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
       sent_at TIMESTAMPTZ
);
