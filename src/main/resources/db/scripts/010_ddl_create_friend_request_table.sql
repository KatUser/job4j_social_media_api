CREATE TABLE friend_request
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    user_from_id INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    user_to_id INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    accepted BOOLEAN
);
