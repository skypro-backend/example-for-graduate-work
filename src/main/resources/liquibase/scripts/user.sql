
CREATE TABLE user_table (
  id SERIAL PRIMARY KEY,
  email VARCHAR(32) NOT NULL,
  password VARCHAR(255),
  firstName VARCHAR(32),
  lastName VARCHAR(32),
  phone VARCHAR(15),
  role VARCHAR(255),
  image VARCHAR(255)
);