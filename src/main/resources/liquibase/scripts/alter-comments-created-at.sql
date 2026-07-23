ALTER TABLE comments
ALTER COLUMN created_at TYPE TIMESTAMP USING to_timestamp(created_at / 1000);