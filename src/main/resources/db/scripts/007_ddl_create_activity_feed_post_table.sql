CREATE TABLE activity_feed_post
(
    id SERIAL PRIMARY KEY,
    activity_feed_id INT REFERENCES activity_feed(id) ON DELETE CASCADE,
    post_id INT REFERENCES post(id) ON DELETE CASCADE
);
