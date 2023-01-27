DROP TABLE IF EXISTS workers;

CREATE TABLE workers (
                         id int AUTO_INCREMENT primary key,
                         uname varchar(255),
                         password varchar(255),
                         created_at date,
                         created_by varchar(32)
);
