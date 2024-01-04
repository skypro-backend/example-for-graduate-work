-- liquibase formatted sql

-- changeset syutins:1
CREATE TABLE images (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   user_id INTEGER,
   ad_id INTEGER,
   file_size BIGINT,
   media_type VARCHAR(255),
   file_path VARCHAR(255),
   data OID,
   CONSTRAINT pk_images PRIMARY KEY (id)
);
ALTER TABLE images ADD CONSTRAINT uc_images_ad UNIQUE (ad_id);
ALTER TABLE images ADD CONSTRAINT uc_images_user UNIQUE (user_id);
ALTER TABLE images ADD CONSTRAINT FK_IMAGES_ON_AD FOREIGN KEY (ad_id) REFERENCES ads (id);
ALTER TABLE images ADD CONSTRAINT FK_IMAGES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);