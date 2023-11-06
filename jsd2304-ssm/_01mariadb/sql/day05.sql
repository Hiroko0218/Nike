-- 聯合查詢作業 ----------------
-- 1. 教語文的老師所帶班級各多少學生?
SELECT t.name,c.name, COUNT(*) number FROM teacher t,subject su,student s ,class c
WHERE s.class_id =c.id AND c.teacher_id =t.id AND t.subject_id =su.id
  AND su.name ='語文' GROUP BY t.name, c.name;
-- 2. 每門課的老師所帶班級各多少學生?
SELECT su.name,t.name,c.name,COUNT(*) FROM teacher t,subject su,student s ,class c
WHERE s.class_id =c.id AND c.teacher_id =t.id AND t.subject_id =su.id
GROUP BY su.name, t.name, c.name;
-- 3. 來自上海的學生的班主任都有誰?
SELECT DISTINCT t.name FROM teacher t,student s ,class c,location l
WHERE s.class_id =c.id AND c.teacher_id =t.id AND s.location_id=l.id
  AND l.name = '上海';
-- 4. 來自南京的學生共多少人
SELECT COUNT(*) FROM student s ,location l
WHERE s.location_id=l.id AND l.name = '南京';
-- 5. 來自武漢的男同學和女同學分別多少人?
SELECT s.gender,COUNT(*) number FROM student s ,location l
WHERE s.location_id=l.id AND l.name = '武漢' GROUP BY s.gender;
-- 6. 每個城市的學生各多少人
SELECT l.name, COUNT(*) number FROM location l, student s
WHERE s.location_id=l.id GROUP BY l.name;
-- 7. 高於平均工資的老師所帶的班級分別多少學生?
SELECT  t.name,c.name,COUNT(*) number FROM teacher t,student s ,class c
WHERE s.class_id =c.id AND c.teacher_id =t.id
  AND t.salary>(SELECT AVG(salary)FROM teacher)GROUP BY t.name, c.name;
-- 8. 每個老師班裏各多少來自鄭州的學生?
SELECT t.name,c.name,COUNT(*) number FROM teacher t,student s ,class c,location l
WHERE s.class_id =c.id AND c.teacher_id =t.id AND s.location_id=l.id
  AND l.name = '鄭州' GROUP BY t.name, c.name;

-- 多對多關係查詢作業 -------------------
-- 1. 查看1年級1班所有同學的語文成績是多少?
SELECT c.name,s.name,t.score FROM student s ,class c,t_stu_subject_score t,subject su
WHERE s.class_id =c.id AND t.stu_id =s.id AND t.subject_id =su.id
  AND c.name ='1年級1班' AND su.name = '語文';
-- 2. 統計1年級1班數學成績的平均值?
SELECT c.name,su.name,AVG(score) FROM student s ,class c,t_stu_subject_score t,subject su
WHERE s.class_id =c.id AND t.stu_id =s.id AND t.subject_id =su.id
  AND c.name ='1年級1班' AND su.name = '數學';
-- 3. 統計6年級的英語成績的平均值?
SELECT su.name,AVG(score) FROM student s ,class c,t_stu_subject_score t,subject su
WHERE s.class_id =c.id AND t.stu_id =s.id AND t.subject_id =su.id
  AND c.name LIKE '6年級%' AND su.name = '英語';
-- 4. 查看"劉蒼松"所帶班級的英語平均分?
SELECT t.name,su.name,AVG(score)  FROM class c, student s, t_stu_subject_score tss, subject su, teacher t
WHERE s.class_id=c.id AND tss.stu_id=s.id AND tss.subject_id=su.id AND c.teacher_id=t.id
  AND t.name ='劉蒼松' AND su.name = '英語';
-- 5. 查看工資最高的老師所帶班級的各科成績的平均分，最高分和最低分分別是多少?
SELECT t.name,c.name,su.name,AVG(tss.score),MAX(tss.score),MIN(tss.score)
FROM class c, student s, t_stu_subject_score tss, subject su, teacher t
WHERE s.class_id=c.id AND tss.stu_id=s.id AND tss.subject_id=su.id
  AND c.teacher_id=t.id
  AND t.salary=(SELECT MAX(salary)FROM teacher)GROUP BY su.name, c.name, t.name;
-- 6. 查看每位大隊長的5門成績平均分是多少？
SELECT s.name,s.job,AVG(tss.score) avg_score FROM student s, t_stu_subject_score tss, subject su
WHERE tss.stu_id=s.id AND tss.subject_id=su.id
  AND s.job='大隊長' GROUP BY s.name,s.job;


-- 內連接查詢----------------------------
-- 1. 查看1年級1班的學生信息?列出學生名字,年齡,所在班級
-- 關聯查詢寫法
SELECT s.name,s.age,c.name FROM class c, student s
WHERE s.class_id=c.id AND c.name='1年級1班';

-- 内連接查詢寫法
SELECT s.name,s.age,c.name
FROM class c
         JOIN student s ON c.id = s.class_id
WHERE c.name='1年級1班';

-- Ctrl + Alt + l  格式化SQL语句
-- 2.查看教英語的老師都有誰?
SELECT s.name,t.name
FROM teacher t
         JOIN subject s on s.id = t.subject_id
WHERE s.name ='英語';
-- 3.查看每個班級名以及對應的班主任名字?
SELECT c.name,t.name
FROM class c
         JOIN teacher t on t.id = c.teacher_id;
-- 4.查看王克晶所帶班級的女同學都有誰?(列出:老師名字，班級名字，學生名字，學生性別)
SELECT t.name,c.name,s.name,s.gender
FROM class c
         JOIN teacher t on t.id = c.teacher_id
         JOIN student s on c.id = s.class_id
WHERE t.name ='王克晶' AND s.gender='女';


-- 外連接
-- 查看所有班级信息和對應的班主任信息,如果該班没有班主任也要將班级信息列出来
-- 左外連接
SELECT c.name,t.name
FROM class c
         LEFT JOIN teacher t ON c.teacher_id = t.id;


-- 查看所有班級信息和對應的班主任信息，如果該老師不帶班,也要將老師信息列出來
-- 右外連接
SELECT c.name,t.name
FROM class c
         RIGHT JOIN teacher t ON c.teacher_id = t.id;


-- 自連接 ----------------------------------
-- 1. 查看'劉蒼松'的下屬都有誰?
-- t: 老師表
-- m: 領導表
SELECT t.name,t.manager,m.id,m.name
FROM teacher t, teacher m
WHERE t.manager=m.id
  AND m.name='劉蒼松';

SELECT t.name,m.name
FROM teacher t
         JOIN teacher m ON t.manager=m.id
WHERE m.name='劉蒼松';

-- 2.查看3年級2班的班長是誰?(student表中team_leader記錄班長的學生id)
SELECT c.name,s.name
FROM class c
         JOIN student s on c.id = s.class_id
WHERE s.team_leader=s.id
  AND c.name ='3年級2班';

SELECT * FROM class;
SELECT * FROM student WHERE class_id=8;