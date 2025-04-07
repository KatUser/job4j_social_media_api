CREATE TABLE app_user_subscriber
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    user_id INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    subscriber_id INT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE
);
