CREATE TABLE news
(
  id            BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title         TEXT                   NOT NULL,
  author        VARCHAR(45)            NOT NULL,
  brief         TEXT                   NOT NULL,
  content       TEXT                   NOT NULL,
  creation_date DATETIME               NOT NULL
);

CREATE TABLE comments
(
  id      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  author  VARCHAR(45)            NOT NULL,
  content TEXT                   NOT NULL,
  news_id BIGINT(20),
  CONSTRAINT news_id_comments FOREIGN KEY (news_id) REFERENCES news (id)
);
CREATE INDEX news_id_comments_idx
  ON comments (news_id);

CREATE TABLE users
(
  id       BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name     VARCHAR(45)            NOT NULL,
  password VARCHAR(60)            NOT NULL,
  enabled  TINYINT(4) DEFAULT '1' NOT NULL
);

CREATE TABLE roles
(
  id   BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  role VARCHAR(45)            NOT NULL
);

CREATE TABLE user_roles
(
  id      BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20)             NOT NULL,
  role_id BIGINT(20)             NOT NULL,
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE INDEX fk_role_idx
  ON user_roles (role_id);
CREATE INDEX fk_user_idx
  ON user_roles (user_id);