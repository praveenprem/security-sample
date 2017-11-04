CREATE TABLE IF NOT EXISTS users (
  id          INT AUTO_INCREMENT PRIMARY KEY        NOT NULL,
  username    VARCHAR(50)                           NOT NULL,
  email       VARCHAR(200)                          NOT NULL,
  fullName    VARCHAR(200)                          NOT NULL,
  password    TEXT(1000)                            NOT NULL,
  createdOn   TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL
);