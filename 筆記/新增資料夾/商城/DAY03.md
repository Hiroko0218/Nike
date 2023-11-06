# 8. 關於創建數據表

## 8.1. 商品表

```mysql
CREATE TABLE mall_goods (
    id bigint unsigned auto_increment not null COMMENT '數據ID',
    category_id bigint unsigned DEFAULT 0 COMMENT '類別ID',
    category_name varchar(32) DEFAULT '' COMMENT '類別名稱',
    title varchar(255) DEFAULT '' COMMENT '標題',
    brief varchar(255) DEFAULT '' COMMENT '摘要',
    cover_url varchar(255) DEFAULT '' COMMENT '封面圖',
	sale_price decimal(10, 2) DEFAULT 0 COMMENT '價格',
    keywords varchar(255) DEFAULT '' COMMENT '關鍵詞列表',
	is_recommend tinyint unsigned DEFAULT 0 COMMENT '是否推薦',
    check_state tinyint unsigned DEFAULT 0 COMMENT '審核狀態',
    is_put_on tinyint unsigned DEFAULT 0 COMMENT '是否上架',
    sort tinyint unsigned DEFAULT 0 COMMENT '排序序號',
    gmt_create datetime default null COMMENT '數據創建時間',
    gmt_modified datetime default null COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商品' DEFAULT CHARSET = utf8mb4;
```

關於以上設計：

- 無論表中的數據量有沒有可能超過`int`的上限值，ID都使用`bigint`類型
- 關於整型數據的`unsigned`，表示此字段的數據是“無符號位的”，以`tinyint`（對應Java中的`byte`）為例，在有符號位時，取值區間為`[-128, 127]`，如果設置為無符號位，取值區間為`[0, 255]`，其實，許多整型字段添加`unsigned`更多的只是為了表示語義：“此字段的值不可能為負數”，並一定是為了得到更大的正數的取值區間
- 關於`varchar`類型的設置值，應該設計一個比你當下認為的上限值更大的值，但不要過份誇張，避免產生歧義
- 所有表示狀態的字段（例如：是否刪除、是否啟用、是否推薦，甚至包括審核狀態、訂單狀態等，甚至包括用戶的性別）都應該使用數值型的類型，通常使用`tinyint`就夠了，切忌使用字符類型來存儲狀態

- `DEFAULT CHARSET=???`和`CHARSET=???`是等效的
- 在當下使用較多的MySQL / MariaDB的版本中，`utf8`指的是`utf8mb3`（most bytes 3），即最多使用3個字節來記錄1個字符，可以表示絕大部分正常使用的字符，但是，某些特殊的生僻字、特殊符號、emoji表情等無法表示，而使用`utf8mb4`（most bytes 4）會最多使用4個字節來記錄1個字節，可以表示更多字符



## 附：數據庫中`char`與`varchar`的區別（面試題）

在數據庫中，`char`和`varchar`都是用於存儲較短的字符串類型的數據的（較長的字符串需要使用`text`）！

其中，`char`是定長（長度固定）的，例如，某個字段設計為`char(16)`，如果在此字段中存入`hello`（長度為5），則會自動補充11個空格再存入到此字段中，而`varchar`中變長（長度可變）的，例如，某個字段設計為`varchar(16)`，如果在此字段中存入`hello`，則只會將`hello`存入，占用5個字符對應的存儲空間！

注意：無論是`char`還是`varchar`，其設置值都是“字符數量”，具體占用多少字節，需要結合此字段的值的字符編碼來計算！

對於`varchar`類型的數據，默認情況下，數據庫會自動的額外使用1個字節來記錄“實際存入的字符數量”，例如在`varchar(16)`中存入了`hello`，則數據庫會自動的額外使用1個字節記錄下`5`這個值（`hello`的字符數量），後續，在讀取此字段的值時，會先讀取到這個`5`，然後從此字段讀`5`個字符出來，由於1個字節能表示的數字的上限是255，如果`varchar`被設計得更大，允許存放更多內容，例如設計為`varchar(2000)`，並且，實際存入的字符數量超過了255個，則數據庫會自動的改為使用2個字節來記錄實際存入的字符數量！

由於`char`是定長的，所以，並不需要使用額外的字節來記錄實際存入的字符數量，並且，在讀取數據時，直接按照`char`的設計值直接讀取數據即可！

所以，相比之下，`varchar`需要額外的消耗1~2個字節來記錄實際存入的字符數量，並且，在讀取時沒有`char`那麽便捷！