CREATE TABLE usuarios(

    id bigint not null auto_increment,
    login varchar(50) not null,
    clave varchar(100) not null,

    primary key(id)
);