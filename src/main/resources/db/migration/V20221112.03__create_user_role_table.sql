create TABLE user_role
    (
        user_id VARCHAR(32) NOT NULL comment '用户ID',
        role_id VARCHAR(32) NOT NULL comment '角色ID',
        CONSTRAINT c_user_id FOREIGN KEY (user_id) REFERENCES user (id),
        CONSTRAINT c_role_id FOREIGN KEY (role_id) REFERENCES role (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '用户角色表';