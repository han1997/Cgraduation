create table course
(
    course_id      int auto_increment comment 'id'
        primary key,
    course_no      varchar(25) not null comment '课程编号',
    course_name    varchar(25) null comment '课程名称',
    course_type    varchar(20) not null comment '课程性质',
    user_level     varchar(20) not null comment '学生层次',
    course_time    varchar(25) not null comment '授课时间',
    course_teacher varchar(11) not null comment '主讲教师'
)
    comment '课程表';

create table international_cooperation
(
    ic_id         int auto_increment comment 'id'
        primary key,
    ic_university varchar(25) not null comment '大学/企业名称',
    ic_country    varchar(50) not null comment '所属国家',
    ic_project    varchar(11) not null comment '合作项目id'
)
    comment '国际合作';

create table news
(
    news_id       int auto_increment comment '新闻id'
        primary key,
    news_provider varchar(11) default '0' null comment '新闻发布人id',
    news_title    varchar(50)             not null comment '新闻标题',
    news_desc     varchar(10000)          null comment '新闻描述',
    news_type     varchar(10)             null comment '新闻类型',
    release_time  varchar(25)             null comment '发布时间'
)
    comment '新闻、通知';

create table project
(
    project_id    int auto_increment comment '项目id'
        primary key,
    project_name  varchar(20)  not null comment '项目名',
    project_owner varchar(11)  not null comment '项目所有人id',
    project_desc  varchar(200) null comment '项目描述'
)
    comment '项目信息表';

create table project_member
(
    pm_id              int auto_increment comment 'id'
        primary key,
    project_id         varchar(11) not null comment '项目id',
    project_member_id  varchar(11) not null comment '项目成员id',
    project_member_job varchar(20) null comment '项目成员职务'
)
    comment '项目成员表';

create table user
(
    user_id          int auto_increment comment '用户id'
        primary key,
    user_no          varchar(20) not null comment '用户编号',
    user_name        varchar(20) not null comment '用户姓名',
    user_psd         varchar(50) not null comment '用户密码',
    user_role        varchar(2)  not null comment '用户角色1管理员2教师3学生',
    user_level       varchar(5)  not null comment '学生层次',
    last_login_time  varchar(25) null comment '上次登入时间',
    last_logout_time varchar(25) null comment '上次登出时间'
)
    comment '用户表';


INSERT INTO `user` VALUES (1, '2015211801', 'admin', 'admin', '1', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (2, '2015211802', '123', '123', '2', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (3, '2015211803', '124', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (4, '2015211804', '125', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (5, '2015211805', '126', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (6, '2015211806', '127', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');
INSERT INTO `user` VALUES (7, '2015212806', '1231', '123', '3', '本科', '2015-01-01 01:01:01', '2015-01-01 01:01:01');

