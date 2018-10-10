CREATE TABLE USERS(
  username varchar_ignorecase(128) NOT NULL PRIMARY KEY,
  password varchar_ignorecase(128) NOT NULL,
  enabled boolean NOT NULL
);

CREATE TABLE AUTHORITIES(
  username varchar_ignorecase(128) NOT NULL,
  authority varchar_ignorecase(128) NOT NULL
);

CREATE UNIQUE index idx_auth_username ON authorities (username,authority);

CREATE TABLE T_NOTE(
  ID BIGINT NOT NULL AUTO_INCREMENT,
  HEADER VARCHAR(255) NOT NULL,
  BODY VARCHAR(512),
  USERNAME varchar_ignorecase(128) NOT NULL,
  CATEGORY VARCHAR(128) NOT NULL,
  FOREIGN KEY (USERNAME) REFERENCES USERS(username)
);
CREATE SEQUENCE PUBLIC.NOTEANDTODO_SEQUENCE START WITH 100;