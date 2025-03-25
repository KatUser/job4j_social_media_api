CREATE TABLE activity_feed
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    user_id INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE
);
