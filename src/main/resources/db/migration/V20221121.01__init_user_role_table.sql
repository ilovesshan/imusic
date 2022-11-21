-- 重置用户表、角色表、用户角色表 并添加初始化数据

DELETE FROM `user_role`;
DELETE FROM `user`;
DELETE FROM `role`;


INSERT INTO
	`imusic`.`user` (`id`, `username`, `nickname`, `password`, `gender`, `locked`, `enabled`, `last_login_ip`, `last_login_time`, `created_time`, `updated_time`)
VALUES
	('2Hecgl1fzxwlokRQ96HAUPxXuHp', 'admin', NULL, '$2a$10$O8gmmgHagCgcA.8cyDxq4e.9DGDZfCKBczppRMIoFR2eZKDJKPctK', NULL, '0', '1', NULL, NULL, now(), now()),
	('2HXLGC2YfMZhzoB0wlWkmTeE9zq', 'ilovesshan', NULL, '$2a$10$p0GDkbgRakc4iuxjlrTZU.Euc/iQk0caftxKRpyMedel9X/i48pMq', NULL, '0', '1', NULL, NULL,now(), now());


INSERT INTO
	`imusic`.`role` (`id`, `name`, `title`, `created_time`, `updated_time`)
VALUES
	('2HecmkwtyZvwEtpTH1G3LvDobLm', 'ROLE_ADMIN', '管理员', now(), now()),
	('2HnejXgnRrJaoN9LgiXpth8zPmO', 'ROLE_USER', '普通用户', now(), now());


INSERT INTO
	`imusic`.`user_role` (`user_id`, `role_id`)
VALUES
	('2Hecgl1fzxwlokRQ96HAUPxXuHp', '2HecmkwtyZvwEtpTH1G3LvDobLm'),
	('2Hecgl1fzxwlokRQ96HAUPxXuHp', '2HnejXgnRrJaoN9LgiXpth8zPmO'),
	('2HXLGC2YfMZhzoB0wlWkmTeE9zq', '2HecmkwtyZvwEtpTH1G3LvDobLm');

