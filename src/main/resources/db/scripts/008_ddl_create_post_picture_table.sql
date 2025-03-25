CREATE TABLE post_picture
(
    id SERIAL PRIMARY KEY,
    picture_id INT REFERENCES picture(id) ON DELETE CASCADE,
    post_id INT REFERENCES post(id) ON DELETE CASCADE
);
