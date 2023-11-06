-- 作業 -------------------------------
CREATE DATABASE day03db CHARSET=UTF8;
USE day03db;
CREATE TABLE sanguo(
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(30) NOT NULL,
                       gender CHAR(1) NOT NULL,
                       country CHAR(1) NOT NULL,
                       attack INT NOT NULL,
                       defense INT NOT NULL
)CHARSET=UTF8;
INSERT INTO sanguo
VALUES (1, '曹操', '男', '魏', 256, 63),
       (2, '張遼', '男', '魏', 328, 69),
       (3, '甄姬', '女', '魏', 168, 34),
       (4, '夏侯淵', '男', '魏', 366, 83),
       (5, '劉備', '男', '蜀', 220, 59),
       (6, '諸葛亮', '男', '蜀', 170, 54),
       (7, '趙雲', '男', '蜀', 377, 66),
       (8, '張飛', '男', '蜀', 370, 80),
       (9, '孫尚香', '女', '蜀', 249, 62),
       (10, '大喬', '女', '吳', 190, 44),
       (11, '小喬', '女', '吳', 188, 39),
       (12, '周瑜', '男', '吳', 303, 60),
       (13, '吕蒙', '男', '吳', 330, 71);
SELECT * FROM sanguo;

-- 1. 查找所有蜀國人信息，按照攻擊力排名
SELECT*FROM sanguo WHERE country='蜀' ORDER BY attack DESC;
-- 2. 查找攻擊力超過200的魏國英雄名字和攻擊力並顯示為姓名， 攻擊力
SELECT name,attack FROM sanguo WHERE attack>200 AND country = '魏';
-- 3. 所有英雄按照攻擊力降序排序，如果相同則按照防御升序排序
SELECT*FROM sanguo ORDER BY attack DESC ,defense ASC ;
-- 4. 查找名字為3字的
SELECT name FROM sanguo WHERE name LIKE '___';
-- 5. 找到魏國防御力排名2-3名的英雄
SELECT name,country,defense FROM sanguo WHERE country ='魏'
ORDER BY defense DESC LIMIT 1,2;
-- 6. 查找攻擊力比魏國最高攻擊力的人還要高的蜀國英雄
SELECT name,country,attack FROM sanguo
WHERE country = '蜀' AND attack<(SELECT MAX(attack) FROM sanguo
                                WHERE country='魏');
-- 7. 找出表中的最大攻擊力的值？
SELECT name,MAX(attack) FROM sanguo;
-- 8. 表中共有多少個英雄？
SELECT COUNT(*) FROM sanguo;
-- 9. 蜀國英雄中攻擊值大於200的英雄的數量
SELECT COUNT(id) FROM sanguo WHERE country ='蜀'AND attack>200;
-- 10. 計算每個國家的平均攻擊力
SELECT country,AVG(attack) FROM sanguo GROUP BY country;
-- 11. 统計每個國家男性英雄和女性英雄的平均攻擊力
SELECT country,gender,AVG(attack) FROM sanguo GROUP BY country, gender;
-- 12. 所有國家的男英雄中 英雄數量最多的前2名的 國家名稱及英雄數量
SELECT country,COUNT(id) number FROM sanguo WHERE gender = '男'
GROUP BY country ORDER BY number DESC LIMIT 0,2;
-- 13. 统計平均攻擊力大於250的國家的英雄數量,根據數量排序,取前2名
SELECT country,AVG(attack) avg_att,COUNT(id) number FROM sanguo
GROUP BY country HAVING avg_att > 250 ORDER BY number DESC LIMIT 0,2;
-- 14. 统計表中都有哪些國家
SELECT DISTINCT country FROM sanguo;
-- 15. 统計表中有幾個國家
SELECT COUNT(DISTINCT country) FROM sanguo;


USE tedu;
-- DISTINCT 去重 --------------------------------------
-- 查看老師的職稱都有哪些?
SELECT DISTINCT title FROM teacher;
-- 查看學生都有哪些職位?
SELECT DISTINCT job FROM student;
-- 查看各年齡段的學生都有哪些職位?
SELECT DISTINCT age, job FROM student;


-- 子查詢 ------------------------------
-- 單行單列子查詢
-- 1.查看比範傳奇工資高的老師都有誰?
SELECT name,salary FROM teacher
WHERE salary>(SELECT salary FROM teacher WHERE name='範傳奇');
-- 2.查看哪些老師的工資是高於平均工資的?
SELECT name,salary FROM teacher
WHERE salary>(SELECT AVG(salary) FROM teacher);
-- 3.查看和'李費水'在同一個班的學生都有誰?
SELECT class_id,name FROM student
WHERE class_id = (SELECT class_id FROM student WHERE name ='李費水');
-- 4.查看工資最高的老師的工資和獎金是多少?
SELECT name,salary,comm FROM teacher
WHERE salary = (SELECT MAX(salary)FROM teacher);


-- 多行單列子查詢
-- 1.查看與"祝雷"和"李費水"在同一個班的學生都有誰?
SELECT name,class_id
FROM student
WHERE class_id IN (SELECT class_id FROM student WHERE name IN ('祝雷','李費水'));
-- 以下錯誤原因，兩個學生的班級號不同，而沒有任何一個學生的班級號可以同時等於兩個不同的值
SELECT name,class_id
FROM student
WHERE class_id=(SELECT class_id FROM student WHERE name IN('祝雷','李費水'));
-- 2.查看比教科目2和科目4老師工資都高的老師都有誰?
SELECT name,salary FROM teacher
WHERE salary>(SELECT MAX(salary) FROM teacher WHERE subject_id IN (2,4));


-- 多行多列子查詢: 可以將一個查詢結果集當作一張表創建出來
-- 1.創建一張表,該表中記錄了每個科目老師的工資情況,要求展示:最高,最低,總和和平均工資以及該科目id
CREATE TABLE sal
AS
SELECT MAX(salary) max_sal,MIN(salary) min_sal,
       SUM(salary) sum_sal,AVG(salary) avg_sal,subject_id
FROM teacher
GROUP BY subject_id;

SHOW TABLES;
SELECT * FROM sal;
DROP TABLE sal;


-- 關聯查詢 -------------------
CREATE TABLE a(
                  aid INT,
                  aname VARCHAR(20)
);
INSERT INTO a VALUES (1,'聶風'),(2,'步驚雲'),(3,'星矢');

CREATE TABLE b
(
    bid   INT,
    bname VARCHAR(20)
);
INSERT INTO b VALUES (1,'雅典娜'),(2,'水冰月');

SELECT * FROM a;
SELECT * FROM b;
SELECT * FROM a,b;
-- 若名相同時，可重新命名
SELECT aa.aid,aa.aname,bb.bid,bb.bname FROM a aa,b bb;

SHOW TABLES;
DROP TABLE a,b;


-- 課堂練習
USE tedu;
-- 1.查看每個老師以及其負責課程科目名?
SELECT t.name,t.age,s.name FROM teacher t,subject s
WHERE t.subject_id=s.id;
-- 2.查看班級的名稱和對應的班主任(老師)是誰?
SELECT c.name,t.name FROM teacher t ,class c
WHERE c.teacher_id=t.id;
-- 3.查看每個學生的名字，年齡，以及其所在的班級名稱和所在樓層
SELECT s.name,s.age,c.name,c.floor FROM student s ,class c
WHERE s.class_id =c.id;
-- 4.王克晶是哪個班的班主任? 列出:班級名稱，樓層，老師名稱，工資
SELECT c.name,c.floor,t.name,t.salary FROM teacher t,class c
WHERE c.teacher_id=t.id AND t.name ='王克晶';
-- 5.查看三年級的班級班主任都是誰? 要列出班級名稱，所在樓層，班主任名字和工資
SELECT c.name,c.floor,t.name,t.salary FROM teacher t,class c
WHERE c.teacher_id = t.id AND c.name LIKE '3年%';
-- 6.查看來自南京的學生都有誰? 要列出城市名字，學生名字，年齡，性別
SELECT l.name,s.name,s.age,s.gender FROM student s,location l
WHERE s.location_id =l.id AND l.name = '南京';
-- 7.查看5年級的中隊長都有誰? 要列出學生名字，年齡，性別，職位和所在班級的名字以及樓層
SELECT s.name,s.age,s.gender,s.job,c.name,c.floor FROM student s,class c
WHERE s.class_id =c.id AND s.job ='中隊長' AND c.name LIKE '5年級%';


-- N張表關聯 ----------------------------------------------
-- 1.查看"範傳奇"所帶班級的學生都有誰?要列出:學生名字，年齡，班級名稱，老師名字
SELECT s.name,s.age,c.name,t.name FROM teacher t, class c, student s
WHERE c.teacher_id=t.id AND s.class_id=c.id AND t.name='範傳奇';
-- 2.查看1年級1班的同學的名字和來自的城市
SELECT c.name,s.name,l.name FROM class c,student s ,location l
WHERE s.class_id=c.id AND s.location_id=l.id AND c.name ='1年級1班';

-- 課堂練習
-- 1. 查看來自北京的學生都是誰?
SELECT l.name,s.name FROM location l ,student s
WHERE s.location_id = l.id AND l.name ='北京';
-- 2. 教"英語"的老師都是誰?
SELECT s.name,t.name FROM teacher t ,subject s
WHERE t.subject_id = s.id AND s.name ='英語';
-- 3. 劉蒼松所帶班級的學生都有誰?
SELECT t.name,c.name,s.name FROM teacher t,student s ,class c
WHERE s.class_id =c.id AND c.teacher_id =t.id AND t.name ='劉蒼松';
-- 4. 教語文的老師所帶的班級有哪些?
SELECT s.name,t.name,c.name FROM teacher t,class c,subject s
WHERE c.teacher_id = t.id AND t.subject_id =s.id AND s.name = '語文';
-- 5. 王克晶所帶的班級學生都來自哪些城市(去重)?
SELECT DISTINCT l.name FROM teacher t,location l,class c,student s
WHERE s.location_id=l.id AND s.class_id =c.id AND c.teacher_id = t.id AND t.name ='王克晶';
-- 6. 3年級的幾個班主任都教哪些課程?
SELECT c.name,t.name,s.name FROM teacher t,class c,subject s
WHERE c.teacher_id = t.id AND t.subject_id =s.id AND c.name LIKE '3年級%';
-- 7. 工資高於10000的老師所帶班裏的大隊長都是誰?
SELECT t.name,t.salary,c.name,s.name,s.job FROM teacher t,student s ,class c
WHERE s.class_id =c.id AND c.teacher_id =t.id AND t.salary>10000 AND s.job='大隊長';
-- 8. "李費水"的班主任教哪門課?
SELECT st.name,c.name,t.name,s.name FROM teacher t,class c,subject s,student st
WHERE c.teacher_id = t.id AND t.subject_id =s.id AND st.class_id =c.id
  AND st.name ='李費水';
-- 9. 所在4樓的班裏的大隊長和中隊長以及班主任都是誰?
SELECT c.floor,s.name,s.job,t.name FROM teacher t,class c,student s
WHERE s.class_id =c.id AND c.teacher_id =t.id AND c.floor = 4 AND s.job IN ('大隊長','中隊長');
-- 10. 全校最小的同學的班主任是誰?
SELECT s.name,s.birth,t.name FROM teacher t,class c,student s
WHERE s.class_id =c.id AND c.teacher_id =t.id
  AND s.birth =(SELECT MAX(birth)FROM student);


-- 關聯查詢與聚合函數组合使用 --------------------------------
-- 1.查看範傳奇所帶班級的學生共多少人?
SELECT COUNT(s.id) number FROM teacher t, class c, student s
WHERE c.teacher_id=t.id AND s.class_id=c.id AND t.name='範傳奇';
-- 2.查看教語文的老師平均工資是多少?
SELECT s.name,AVG(t.salary) FROM teacher t,subject s
WHERE t.subject_id=s.id AND s.name ='語文';
-- 3.查看教每門課老師的平均工資是多少(GROUP BY)? 列出平均工資和科目名稱
SELECT s.name,AVG(salary) FROM teacher t,subject s
WHERE t.subject_id=s.id GROUP BY s.name;
-- 4.僅查看平均工資高於6000的那些科目的老師平均工資是多少? 列出平均工資和科目名稱
SELECT s.name,AVG(salary) s_avg FROM teacher t,subject s
WHERE t.subject_id=s.id GROUP BY s.name HAVING s_avg >6000 ;
-- 5.查看工資最高的老師班裏的學生共多少人?
SELECT COUNT(s.id) number FROM teacher t, class c, student s
WHERE c.teacher_id=t.id AND s.class_id=c.id AND t.salary=(SELECT MAX(salary)FROM teacher);


-- 多對多關係 -------------------------
-- 1.查看學習語文的學生都有誰，列出學生姓名、科目名稱、學生成績?
SELECT s.name,su.name,tss.score FROM subject su, t_stu_subject_score tss, student s
WHERE tss.subject_id=su.id AND tss.stu_id=s.id AND su.name='語文';
-- 2.查看'李費水'都學了哪門課程以及成績，列出學生姓名、科目名稱、學生成績?
SELECT s.name,su.name,tss.score FROM subject su, t_stu_subject_score tss, student s
WHERE tss.subject_id=su.id AND tss.stu_id=s.id AND s.name='李費水';