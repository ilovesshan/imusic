create TABLE role
    (
        id VARCHAR(32) NOT NULL   PRIMARY KEY comment '角色ID',
        name VARCHAR(128) NULL comment '角色名称',
        title VARCHAR(128) NULL comment '角色标识',
        created_time datetime(6) NOT NULL comment '创建时间',
        updated_time datetime(6) NOT NULL comment '更新时间'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '角色表';