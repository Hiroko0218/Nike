-- 查看庫
SHOW DATABASES;
-- 創建庫
CREATE DATABASE mydb;
-- 創建庫字符碼為UTF-8
CREATE DATABASE mydb1 CHARSET = UTF8;
-- 創建庫字符碼為GBK
CREATE DATABASE mydb2 CHARSET = GBK;
-- 查看庫的字符編碼
SHOW CREATE DATABASE mydb;
-- 進入mydb庫
USE mydb;
-- 刪除庫
DROP DATABASE mydb;
DROP DATABASE mydb1;
DROP DATABASE mydb2;



CREATE DATABASE studb;
USE studb;
-- 創建表
CREATE TABLE stuinfo
(
    name CHAR(20),
    age  INT
);
-- 顯示當前庫表單列表
SHOW TABLES;
-- 查看表結構
DESC stuinfo;
-- 查看創建表的語句
SHOW CREATE TABLE stuinfo;
-- 刪除表
DROP TABLE stuinfo;
-- 查看當前所有表
SHOW TABLES;


-- 確認現在在哪個庫
SELECT DATABASE();

CREATE DATABASE mydb3 CHARSET = UTF8;
CREATE TABLE t_hero
(
    name CHAR,
    age  INT
);
DESC hero;
-- 修改表單名
RENAME TABLE t_hero TO hero;
SHOW CREATE TABLE hero;
DROP TABLE hero;
SHOW TABLES;
DROP DATABASE mydb3;
SHOW DATABASES;

USE studb;
CREATE TABLE hero
(
    name VARCHAR(32),
    age  INT(3)
);
-- 末尾添加gendr字段
ALTER TABLE hero ADD gendr CHAR(1);
-- 添加id到第一個字段
ALTER  TABLE hero ADD id INT FIRST ;
-- 指定位置添加字段
ALTER TABLE hero ADD pwd CHAR(32) AFTER name;
-- 刪除字段
ALTER TABLE hero DROP pwd;
-- 修改字段數據類型modifu
ALTER TABLE hero MODIFY name CHAR(20);
-- 替換字段change
ALTER TABLE hero CHANGE gendr nickname CHAR(20);

DESC hero;


CREATE DATABASE mydb4 CHARSET=UTF8 ;
USE mydb4 ;
CREATE TABLE teacher( name CHAR(20));
ALTER TABLE teacher ADD age INT;
ALTER TABLE teacher ADD id INT FIRST ;
ALTER TABLE teacher ADD salary INT AFTER age;
ALTER TABLE teacher DROP age;
RENAME TABLE teacher TO t;
DESC t;
DROP TABLE t;
SHOW TABLES ;
DROP DATABASE mydb4;
SHOW DATABASES ;


CREATE DATABASE mydb;
USE mydb;
CREATE TABLE person(
                       name CHAR(20) DEFAULT '無名氏',
                       age INT DEFAULT 0
);
-- 全列插入
INSERT INTO person VALUES ('哈哈',20);
INSERT INTO person VALUES ('呵呵',24),('嘿嘿',23),('齁齁',22);

-- 選擇指定字段插入
INSERT INTO person(name, age) VALUES ('吼吼',21);
INSERT INTO person(age,name) VALUES (21,'嘻嘻');
INSERT INTO person(name) VALUES ('科科');
INSERT INTO person(name) VALUES ('ㄅㄅ');

-- 默認值測試
INSERT INTO person(name) VALUES ('ㄆㄆ');
INSERT INTO person(age) VALUES (40);

-- 查詢表中數據
SELECT * FROM person;



-- 綜合練習shopdb
CREATE DATABASE shopdb;
USE shopdb;
CREATE TABLE user(
                     id INT,
                     username CHAR(20),
                     password CHAR(32),
                     phone CHAR(11) DEFAULT 13911112222,
                     email CHAR(50)
);
DESC user;

-- 全列插入1條表紀錄
INSERT INTO user(id, username, password, phone, email)
VALUES (1,'haha','coco123',0932073934,'coco1230456');
SELECT * FROM user;

-- 指定字段插入數據
INSERT INTO user(id,username,password)
VALUES (2,'喬治','123456'),(3,'丹尼','123456');
SELECT * FROM user;


USE mydb;
SELECT * FROM person;

-- 修改表數據update
UPDATE person SET age=40;
-- 把哈哈的年齡修改為25歲
UPDATE person SET age=25 WHERE name='哈哈';
-- 把呵呵的名字 ㄑㄑ，年齡改為 2
UPDATE person SET name='ㄑㄑ',age=2 WHERE name='呵呵';
-- 把年齡為40的用戶的年齡全部改為36
UPDATE person SET age=36 WHERE age=40;
-- 將每個人的年齡+1
UPDATE person SET age=age+1;

-- 删除delete
DELETE FROM person WHERE name='熊大';
DELETE FROM person WHERE age<30;