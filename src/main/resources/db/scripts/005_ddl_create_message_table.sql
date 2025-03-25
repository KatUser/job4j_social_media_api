CREATE TABLE message
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    user_from INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    user_to INT NOT NULL NOT NULL  REFERENCES app_user(id) ON DELETE CASCADE,
    text TEXT
);
