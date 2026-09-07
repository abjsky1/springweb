
-- CREATE TABLE menu (
--     menu_id INT AUTO_INCREMENT PRIMARY KEY,
--     menu_name VARCHAR(50) NOT NULL,
--     menu_price INT NOT NULL
-- );
-- 
-- CREATE TABLE product (
--     product_no INT AUTO_INCREMENT PRIMARY KEY,
--     product_name VARCHAR(50) NOT NULL,
--     product_price INT NOT NULL
-- );
-- 
-- CREATE TABLE recipe (
--     menu_no INT NOT NULL,
--     product_no INT NOT NULL,
--     recipe_order INT NOT NULL,
-- 
--     PRIMARY KEY (menu_no, recipe_order),
-- 
--     CONSTRAINT fk_recipe_menu
--         FOREIGN KEY (menu_no)
--         REFERENCES menu(menu_id),
-- 
--     CONSTRAINT fk_recipe_product
--         FOREIGN KEY (product_no)
--         REFERENCES product(product_no)
-- );
-- 
-- CREATE TABLE productLog (
--     productLog_no INT AUTO_INCREMENT PRIMARY KEY,
--     product_id INT NOT NULL,
--     product_qty INT NOT NULL,
--     product_condition VARCHAR(20) NOT NULL,
--     productLog_price INT NOT NULL,
--     customerLog_day INT NOT NULL,
-- 
--     CONSTRAINT fk_productLog_product
--         FOREIGN KEY (product_id)
--         REFERENCES product(product_no)
-- );


INSERT INTO menu (menu_name, menu_price) VALUES
('치즈버거', 5000),
('불고기버거', 5500),
('더블불고기버거', 7500),
('새우버거', 6000),
('베이컨치즈버거', 7000),
('클래식버거', 6500),
('더블치즈버거', 8000),
('스파이시버거', 7000),
('치킨버거', 6500),
('프리미엄버거', 10000);

INSERT INTO product (product_name, product_price) VALUES
('햄버거빵', 500),
('소고기패티', 1200),
('불고기패티', 1000),
('치즈', 300),
('양상추', 200),
('토마토', 300),
('피클', 100),
('베이컨', 500),
('새우패티', 1300),
('치킨패티', 1100),
('양파', 150),
('스파이시소스', 200);

INSERT INTO recipe (menu_no, product_no, recipe_order)
VALUES (1, 1, 1),
       (1, 4, 2),
       (1, 2, 3),
       (1, 5, 4),
       (1, 1, 5),

       (2, 1, 1),
       (2, 3, 2),
       (2, 11, 3),
       (2, 5, 4),
       (2, 1, 5),

       (3, 1, 1),
       (3, 3, 2),
       (3, 4, 3),
       (3, 3, 4),
       (3, 11, 5),
       (3, 5, 6),
       (3, 7, 7),
       (3, 1, 8),

       (4, 1, 1),
       (4, 5, 2),
       (4, 9, 3),
       (4, 7, 4),
       (4, 1, 5),

       (5, 1, 1),
       (5, 8, 2),
       (5, 4, 3),
       (5, 2, 4),
       (5, 6, 5),
       (5, 5, 6),
       (5, 1, 7),

       (6, 1, 1),
       (6, 5, 2),
       (6, 6, 3),
       (6, 7, 4),
       (6, 11, 5),
       (6, 2, 6),
       (6, 1, 7),

       (7, 1, 1),
       (7, 4, 2),
       (7, 2, 3),
       (7, 4, 4),
       (7, 2, 5),
       (7, 5, 6),
       (7, 7, 7),
       (7, 1, 8),

       (8, 1, 1),
       (8, 12, 2),
       (8, 10, 3),
       (8, 11, 4),
       (8, 5, 5),
       (8, 12, 6),
       (8, 1, 7),

       (9, 1, 1),
       (9, 5, 2),
       (9, 10, 3),
       (9, 6, 4),
       (9, 7, 5),
       (9, 1, 6),

       (10, 1, 1),
       (10, 4, 2),
       (10, 2, 3),
       (10, 8, 4),
       (10, 4, 5),
       (10, 2, 6),
       (10, 6, 7),
       (10, 11, 8),
       (10, 5, 9),
       (10, 7, 10),
       (10, 1, 11);


INSERT INTO productlog
(product_log_no, product_no, product_qty, product_condition, product_log_price, customer_log_day)
VALUES
(1, 1, 200, 'ORDER', 20000, 1),
(2, 2, -100, 'USE', 0, 1),
(3, 3, 100, 'ORDER', 30000, 1),
(4, 5, 150, 'ORDER', 75000, 1),
(5, 7, 50, 'ORDER', 25000, 2),
(6, 8, -30, 'USE', 0, 2),
(7, 9, -100, 'USE', 0, 2),
(8, 10, 80, 'ORDER', 12000, 2),
(9, 4, 40, 'USE', 0, 3),
(10, 6, 120, 'ORDER', 60000, 3);

