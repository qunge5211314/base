create table if not exists user
(
    id          bigint   not null primary key auto_increment comment '主键id',
    is_valid    boolean  not null default true comment '是否有效记录',
    create_time datetime not null default CURRENT_TIMESTAMP comment '记录创建时间',
    update_time datetime not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '记录更新时间',
    name        varchar(32) not null unique comment '用户名',
    email       varchar(60) not null unique comment '注册邮箱',
    password    varchar(32) not null comment '密码',
    gender      boolean comment '性别',
    portrait    varchar(32) comment '头像',
    index (is_valid),
    index (create_time),
    index (update_time),
    index (name),
    index (email),
    index (password)
) engine = innodb,
  charset = utf8, comment ='用户表';