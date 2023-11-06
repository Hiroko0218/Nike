-- 搜尋
SELECT id,name,title FROM teacher;
SELECT name FROM teacher;
-- 開發時較少用，耗時
SELECT *FROM teacher;
DESC teacher;
DESC student;

-- 查看'一級講師'的信息，列出:名字，職位，工資，年齡
SELECT name,title,salary,age FROM teacher WHERE title='一級講師';
-- 查看除了'劉蒼松'以外的所有老師的名字，工資，獎金，職位
SELECT name,salary,comm,title FROM teacher WHERE name!='劉蒼松';
-- 查看職位是'大隊長'的學生的名字,年齡,性別?
SELECT name,age,gender FROM student WHERE job='大隊長';
-- 查看年齡在30歲以上(含)的老師的名字,職稱,工資,獎金
SELECT  name,title,salary,comm FROM teacher WHERE age>30;


-- 算術運算符
-- 查找年齡爲偶數的老師信息
SELECT * FROM teacher WHERE age%2=0;
-- 查看年薪高於60000的老師都有誰?
SELECT name FROM teacher WHERE salary * 12 + comm > 6000;

-- 邏輯運算符
-- AND：'與'，都爲真時才爲真 (並且)
-- 查看7歲的'大隊長'都有誰? 列出這些學生的名字,年齡,性別和職位
SELECT name,age,gender,job FROM student WHERE age=7 AND job='大隊長';
-- 看班級編號小於6的所有中隊長都有誰? 列明名字，年齡，性別，班級編號(class_id)，職位
SELECT name,age,gender,class_id,job FROM student WHERE class_id < 6 AND job = '中隊長';


-- OR：'或'，都爲假時才爲假
-- 查看所有一級講師和三級講師的名字，職稱，工資?
SELECT name,title,salary FROM teacher WHERE title='一級講師'OR title='三級講師';
-- 查看所有大隊長，中隊長和小隊長的名字，性別，年齡和職位?
SELECT name,gender,age,job FROM student WHERE job='大隊長' OR job = '中隊長';


-- AND的優先級高於OR，爲了提高OR的優先級，可以使用小括號 '()'
-- 查看班級編號在6(含)以下的所有大隊長和中隊長的名字，年齡，性別，班級編號和職位
SELECT name, age, gender, class_id, job FROM student
WHERE class_id < 6 AND (job = '大隊長' OR job = '中隊長');


-- day02作業
CREATE DATABASE exercisedb CHARSET = UTF8;
SHOW DATABASES ;
USE exercisedb;
CREATE TABLE books(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      bname VARCHAR(50) NOT NULL ,
                      author VARCHAR(30) NOT NULL DEFAULT '佚名',
                      press VARCHAR(128),
                      price DOUBLE(7,2) NOT NULL DEFAULT 0.00,
    comment TEXT
)CHARSET = UTF8;
DESC books;
INSERT INTO books(bname,author,press,price,comment)
VALUES
    ('邊城','沈從文','機械工業出版社',36,'小城故事多'),
    ('駱駝樣子','老舍','機械工業出版社',43,'你是祥子嗎？'),
    ('茶館','老舍','中國文學出版社',55,'老北京'),
    ('呐喊','魯迅','人民教育出版社',71,'最後的聲音'),
    ('朝花夕拾','魯迅','中國文學出版社',53,'好時光'),
    ('圍城','錢鍾書','中國文學出版社',44,'你心中的圍城是什麼');

INSERT INTO books(bname,author,press,price)
VALUES
    ('林家鋪子','茅盾','機械工業出版社',51),
    ('子夜','茅盾','人民教育出版社',47);

SELECT *FROM books;
DESC books;
-- 查找30多元的書
SELECT id,bname,price FROM books WHERE price>30;
-- 查找人民教育出版社出版的書　
SELECT id,bname,press FROM books WHERE press='人民教育出版社';
-- 查找老舍寫的，中國文學出版社出版的書　
SELECT id,bname,press,author FROM books WHERE author ='老舍' AND press='中國文學出版社';
-- 查找價格超過60元的書，只看書名和價格
SELECT bname,price FROM books WHERE price>60;
-- 查找魯迅寫的或者茅盾寫的書
SELECT id,bname,author,price FROM books WHERE author = '魯迅'OR author='茅盾';
-- 將呐喊的價格修改為45元
UPDATE books SET price = 45 WHERE bname = '呐喊';
-- 增加一個字段出版時間，類型為 date ，放在價格字段price後面
ALTER TABLE books ADD pub_time DATE AFTER price;
DESC books;
-- 修改所有老舍的作品出版時間為 2018-10-01
UPDATE books SET pub_time='2018-10-01' WHERE author='老舍';
-- 修改所有中國文學出版社出版的但是不是老舍的作品出版時間為 2020-01-01
UPDATE books SET pub_time='2020-01-01' WHERE press ='中國文學出版社'AND author !='老舍';
-- 所有魯迅的書價格增加5元
UPDATE books SET price =price+5 WHERE author ='魯迅';
-- 删除所有價格超過70元或者不到40元的書
DELETE FROM books WHERE price<40 OR price>70;




-- 基礎查詢：邏輯運算符 -----------------------------
-- 查看班级編號在6(含)以下的所有大隊長和中對長的名字，年齡，性别，班级編號和職位
USE tedu;
SELECT name,age,gender,class_id,job FROM student
WHERE class_id<=6 AND (job='大隊長' OR job='中隊長');


-- BETWEEN AND------------------------------------------

-- 在一個範圍內
-- 1.查看工資在2000到5000之間的老師的名字,性別,年齡,工資
SELECT name,gender,age,salary FROM teacher WHERE salary BETWEEN 2000 AND 5000;

-- 除了那個範圍外
-- 2.查看年齡除了8到10歲的學生的名字，性別，年齡
SELECT name,gender,age FROM student WHERE age NOT BETWEEN 8 AND 10;
-- 3.查看年齡在20到35之間的男老師都有誰？列出名字，性別，年齡，職稱
SELECT name,gender,age,title FROM teacher WHERE age BETWEEN 20 AND 35 AND gender ='男';
-- 4.查看所有在3-5層的班級都有哪些？列出班級名稱和所在樓層
SELECT name,floor FROM class WHERE floor BETWEEN 3 AND 5;


-- IN 和 NOT IN ------------------------------------------

-- IN(列表)：等於列表其中之一
-- 1.查看所有大隊長，中隊長和小隊長的名字，性別，年齡和職位?
SELECT name,gender,age,job FROM student
WHERE job IN ('大隊長','中隊長','小隊長');
-- 2.查看所有一級講師，二級講師，三級講師的名字，職稱，工資和性別
SELECT name,title,salary,gender FROM teacher
WHERE title IN ('一級講師','二級講師','三級講師');

-- NOT IN(列表)：不在列表中
-- 3.查看除一級講師和二級講師之外的所有老師的名字，職稱，工資
SELECT name,title,salary FROM teacher
WHERE title NOT IN ('一級講師','二級講師');
-- 4.查看除大隊長，中隊長，小隊長的其他學生的名字，職位，性別，年齡
SELECT name,job,gender,age FROM student
WHERE job NOT IN ('大隊長','中隊長','小隊長');
-- 5.查看id編號為1 3 5的老師的 編號，職稱，工資
SELECT id, title,salary FROM teacher WHERE id IN (1,3,5);


-- NULL值判斷------------------------------------------
-- IS NULL：判斷字段的值是否爲NULL
-- 1.查看哪些老師的獎金爲空?
SELECT name,salary,comm FROM teacher
WHERE comm IS NULL;

-- IS NOT NULL：判斷字段的值是否不是NULL
-- 不可以用 = 和 != 判斷NULL值
-- 2.查看有獎的金老師?
SELECT name,salary,comm FROM teacher
WHERE comm IS NOT NULL;


-- 課堂練習
-- 1. 查看負責課程編號(subject_id)爲1的男老師都有誰?
SELECT name,subject_id,gender FROM teacher WHERE subject_id =1 AND gender ='男';
-- 2. 查看工資高於5000的女老師都有誰?
SELECT name,gender,salary FROM teacher WHERE salary>5000 AND gender = '女';
-- 3. 查看工資高於5000的男老師或所有女老師的工資？
SELECT name,gender,salary FROM teacher
WHERE (gender ='男'AND salary>5000 )OR gender = '女';
-- 4. 查看所有9歲學生的學習委員和語文課代表都是誰?
SELECT name,age,job FROM student WHERE job IN ('學習委員','語文課代表')AND age =9;
-- 5. 查看工資在6000到10000之間的老師以及具體工資?
SELECT name,salary FROM teacher WHERE salary BETWEEN 6000 AND 10000;
-- 6. 查看工資在4000到8000以外的老師及具體工資?
SELECT name,salary FROM teacher WHERE salary NOT BETWEEN 4000 AND 8000;
-- 7. 查看老師負責的課程編號都有什麼?
-- 8. 查看所有女老師的職稱都是什麼?
-- 9. 查看7-10歲的男同學的職位都有哪些?
-- 10. 查看一級講師和二級講師的獎金(comm)是多少?
SELECT name,title,comm FROM teacher WHERE title IN ('一級講師','二級講師');
-- 11. 查看除老闆和總監的其他老師的工資和獎金是多少?
SELECT name,title,salary FROM teacher WHERE title NOT IN ('老闆','總監');
-- 12. 查看'3年級2班'和'5年級3班'在那層樓?
SELECT name,floor FROM class WHERE name IN ('3年級2班','5年級3班');


-- 高級查詢
-- 模糊查询 LIKE ------------------------------
-- 1.查看姓張的學生都有誰?
SELECT name FROM student WHERE name LIKE '張%';
-- 2.查看三個字名字中第二個字是'平'的學生都有誰?
SELECT name FROM student WHERE name LIKE '_平_';
-- 3.查看最後一個字是'晶'的老師都有誰?
SELECT name FROM teacher WHERE name LIKE '%晶';
-- 4.查看哪些學生是課代表?列出他的名字和職位
SELECT name,job FROM student WHERE job LIKE '%課代表';
-- 5.查看所有的2班都在哪層?
SELECT name,floor FROM class WHERE name LIKE '%2班';

-- 課堂練習
-- 1. 查詢名字姓"李"的學生姓名
SELECT name FROM student WHERE name LIKE '李%';
-- 2. 查詢名字中包含"江"的學生姓名
SELECT name FROM student WHERE name LIKE '%江%';
-- 3. 查詢名字以"郭"結尾的學生姓名
SELECT name FROM student WHERE name LIKE '%郭';
-- 4. 查詢9-12歲裏是"課代表"的學生信息
SELECT name,age,job FROM student WHERE age BETWEEN 9 AND 12 AND job LIKE '%課代表';
-- 5. 查詢名字第二個字是"苗"的學生信息
SELECT*FROM student WHERE name LIKE '_苗_';
-- 6. 查詢姓"邱"的課代表都是誰?
SELECT name,job FROM student WHERE name LIKE '邱%' AND job LIKE '%課代表';


-- AS 别名 -------------------------------------------
-- 给字段起别名
-- 1.查詢所有老師的姓名、工資和年薪
SELECT name AS 姓名, salary*12 AS 年薪 FROM teacher;
-- 省略AS關鍵字
SELECT name 姓名, salary*12 年薪 FROM teacher;
-- 2.給表重新取名字
SELECT t.name 姓名, t.salary*12 年薪 FROM teacher t;


-- ORDER BY 排序 ------------------------
-- 1.查看老師的工資排名,從多到少
SELECT t.name,t.salary FROM teacher t ORDER BY t.salary DESC;
-- 2.查看老師獎金的排名?
SELECT t.name,t.comm FROM teacher t ORDER BY t.comm DESC ;
-- 3.查看學生的生日,按照從遠到近
SELECT name, birth FROM student ORDER BY birth ;
-- 4.查看7-10歲的學生信息,學生按照年齡從大到小排序(同年齡的看生日)
SELECT*FROM student WHERE age BETWEEN 7 AND 10 ORDER BY birth ;
-- 5.查看老師的工資和獎金，首先按照獎金的升序，再按照工資的降序
SELECT name,comm,salary FROM teacher ORDER BY comm,salary DESC ;


-- LIMIT 分業查詢 -------------------------
-- 1.查看老師工資的前5名?
SELECT name,salary FROM teacher
WHERE gender='男' ORDER BY salary DESC
    LIMIT 5;
-- 2.查看老師獎金信息，按照降序排序後，每頁顯示3條，顯示第5頁?
SELECT name,comm FROM teacher ORDER BY comm DESC LIMIT 12,3;

-- 課堂練習
-- 1.查詢所有10歲學生的生日,按生日對應的年紀從大到小.
SELECT name,age,birth FROM student WHERE age=10 ORDER BY birth;
-- 2. 查詢8歲同學中名字含有"苗"的學生信息
SELECT name,age FROM student WHERE age =8 AND name LIKE '%苗%';
-- 3. 查詢負責課程編號1和2號且工資高於6000的老師信息
SELECT name 姓名,salary 工資,subject_id 課程編號 FROM teacher
WHERE subject_id IN (1,2) AND salary>6000;
-- 4. 查詢10歲以上的語文課代表和數學課代表
SELECT name,age,job FROM student WHERE age>10 AND job IN ('語文課代表','數學課代表');
-- 5. 查詢不教課程編號1的老師信息,按照工資降序排序
SELECT name,salary,subject_id 課程編號 FROM teacher
WHERE subject_id !=1 ORDER BY salary DESC ;
-- 6. 查詢所有老師的獎金，並按照獎金降序排序
SELECT name,comm 獎金 FROM teacher ORDER BY comm DESC ;
-- 7. 查看工資高於8000的老師負責的課程編號都有那些?
SELECT name,salary,subject_id 課程編號 FROM teacher WHERE salary>8000;
-- 8. 查看全校年齡最小學生的第11-15名
SELECT name,age,birth FROM student ORDER BY birth DESC LIMIT 10,15;


-- 聚合函数 --------------------------
-- 1.查看老師的是多少?
SELECT AVG(salary) '平均工資'
FROM teacher;
-- 2.查看老師的最高工資,最低工資,平均工資和工資總和都是多少?
SELECT MAX(salary) '最高工資', MIN(salary) '最低工資', AVG(salary) '平均工資'
FROM teacher;
-- 3.查看負責課程編號1的老師的平均工資是多少?
SELECT AVG(salary) FROM teacher WHERE subject_id = 1;
-- 4.查看總共多少位老師?
SELECT COUNT(id) '總共有幾位老師' FROM teacher;
SELECT COUNT(name) '總共有幾位老師' FROM teacher;
SELECT COUNT(*) '總共有幾位老師' FROM teacher;


-- 課堂練習
-- 1.查看負責課程編號2的老師共多少人?
SELECT COUNT(name) FROM teacher WHERE subject_id = 2;
-- 2. 查看班級編號(class_id)爲1的學生有多少人?
SELECT COUNT(name) FROM student WHERE class_id = 1;
-- 3. 查看全校學生生日最大的是哪天?
SELECT MIN(birth) FROM student ;
-- 4. 查看11歲的課代表總共多少人?
SELECT COUNT(name) FROM student WHERE age =11 AND job LIKE '%課代表';
-- 5. 姓張的學生有多少人?
SELECT COUNT(name) FROM student WHERE name LIKE '張%';
-- 6. 工資高於5000的老師中最低工資是多少?
SELECT MIN(salary) FROM teacher WHERE salary > 5000;
-- 7. 4層有幾個班?
SELECT COUNT(name) FROM class WHERE floor = 4;
-- 8. 老師中"總監"的平均工資是多少?
SELECT AVG(salary) FROM teacher WHERE title = '總監';


-- 分組聚合 ---------------------------
-- 單字段分組
-- 1.查看每種職位的老師平均工資是多少?
SELECT title,AVG(salary) FROM teacher GROUP BY title;
-- 2.查看每個班級各多少人?
SELECT class_id,COUNT(id) '人數' FROM student GROUP BY class_id;
-- 3.查看學生每種職位各多少人,以及最大生日和最小生日?
SELECT COUNT(*) '人數',MIN(birth) '最大生日',MAX(birth) '最小生日',job
FROM student GROUP BY job;

-- 多字段分組
-- 1.查看同班级同性别的學生分别多少人?
SELECT class_id,gender,COUNT(id)'人數' FROM student GROUP BY class_id,gender;
-- 2.查看每個班每種職位各多少人?
SELECT class_id,job,COUNT(id)'人數' FROM student GROUP BY class_id, job;

-- 分組聚合排序
-- 1.查看每個科目老師的平均工資排名?
SELECT subject_id,AVG(salary) avgs FROM teacher
GROUP BY subject_id ORDER BY avgs DESC LIMIT 0,5;

-- HAVING子句: 用於分組中的過濾條件
-- 1.查看每個科目老師的平均工資? 但是僅查看平均工資高於6000的那些！
SELECT subject_id,AVG(salary) avgs FROM teacher
GROUP BY subject_id HAVING avgs >6000;
-- 2.查看每個科目男老師的平均工資? 但是僅查看平均工資高於6000的那些！
SELECT subject_id,gender,AVG(salary) avgs FROM teacher
WHERE gender = '男' GROUP BY subject_id HAVING avgs > 6000;
-- 3.查看每個科目老師的平均工資，前提是該科目老師最高工資要超過9000!
SELECT subject_id,AVG(salary)  FROM teacher
GROUP BY subject_id HAVING MAX(salary)>9000;
-- 4.查看各科目男老師的平均工資是多少? 前提是該科目老師最低工資高於4000!
SELECT subject_id,AVG(salary) FROM teacher
WHERE gender = '男' GROUP BY subject_id HAVING MIN(salary)>4000;
-- 5.查看每個科目男老師的平均工資? 但是僅查看平均工資高於5000的那些!
-- 按照平均工資降序排列，每頁顯示2條數據，顯示第2頁的數據
SELECT subject_id,gender,AVG(salary) avgs FROM teacher
WHERE gender = '男'
GROUP BY subject_id
HAVING avgs > 5000
ORDER BY avgs DESC
LIMIT 2,2;