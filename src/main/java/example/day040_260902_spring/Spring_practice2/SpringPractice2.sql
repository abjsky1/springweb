DROP DATABASE IF EXISTS mydb260902practice2;

CREATE DATABASE mydb260902practice2;

USE mydb260902practice2;

CREATE TABLE test(
    no INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,
    writer VARCHAR(255)

);

INSERT into test(content , writer) VALUES('안녕하세요' , '김길리');
INSERT into test(content , writer) VALUES('반갑습니다' , '홍길동');
INSERT into test(content , writer) VALUES('감사합니다' , '가나디');

SELECT * from test;