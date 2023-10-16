/*
*********************************************************************
市區字典數據表，原始數據：https://github.com/eduosi/district
*********************************************************************
當需要導入當前表數據時，可以：
- 登入MySQL客戶端，例如在終端下執行：mysql -u root -proot
- 切換到當前數據庫，例如：use tea_store
- 通過source命令直接導入當前腳本，例如：source d:/a.sql
或者：
- 在IntelliJ IDEA中打開此文件，對文件內容點右鍵，選擇：Run dict.sql
  然後，在彈出的對話框中選擇Database面板中配置的數據源，並執行即可
*/

DROP TABLE IF EXISTS `dict_district`;
CREATE TABLE `dict_district`
(
    id        BIGINT UNSIGNED AUTO_INCREMENT COMMENT '數據ID',
    parent_id BIGINT UNSIGNED DEFAULT 0 COMMENT '父級單位ID',
    code      VARCHAR(32)     DEFAULT '' COMMENT '行政代碼',
    name      VARCHAR(256)    DEFAULT '' COMMENT '名稱',
    suffix    VARCHAR(64)     DEFAULT '' COMMENT '名稱後綴',
    pinyin    VARCHAR(256)    DEFAULT '' COMMENT '名稱拼音',
    sort      INT             DEFAULT 0 COMMENT '排序序號',
    PRIMARY KEY (id)
) CHARSET = utf8mb4 COMMENT '市區字典';

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (1, 0, '63', '台北', '市', 'taibei', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (2, 0, '64', '高雄', '市', 'gaoxiong', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (3, 0, '10017', '基隆', '市', 'jilong', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (4, 0, '10019', '台中', '市', 'taizhong', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (5, 0, '10021', '台南', '市', 'tainan', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (6, 0, '10018', '新竹', '市', 'xinzhu', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (7, 0, '10020', '嘉義', '市', 'jiayi', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (8, 0, '', '新北', '市', 'xinbei', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (9, 0, '10002', '宜蘭', '縣', 'yilan', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (10, 0, '10003', '桃園', '縣', 'taoyuan', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (11, 0, '10004', '新竹', '縣', 'xinzhu', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (12, 0, '10005', '苗栗', '縣', 'miaoli', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (13, 0, '10007', '彰化', '縣', 'zhanghua', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (14, 0, '10008', '南投', '縣', 'nantou', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (15, 0, '10009', '雲林', '縣', 'yunlin', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (16, 0, '10020', '嘉義', '縣', 'jiayi', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (17, 0, '10013', '屏東', '縣', 'pingdong', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (18, 0, '10014', '台東', '縣', 'taidong', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (19, 0, '10015', '花蓮', '縣', 'hualian', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (20, 0, '10016', '澎湖', '縣', 'penghu', 20);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (21, 0, '', '連江', '縣', 'lianjiang', 21);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (22, 0, '', '金門', '縣', 'jinmen', 22);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (23, 1, '6300100', '松山', '區', 'songshan', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (24, 1, '6300200', '信義', '區', 'xinyi', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (25, 1, '6300300', '大安', '區', 'daan', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (26, 1, '6300400', '中山', '區', 'zhongshan', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (27, 1, '630016', '中正', '區', 'zhongzheng', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (28, 1, '6300600', '大同', '區', 'datong', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (29, 1, '6300700', '萬華', '區', 'wanhua', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (30, 1, '6300800', '文山', '區', 'wenshan', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (31, 1, '6300900', '南港', '區', 'nangang', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (32, 1, '6301000', '內湖', '區', 'neihu', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (33, 1, '6301100', '士林', '區', 'shilin', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (34, 1, '6301200', '北投', '區', 'beitou', 12);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (35, 2, '6400100', '鹽埕', '區', 'yancheng', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (36, 2, '6400200', '鼓山', '區', 'gushan', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (37, 2, '6400300', '左營', '區', 'zuoying', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (38, 2, '6400400', '楠梓', '區', 'nanzi', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (39, 2, '640016', '三民', '區', 'sanmin', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (40, 2, '6400600', '新興', '區', 'xinxing', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (41, 2, '6400700', '前金', '區', 'qianjin', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (42, 2, '6400800', '苓雅', '區', 'lingya', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (43, 2, '6400900', '前鎮', '區', 'qianzhen', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (44, 2, '6401000', '旗津', '區', 'qijin', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (45, 2, '6401100', '小港', '區', 'xiaogang', 11);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (46, 3, '1001701', '中正', '區', 'zhongzheng', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (47, 3, '1001702', '七堵', '區', 'qidu', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (48, 3, '1001703', '暖暖', '區', 'nuannuan', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (49, 3, '1001704', '仁愛', '區', 'renai', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (50, 3, '1001705', '中山', '區', 'zhongshan', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (51, 3, '1001706', '安樂', '區', 'anle', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (52, 3, '1001707', '信義', '區', 'xinyi', 7);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (53, 4, '', '大安', '區', 'daan', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (54, 4, '', '神岡', '區', 'shengang', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (55, 4, '', '石岡', '區', 'shigang', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (56, 4, '', '東勢', '區', 'dongshi', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (57, 4, '', '新社', '區', 'xinshe', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (58, 4, '', '和平', '區', 'heping', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (59, 4, '', '大肚', '區', 'dadu', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (60, 4, '', '沙鹿', '區', 'shalu', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (61, 4, '', '龍井', '區', 'longjing', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (62, 4, '', '梧棲', '區', 'wuqi', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (63, 4, '', '清水', '區', 'qingshui', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (64, 4, '', '大甲', '區', 'dajia', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (65, 4, '', '外埔', '區', 'waipu', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (66, 4, '', '大雅', '區', 'daya', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (67, 4, '', '潭子', '區', 'tanzi', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (68, 4, '', '後里', '區', 'houli', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (69, 4, '', '豐原', '區', 'fengyuan', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (70, 4, '', '烏日', '區', 'wuri', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (71, 4, '', '霧峰', '區', 'wufeng', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (72, 4, '', '大里', '區', 'dali', 20);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (73, 4, '', '太平', '區', 'taiping', 21);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (74, 4, '1001901', '中區', '', 'zhongqu', 22);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (75, 4, '1001902', '東區', '', 'dongqu', 23);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (76, 4, '1001903', '南區', '', 'nanqu', 24);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (77, 4, '1001904', '西區', '', 'xiqu', 25);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (78, 4, '1001905', '北區', '', 'beiqu', 26);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (79, 4, '1001906', '西屯', '區', 'xitun', 27);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (80, 4, '1001907', '南屯', '區', 'nantun', 28);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (81, 4, '1001908', '北屯', '區', 'beitun', 29);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (82, 5, '1002101', '東區', '', 'dongqu', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (83, 5, '1002102', '南區', '', 'nanqu', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (84, 5, '1002104', '北區', '', 'beiqu', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (85, 5, '1002106', '安南', '區', 'annan', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (86, 5, '1002107', '安平', '區', 'anping', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (87, 5, '1002108', '中西', '區', 'zhongxi', 6);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (88, 6, '1001801', '東區', '', 'dongqu', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (89, 6, '1001802', '北區', '', 'beiqu', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (90, 6, '1001803', '香山', '區', 'xiangshan', 3);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (91, 7, '1002001', '東區', '', 'dongqu', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (92, 7, '1002002', '西區', '', 'xiqu', 2);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (93, 8, '', '板橋', '區', 'banqiao', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (94, 8, '', '瑞芳', '區', 'ruifang', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (95, 8, '', '八里', '區', 'bali', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (96, 8, '', '深坑', '區', 'shenkeng', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (97, 8, '', '三芝', '區', 'sanzhi', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (98, 8, '', '金山', '區', 'jinshan', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (99, 8, '', '萬里', '區', 'wanli', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (100, 8, '', '貢寮', '區', 'gongliao', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (101, 8, '', '石門', '區', 'shimen', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (102, 8, '', '雙溪', '區', 'shuangxi', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (103, 8, '', '石碇', '區', 'shiding', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (104, 8, '', '坪林', '區', 'pinglin', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (105, 8, '', '烏來', '區', 'wulai', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (106, 8, '', '泰山', '區', 'taishan', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (107, 8, '', '五股', '區', 'wugu', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (108, 8, '', '鶯歌', '區', 'yingge', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (109, 8, '', '中和', '區', 'zhonghe', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (110, 8, '', '新莊', '區', 'xinzhuang', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (111, 8, '', '三重', '區', 'sanzhong', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (112, 8, '', '新店', '區', 'xindian', 20);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (113, 8, '', '土城', '區', 'tucheng', 21);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (114, 8, '', '永和', '區', 'yonghe', 22);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (115, 8, '', '蘆洲', '區', 'luzhou', 23);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (116, 8, '', '汐止', '區', 'xizhi', 24);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (117, 8, '', '樹林', '區', 'shulin', 25);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (118, 8, '', '淡水', '區', 'danshui', 26);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (119, 8, '', '三峽', '區', 'sanxia', 27);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (120, 8, '', '林口', '區', 'linkou', 28);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (121, 8, '', '平溪', '區', 'pingxi', 29);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (122, 9, '1000201', '宜蘭', '市', 'yilan', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (123, 9, '1000202', '羅東', '鎮', 'luodong', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (124, 9, '1000203', '蘇澳', '鎮', 'suao', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (125, 9, '1000204', '頭城', '鄉', 'toucheng', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (126, 9, '1000205', '礁溪', '鄉', 'jiaoxi', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (127, 9, '1000206', '壯圍', '鄉', 'zhuangwei', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (128, 9, '1000207', '員山', '鄉', 'yuanshan', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (129, 9, '1000208', '冬山', '鄉', 'dongshan', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (130, 9, '1000209', '五結', '鄉', 'wujie', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (131, 9, '1000210', '三星', '鄉', 'sanxing', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (132, 9, '1000211', '大同', '鄉', 'datong', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (133, 9, '1000212', '南澳', '鄉', 'nanao', 12);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (134, 10, '1000301', '桃園', '市', 'taoyuan', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (135, 10, '1000302', '中壢', '市', 'zhongli', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (136, 10, '1000303', '大溪', '鎮', 'daxi', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (137, 10, '1000304', '楊梅', '鎮', 'yangmei', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (138, 10, '1000305', '蘆竹', '鄉', 'luzhu', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (139, 10, '1000306', '大園', '鄉', 'dayuan', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (140, 10, '1000307', '龜山', '鄉', 'guishan', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (141, 10, '1000308', '八德', '市', 'bade', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (142, 10, '1000309', '龍潭', '鄉', 'longtan', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (143, 10, '1000310', '平鎮', '市', 'pingzhen', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (144, 10, '1000311', '新屋', '鄉', 'xinwu', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (145, 10, '1000312', '觀音', '鄉', 'guanyin', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (146, 10, '1000313', '覆興', '鄉', 'fuxing', 13);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (147, 11, '1000401', '竹北', '市', 'zhubei', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (148, 11, '1000402', '竹東', '鎮', 'zhudong', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (149, 11, '1000403', '新埔', '鎮', 'xinpu', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (150, 11, '1000404', '關西', '鎮', 'guanxi', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (151, 11, '1000405', '湖口', '鄉', 'hukou', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (152, 11, '1000406', '新豐', '鄉', 'xinfeng', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (153, 11, '1000407', '芎林', '鄉', 'xionglin', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (154, 11, '1000408', '橫山', '鄉', 'hengshan', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (155, 11, '1000409', '北埔', '鄉', 'beipu', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (156, 11, '1000410', '寶山', '鄉', 'baoshan', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (157, 11, '1000411', '峨眉', '鄉', 'emei', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (158, 11, '1000412', '尖石', '鄉', 'jianshi', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (159, 11, '1000413', '五峰', '鄉', 'wufeng', 13);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (160, 12, '100017', '苗栗', '市', 'miaoli', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (161, 12, '100018', '苑里', '鎮', 'yuanli', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (162, 12, '100019', '通霄', '鎮', 'tongxiao', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (163, 12, '100020', '竹南', '鎮', 'zhunan', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (164, 12, '1000505', '頭份', '鎮', 'toufen', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (165, 12, '1000506', '後龍', '鎮', 'houlong', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (166, 12, '1000507', '卓蘭', '鎮', 'zhuolan', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (167, 12, '1000508', '大湖', '鄉', 'dahu', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (168, 12, '1000509', '公館', '鄉', 'gongguan', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (169, 12, '1000510', '銅鑼', '鄉', 'tongluo', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (170, 12, '1000511', '南莊', '鄉', 'nanzhuang', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (171, 12, '1000512', '頭屋', '鄉', 'touwu', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (172, 12, '1000513', '三義', '鄉', 'sanyi', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (173, 12, '1000514', '西湖', '鄉', 'xihu', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (174, 12, '1000515', '造橋', '鄉', 'zaoqiao', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (175, 12, '1000516', '三灣', '鄉', 'sanwan', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (176, 12, '1000517', '獅潭', '鄉', 'shitan', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (177, 12, '1000518', '泰安', '鄉', 'taian', 18);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (178, 13, '1000701', '彰化', '市', 'zhanghua', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (179, 13, '1000702', '鹿港', '鎮', 'lugang', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (180, 13, '1000703', '和美', '鎮', 'hemei', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (181, 13, '1000704', '線西', '鄉', 'xianxi', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (182, 13, '1000705', '伸港', '鄉', 'shengang', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (183, 13, '1000706', '福興', '鄉', 'fuxing', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (184, 13, '1000707', '秀水', '鄉', 'xiushui', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (185, 13, '1000708', '花壇', '鄉', 'huatan', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (186, 13, '1000709', '芬園', '鄉', 'fenyuan', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (187, 13, '1000710', '員林', '鎮', 'yuanlin', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (188, 13, '1000711', '溪湖', '鎮', 'xihu', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (189, 13, '1000712', '田中', '鎮', 'tianzhong', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (190, 13, '1000713', '大村', '鄉', 'dacun', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (191, 13, '1000714', '埔鹽', '鄉', 'puyan', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (192, 13, '1000715', '埔心', '鄉', 'puxin', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (193, 13, '1000716', '永靖', '鄉', 'yongjing', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (194, 13, '1000717', '社頭', '鄉', 'shetou', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (195, 13, '1000718', '二水', '鄉', 'ershui', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (196, 13, '1000719', '北鬥', '鎮', 'beidou', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (197, 13, '1000720', '二林', '鎮', 'erlin', 20);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (198, 13, '1000721', '田尾', '鄉', 'tianwei', 21);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (199, 13, '1000722', '埤頭', '鄉', 'pitou', 22);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (200, 13, '1000723', '芳苑', '鄉', 'fangyuan', 23);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (201, 13, '1000724', '大城', '鄉', 'dacheng', 24);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (202, 13, '1000725', '竹塘', '鄉', 'zhutang', 25);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (203, 13, '1000726', '溪州', '鄉', 'xizhou', 26);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (204, 14, '1000801', '南投', '市', 'nantou', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (205, 14, '1000802', '南投', '鎮', 'nantou', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (206, 14, '1000803', '草屯', '鎮', 'caotun', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (207, 14, '1000804', '竹山', '鎮', 'zhushan', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (208, 14, '1000805', '集集', '鎮', 'jiji', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (209, 14, '1000806', '名間', '鄉', 'mingjian', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (210, 14, '1000807', '鹿谷', '鄉', 'lugu', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (211, 14, '1000808', '中寮', '鄉', 'zhongliao', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (212, 14, '1000809', '魚池', '鄉', 'yuchi', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (213, 14, '1000810', '國姓', '鄉', 'guoxing', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (214, 14, '1000811', '水里', '鄉', 'shuili', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (215, 14, '1000812', '信義', '鄉', 'xinyi', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (216, 14, '1000813', '仁愛', '鄉', 'renai', 13);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (217, 15, '1000901', '鬥六', '市', 'douliu', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (218, 15, '1000902', '鬥南', '鎮', 'dounan', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (219, 15, '1000903', '虎尾', '鎮', 'huwei', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (220, 15, '1000904', '西螺', '鎮', 'xiluo', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (221, 15, '1000905', '土庫', '鎮', 'tuku', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (222, 15, '1000906', '北港', '鎮', 'beigang', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (223, 15, '1000907', '古坑', '鄉', 'gukeng', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (224, 15, '1000908', '大埤', '鄉', 'dapi', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (225, 15, '1000909', '莿桐', '鄉', 'citong', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (226, 15, '1000910', '林內', '鄉', 'linnei', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (227, 15, '1000911', '二侖', '鄉', 'erlun', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (228, 15, '1000912', '侖背', '鄉', 'lunbei', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (229, 15, '1000913', '麥寮', '鄉', 'mailiao', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (230, 15, '1000914', '東勢', '鄉', 'dongshi', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (231, 15, '1000915', '褒忠', '鄉', 'baozhong', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (232, 15, '1000916', '台西', '鄉', 'taixi', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (233, 15, '1000917', '元長', '鄉', 'yuanchang', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (234, 15, '1000918', '四湖', '鄉', 'sihu', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (235, 15, '1000919', '口湖', '鄉', 'kouhu', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (236, 15, '1000920', '水林', '鄉', 'shuilin', 20);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (237, 16, '', '太保', '市', 'taibao', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (238, 16, '', '樸子', '市', 'pozi', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (239, 16, '', '布袋', '鎮', 'budai', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (240, 16, '10010', '大林', '鎮', 'dalin', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (241, 16, '1001001', '民雄', '鄉', 'minxiong', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (242, 16, '1001002', '溪口', '鄉', 'xikou', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (243, 16, '1001003', '新港', '鄉', 'xingang', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (244, 16, '1001004', '六腳', '鄉', 'liujiao', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (245, 16, '1001005', '東石', '鄉', 'dongshi', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (246, 16, '1001006', '義竹', '鄉', 'yizhu', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (247, 16, '1001007', '鹿草', '鄉', 'lucao', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (248, 16, '1001008', '水上', '鄉', 'shuishang', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (249, 16, '1001009', '中埔', '鄉', 'zhongpu', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (250, 16, '1001010', '竹崎', '鄉', 'zhuqi', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (251, 16, '1001011', '梅山', '鄉', 'meishan', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (252, 16, '1001012', '番路', '鄉', 'fanlu', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (253, 16, '1001013', '大埔', '鄉', 'dapu', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (254, 16, '1001014', '阿里山', '鄉', 'alishan', 18);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (255, 17, '1001301', '屏東', '市', 'pingdong', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (256, 17, '1001302', '潮州', '鎮', 'chaozhou', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (257, 17, '1001303', '東港', '鎮', 'donggang', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (258, 17, '1001304', '恒春', '鎮', 'hengchun', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (259, 17, '1001305', '萬丹', '鄉', 'wandan', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (260, 17, '1001306', '長治', '鄉', 'changzhi', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (261, 17, '1001307', '麟洛', '鄉', 'linluo', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (262, 17, '1001308', '九如', '鄉', 'jiuru', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (263, 17, '1001309', '里港', '鄉', 'ligang', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (264, 17, '1001310', '鹽埔', '鄉', 'yanpu', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (265, 17, '1001311', '高樹', '鄉', 'gaoshu', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (266, 17, '1001312', '萬巒', '鄉', 'wanluan', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (267, 17, '1001313', '內埔', '鄉', 'neipu', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (268, 17, '1001314', '竹田', '鄉', 'zhutian', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (269, 17, '1001315', '新埤', '鄉', 'xinpi', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (270, 17, '1001316', '枋寮', '鄉', 'fangliao', 16);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (271, 17, '1001317', '新園', '鄉', 'xinyuan', 17);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (272, 17, '1001318', '崁讚', '鄉', 'kanding', 18);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (273, 17, '1001319', '林邊', '鄉', 'linbian', 19);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (274, 17, '1001320', '南州', '鄉', 'nanzhou', 20);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (275, 17, '1001321', '佳冬', '鄉', 'jiadong', 21);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (276, 17, '1001322', '琉球', '鄉', 'liuqiu', 22);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (277, 17, '1001323', '車城', '鄉', 'checheng', 23);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (278, 17, '1001324', '滿州', '鄉', 'manzhou', 24);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (279, 17, '1001325', '枋山', '鄉', 'fangshan', 25);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (280, 17, '1001326', '三地門', '鄉', 'sandimen', 26);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (281, 17, '1001327', '霧台', '鄉', 'wutai', 27);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (282, 17, '1001328', '瑪家', '鄉', 'majia', 28);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (283, 17, '1001329', '泰武', '鄉', 'taiwu', 29);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (284, 17, '10010', '來義', '鄉', 'laiyi', 30);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (285, 17, '10011', '春日', '鄉', 'chunri', 31);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (286, 17, '10012', '獅子', '鄉', 'shizi', 32);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (287, 17, '10013', '牡丹', '鄉', 'mudan',33 );

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (288, 18, '', '卑南', '鄉', 'beinan', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (289, 18, '1001401', '台東', '市', 'taidong', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (290, 18, '1001402', '成功', '鎮', 'chenggong', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (291, 18, '1001403', '關山', '鎮', 'guanshan', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (292, 18, '1001405', '鹿野', '鄉', 'luye', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (293, 18, '1001406', '池上', '鄉', 'chishang', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (294, 18, '1001407', '東河', '鄉', 'donghe', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (295, 18, '1001408', '長濱', '鄉', 'changbin', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (296, 18, '1001409', '太麻里', '鄉', 'taimali', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (297, 18, '1001410', '大武', '鄉', 'dawu', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (298, 18, '1001411', '綠島', '鄉', 'lu:dao', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (299, 18, '1001412', '海端', '鄉', 'haiduan', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (300, 18, '1001413', '延平', '鄉', 'yanping', 13);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (301, 18, '1001414', '金峰', '鄉', 'jinfeng', 14);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (302, 18, '1001415', '達仁', '鄉', 'daren', 15);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (303, 18, '1001416', '蘭嶼', '鄉', 'lanyu', 16);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (304, 19, '1001501', '花蓮', '市', 'hualian', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (305, 19, '1001502', '鳳林', '鎮', 'fenglin', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (306, 19, '1001503', '玉里', '鎮', 'yuli', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (307, 19, '1001504', '新城', '鄉', 'xincheng', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (308, 19, '1001505', '吉安', '鄉', 'jian', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (309, 19, '1001506', '壽豐', '鄉', 'shoufeng', 6);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (310, 19, '1001507', '光覆', '鄉', 'guangfu', 7);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (311, 19, '1001508', '豐濱', '鄉', 'fengbin', 8);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (312, 19, '1001509', '瑞穗', '鄉', 'ruisui', 9);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (313, 19, '1001510', '富里', '鄉', 'fuli', 10);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (314, 19, '1001511', '秀林', '鄉', 'xiulin', 11);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (315, 19, '1001512', '萬榮', '鄉', 'wanrong', 12);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (316, 19, '1001513', '卓溪', '鄉', 'zhuoxi', 13);

INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (317, 20, '1001601', '馬公', '市', 'magong', 1);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (318, 20, '1001602', '湖西', '鄉', 'huxi', 2);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (319, 20, '1001603', '白沙', '鄉', 'baisha', 3);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (320, 20, '1001604', '西嶼', '鄉', 'xiyu', 4);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (321, 20, '1001605', '望安', '鄉', 'wangan', 5);
INSERT INTO dict_district (id, parent_id, code, name, suffix, pinyin, sort) VALUES (322, 20, '1001606', '七美', '鄉', 'qimei', 6);
