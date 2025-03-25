CREATE TABLE post
(
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES app_user(id) ON DELETE CASCADE,
    created TIMESTAMP NOT NULL ,
    title VARCHAR NOT NULL,
    text TEXT NOT NULL
);
