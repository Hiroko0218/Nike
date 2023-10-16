package com.yafeng.nike.admin.content.dao.search;

import com.yafeng.nike.admin.content.pojo.vo.search.ArticleSearchVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 處理文章搜索的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface IArticleSearchRepository
        extends ElasticsearchRepository<ArticleSearchVO, Long> {

}
