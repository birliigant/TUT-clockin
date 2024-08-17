create table class
(
    id          int auto_increment
        primary key,
    name        char(20)             null,
    student_num int        default 0 null,
    is_deleted  tinyint(1) default 0 null
);

create table clock
(
    id         int auto_increment
        primary key,
    message_id int                       null,
    student_id int                       null,
    class_id   int                       null,
    type       char(10)   default '打卡' null,
    is_pass    tinyint(1) default 0      null,
    time       datetime                  null,
    is_read    tinyint(1) default 0      null,
    is_agreed  tinyint(1) default 0      null,
    reason     char(255)                 null
);

create table college
(
    id         int auto_increment
        primary key,
    name       char(20)             null,
    is_deleted tinyint(1) default 0 null
);

create table message
(
    id          int auto_increment
        primary key,
    location    char(100)            null,
    create_time datetime             null,
    start_time  datetime             null,
    end_time    datetime             null,
    is_deleted  tinyint(1) default 0 null
);

create table message_to_class
(
    id         int auto_increment
        primary key,
    message_id int                  null,
    class_id   int                  null,
    is_deleted tinyint(1) default 0 null
);

create table user
(
    id          int auto_increment
        primary key,
    name        char(10)      null,
    work_id     int           null,
    college_id  int           null,
    email       char(50)      null,
    password    char(255)     null,
    class_id    int           null,
    major_id    int           null,
    grade       int           null,
    dormitory   char(10)      null,
    icon        char(100)     null,
    role        int default 0 null,
    class_ids   char(255)     null,
    create_time datetime      null,
    constraint user_pk
        unique (email),
    constraint user_pk_2
        unique (work_id)
);


