CREATE TABLE topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fecha datetime not null,
    status tinyint not null,
    curso varchar(100) not null,

    primary key(id)
);