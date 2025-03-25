CREATE TABLE app_user
(
    id SERIAL PRIMARY KEY,
    registered TIMESTAMP,
    name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email TEXT NOT NULL UNIQUE
);
