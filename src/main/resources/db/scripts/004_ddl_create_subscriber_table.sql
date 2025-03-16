CREATE TABLE subscriber
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    user_from INT NOT NULL REFERENCES app_user(id),
    user_to INT NOT NULL NOT NULL  REFERENCES app_user(id),
    accepted BOOLEAN
);
