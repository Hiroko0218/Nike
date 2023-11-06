-- 作業
CREATE DATABASE day2db CHARSET=UTF8;
USE day2db;
CREATE TABLE t_hero(name CHAR(3));
RENAME TABLE t_hero TO hero;
SHOW TABLES ;
ALTER TABLE hero ADD money INT;
ALTER TABLE hero ADD id INT FIRST;
ALTER TABLE hero ADD age INT AFTER name;
DESC hero;
INSERT INTO hero (id,name,age,money)
VALUES (1,'李白',50,6888),
       (2,'趙雲',30,13888),
       (3,'劉備',25,6888);
SELECT *FROM hero;
UPDATE hero SET age=52 WHERE name='劉備';
UPDATE hero SET money=5000 WHERE age<50;
DELETE FROM hero WHERE money=5000;
DROP TABLE hero;
DROP DATABASE day2db;



CREATE DATABASE day02db CHARSET=UTF8;
USE day02db;

-- 數據類型:浮點型
CREATE TABLE person(
                       id INT,
                       name VARCHAR(20),
    -- 7為整數+小數總位數，2為小數點位數
                       salary DOUBLE(7,2)
    )CHARSET  = UTF8;
SHOW TABLES ;
DESC person;

-- 合理範圍內，數據插入成功
INSERT INTO person(id, name, salary)
VALUES (1,'Tom',12345.67);

-- 小數位會進行四捨五入
INSERT INTO person(id, name, salary)
VALUES (2,'Jack',12345.678);

-- 超出範圍，插入失敗: Out of range
INSERT INTO person(id, name, salary)
VALUES (3,'Jack',123456.78);

-- 小數為四捨五入後超出範圍，插入失敗: Out of range
INSERT INTO person(id, name, salary)
VALUES (4,'Luck',99999.996);

SELECT *FROM person;

-- 字符類型:定長CHAR 和 變長VARCHAR
CREATE TABLE person1(
                        id INT,
                        name CHAR(3)
)CHARSET = UTF8;
SHOW TABLES ;
DESC person1;

INSERT INTO person1(id, name)
VALUES (1,'張天愛');

-- 超出範圍，插入失敗
INSERT INTO person1(id, name)
VALUES (2,'迪麗熱巴');


-- 日期時間類型
CREATE TABLE userinfo(
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(20),
                         password CHAR(32),
                         mobile CHAR(11),
                         balance DOUBLE(8,2),
    created_time DATETIME
)CHARSET  = UTF8;
DESC userinfo;

INSERT INTO userinfo(id, username, password, mobile, balance, created_time)
VALUES (1, 'Mike', '123456', '13166668888', 50000.00, '2023-06-06 10:29:30');

-- 時間2種寫法皆可，上面較為易讀
INSERT INTO userinfo(id, username, password, mobile, balance, created_time)
VALUES (1, 'Mike', '123456', '13166668888', 50000.00, '20230606102930');

SELECT *FROM userinfo;

-- 1.將id為12的數據的密碼改為:654321，餘額改為8710.16元
UPDATE userinfo SET password=654321,balance=8710.16 WHERE id =1;

-- 2.在userinfo表的 id，username，password三個字段中插入1條數據，内容自定義
INSERT INTO userinfo (id, username, password)
VALUES (4, 'zhao', '871016');

-- 3.删除userinfo表中id大於100的紀錄（如果没有也沒關係，寫出語句）
DELETE FROM userinfo WHERE id>100;


-- 修改數據類型: TIMESTAMP
-- 1.修改created_time數據類型為TIMESTAMP
-- 2.查詢數據表確認
-- 3.再重新插入一條數據(id, username, password)
-- 4.再次查詢數據表確認

-- 1.
ALTER TABLE userinfo MODIFY created_time TIMESTAMP;
-- 2.
SELECT * FROM userinfo;
-- 3.
INSERT INTO userinfo (id, username, password)VALUES (4, 'hehe', '676050');
-- 4.
SELECT * FROM userinfo;


-- 約束
DROP TABLE userinfo;
CREATE TABLE userinfo(
    -- PRIMARY KEY (主鍵): 非空且不能重複
    -- AUTO_INCREMENT 自增長與主鍵一起使用
                         id INT PRIMARY KEY AUTO_INCREMENT,

    -- 不可為空
                         username VARCHAR(20) NOT NULL UNIQUE ,
                         password CHAR(32) NOT NULL ,

    -- UNIQUE不可重複，但可為null值
                         mobile CHAR(11) NOT NULL UNIQUE ,
                         balance DOUBLE(8,2) NOT NULL DEFAULT 0.00,
       created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)CHARSET  = UTF8;
DESC userinfo;

-- 未给id字段賦值，會自動賦值並保持自增長
INSERT INTO userinfo(username,password)
VALUES('lili', '123456'),('zhao', '123456');
-- 指定id字段时，可以通过NULL进行占位
INSERT INTO userinfo(id,username,password) VALUES (NULL,'ying','123456');
-- 此条语句仅仅是为了测试
DELETE FROM userinfo WHERE id>=3;
-- 查询表中数据
SELECT * FROM userinfo;


-- 非空約束 NOT NULL
-- 報錯: password字段不允許為NULL，而且没有設置默認值Default
INSERT INTO userinfo(id,username) VALUES (NULL,'周杰倫');
-- 正確: 因為balance和created_time 雖然設置了非空NULL，但是也設置了默認值
INSERT INTO userinfo(id,username,password) VALUES (NULL,'周杰倫','123456');

-- 唯一约束UNIQUE
INSERT INTO userinfo(username,password) VALUES (NULL, '123456');
SELECT * FROM userinfo;


-- 創建部門表
CREATE TABLE dept(
                     id int PRIMARY KEY AUTO_INCREMENT,
                     dname VARCHAR(50) NOT NULL
);

INSERT INTO dept
VALUES (1, '技術部'),
       (2, '銷售部'),
       (3, '市場部'),
       (4,'行政部'),
       (5,'財務部'),
       (6,'總裁辦公室');

SELECT *FROM dept;

-- 創建員工表
CREATE TABLE emp(
                    id int PRIMARY KEY AUTO_INCREMENT,
                    name varchar(32) NOT NULL,
                    age tinyint,
                    salary double(8,2),
    dept_id int,
    CONSTRAINT dep_fk FOREIGN KEY (dept_id)
         REFERENCES dept(id)
);
DESC emp;
INSERT INTO emp
VALUES (1, 'Lily', 29, 20000, 2),
       (2, 'Tom', 27, 16000, 1),
       (3, 'Joy', 30, 28000, 1),
       (4, 'Emma', 24, 8000, 4),
       (5, 'Abby', 28, 17000, 3),
       (6, 'Jame', 32, 22000, 3);

-- 員工表中插入1條數據,部門的id為 8(不存在)
-- 數據存入失敗
INSERT INTO emp VALUES (NULL,'Jack',25,12000,8);

-- 默認級連動作：RESTRICT
-- 删除主表dept數據時，如果從表emp中有和其相關的數據，則不允許删除
-- 需要先删除從表emp中相關聯的數據，再删除主表dept中的數據
DELETE FROM emp WHERE dept_id=4;
DELETE FROM dept WHERE id=4;

-- 第二種級連動作: CASCADE
-- 1.刪除外鍵
ALTER TABLE emp DROP FOREIGN KEY dept_fk;
-- 2.增加外鍵(已有表)
ALTER TABLE emp ADD CONSTRAINT dept_fk FOREIGN KEY (dept_id)
    REFERENCES dept(id) ON DELETE CASCADE ON UPDATE CASCADE;
-- 級聯刪除: 刪除主表數據時，從表中相關聯數據一並删除
DELETE FROM dept WHERE id=6;
-- 級聯更新：更新主表主鍵字段時，從表中相關聯的數據一並更新
UPDATE dept SET id=4 WHERE id=2;

SELECT *FROM emp;
SELECT *FROM dept;