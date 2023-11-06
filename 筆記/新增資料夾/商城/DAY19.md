# 基於Spring Boot的Elasticsearch編程

## 添加依賴

在Spring Boot中，實現Elasticsearch編程需要添加依賴：`spring-boot-starter-data-elasticsearch`

## 定義文檔數據的Java類

注意：與ES中的文檔對應的Java類，並不需要與實體類、用於Redis的相關類保持一致！

使用ES時的Java類可以完全自由設計，只需要包含顯示在“搜索結果中的屬性（最終顯示在客戶端界面中的屬性）”及“執行搜索時需要匹配的屬性”即可。

```java
@Data
public class GoodsSearchVO implements Serializable {
	private Long id;
    private String categoryName;
    private String title;
    private String brief;
    private String coverUrl;
    private BigDecimal salePrice;
    private String keywords;
    private Integer sort;
    private Integer isRecommend;
    private Integer salesCount;
    private Integer commentCount;
    private Integer positiveCommentCount;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
```



## 編寫數據訪問接口

在Spring Boot項目中添加了Elasticsearch編程的依賴項後，只需要自定義接口，繼承自框架中的`Repository`接口即可表示自定義接口是用於訪問數據的，`Repository`接口需要指定2個泛型，分別是你要操作的數據的類型，和此數據在ES中的ID的類型。
