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