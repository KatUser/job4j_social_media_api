CREATE TABLE app_user_role
(
    role_id   INT REFERENCES role (id),
    app_user_id INT REFERENCES app_user (id)
);