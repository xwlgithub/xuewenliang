-- DROP TABLE IF EXISTS authorities;
-- CREATE TABLE mooc_authorities (
--   username varchar(255) NOT NULL,
--   authroity varchar(255) NOT NULL
-- ) ENGINE=InnoDB ;
--
-- DROP TABLE IF EXISTS users;
-- CREATE TABLE mooc_users (
--   username varchar(255) NOT NULL,
--   password varchar(255) NOT NULL,
--   enabled varchar(255) NOT NULL DEFAULT 1
-- ) ENGINE=InnoDB ;

-- 最初的
-- insert into mooc_users
-- (username,password, enabled)
--             values ('user', '{bcrypt}$2a$10$Pp1.wmvTYxkSsP4ahWHVKOrlu9ti.bjqX3k0n9a70H9jQ8F9oaFZ2',1),
--                    ('users', '{SHA-1}{eABYh0gkPCjzj2F7EMz5Q8F05/5o/1mit9Y1n4d4dns=}421b9d0311ab12ea175d206468266b46ae147a3d',1);
-- insert into mooc_authorities(username, authroity) values ('users', 'ROLE_USER'), ('user', 'ROLE_ADMIN'),('user', 'ROLE_USER');

-- insert into mooc_users(id, username, `name`, mobile, password_hash, enabled, account_non_expired, account_non_locked, credentials_non_expired, email)
--             values (1, 'user', 'Zhang San', '13012341234', '{bcrypt}$2a$10$jhS817qUHgOR4uQSoEBRxO58.rZ1dBCmCTjG8PeuQAX4eISf.zowm', 1, 1, 1, 1, 'zhangsan@local.dev'),
--                    (2, 'old_user', 'Li Si', '13812341234', '{SHA-1}7ce0359f12857f2a90c7de465f40a95f01cb5da9', 1, 1, 1, 1, 'lisi@local.dev');
-- insert into mooc_roles(id, role_name) values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
-- insert into mooc_users_roles(user_id, role_id) values (1, 1), (1, 2), (2, 1);
delete from mooc_roles_permissions;
delete from mooc_users_roles;
delete from mooc_permissions;
delete from mooc_users;
delete from mooc_roles;
insert into mooc_permissions(id, permission_name, display_name)
    values (1, 'USER_READ', '查询用户信息'),
           (2, 'USER_CREATE', '新建用户'),
           (3, 'USER_UPDATE', '编辑用户信息'),
           (4, 'USER_ADMIN', '用户管理');
insert into mooc_users(id, username, `name`, mobile, password_hash, enabled, account_non_expired, account_non_locked, credentials_non_expired, using_mfa, mfa_key, email)
    values (1, 'user', 'Zhang San', '13012341234', '{bcrypt}$2a$10$jhS817qUHgOR4uQSoEBRxO58.rZ1dBCmCTjG8PeuQAX4eISf.zowm', 1, 1, 1, 1, true, '8Uy+OZUaZur9WwcP0z+YxNy+QdsWbtfqA70GQMxMfLeisTd8Na6C7DkjhJWLrGyEyBsnEmmkza6iorytQRh7OQ==', 'zhangsan@local.dev'),
           (2, 'old_user', 'Li Si', '13812341234', '{SHA-1}7ce0359f12857f2a90c7de465f40a95f01cb5da9', 1, 1, 1, 1, false, '8Uy+OZUaZur9WwcP0z+YxNy+QdsWbtfqA70GQMxMfLeisTd8Na6C7DkjhJWLrGyEyBsnEmmkza6iorytQRh7OQ==', 'lisi@local.dev');
insert into mooc_roles(id, role_name, display_name, built_in)
    values (1, 'ROLE_USER', '客户端用户', true),
           (2, 'ROLE_ADMIN', '超级管理员', true),
           (3, 'ROLE_STAFF', '管理后台用户', true);
insert into mooc_users_roles(user_id, role_id) values (1, 1), (1, 2), (1, 3), (2, 1);
insert into mooc_roles_permissions(role_id, permission_id) values (1, 1), (2, 1), (2, 2), (2, 3), (2, 4);
