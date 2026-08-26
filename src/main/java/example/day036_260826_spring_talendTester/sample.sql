DROP DATABASE IF EXISTS mydb260826_test;
CREATE DATABASE mydb260826_test;
USE mydb260826_test;
CREATE TABLE waitingList( 
    no int AUTO_INCREMENT , 
    phoneNumber VARCHAR(255) ,
    people int ,
    constraint PRIMARY KEY( no ) 
);

insert into waitingList( phoneNumber, people )values( "010-1234-5678", 3 ),( "010-1111-2222", 4); -- 샘플 데이터 2개 