DROP DATABASE IF EXISTS mydb260902;

CREATE DATABASE mydb260902;

USE mydb260902;

CREATE TABLE exam(
    eno INT AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR(255)
);

INSERT into exam(ename) VALUES('김길리');
INSERT into exam(ename) VALUES('홍길동');
INSERT into exam(ename) VALUES('가나디');

SELECT * from exam;