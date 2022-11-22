create TABLE `music` (
    `id` VARCHAR(32) NOT NULL PRIMARY KEY comment '音乐ID',
    `name` VARCHAR(32) NULL comment '音乐名称',
    `status` ENUM('DRAFT', 'PUBLISHED', 'CLOSED') NOT NULL DEFAULT 'DRAFT' comment '音乐描述 DRAFT-草稿、PUBLISHED-已发布、CLOSED-已下架',
    `description` VARCHAR(256) NULL comment '音乐描述',
    `created_time` datetime(6) NOT NULL comment '创建时间',
    `updated_time` datetime(6) NOT NULL comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment '音乐表';