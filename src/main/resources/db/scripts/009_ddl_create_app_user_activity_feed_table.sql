CREATE TABLE app_user_activity_feed
(
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES app_user(id) ON DELETE CASCADE,
    activity_feed_id INT REFERENCES activity_feed(id) ON DELETE CASCADE
);
