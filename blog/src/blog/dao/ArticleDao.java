package blog.dao;

import java.util.List;
import java.util.Map;

import blog.model.Article;

public interface ArticleDao {
	
	static final String SEARCH_ARTICLE = "article";
	static final String SEARCH_SORT = "blog_sort";

	static final int LESS = 1;
	static final int MORE = 2;

	/**
	 * 浏览了文�? 增加?��章的浏览次数
	 * 
	 * @param article_id
	 */
	void addVisit(int article_id);

	/**
	 * ?��?��上�??���? ?�� 下�??���?
	 * 
	 * @param time
	 * @param less_or_more
	 * @return
	 */
	Article getANearArticle(String time, int less_or_more);

	/**
	 * ?��组某�??��属�?? 计算每个组的大小 返回Map
	 * 
	 * @param search_column
	 * @return
	 */
	Map getColumAndCount(String search_column);

	/**
	 * 返回???��?��类别
	 * 
	 * @return
	 */
	List getAllSort();

	/**
	 * ?��?��?���?
	 * 
	 * @param a
	 * @return
	 */
	Article addArticle(Article a);

	/**
	 * ?��?��?���?
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteArticle(String id);

	/**
	 * ?��?��???��?��?���?
	 * 
	 * @return
	 */
	List getAllArticle();

	/**
	 * ?��?��?��读排行文章列�?
	 * 
	 * @return
	 */
	List getVisitRank();

	/**
	 * ?�过?���??��?��询文�?
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * ?��?��?��章的?��?��?��?�类?��?��?��?��
	 * 
	 * @param search_key
	 * @return
	 */
	int getCount(String search_key);

	/**
	 * ?��赞了?���?
	 * 
	 * @param id
	 * @return
	 */
	int star_article(int id);

	/**
	 * ?��?��了类?��
	 * 
	 * @param old_sort
	 * @param new_sort
	 * @return
	 */
	boolean updateSort(String old_sort, String new_sort);

	/**
	 * ?��?��?��类和?���?
	 * 
	 * @param sort
	 * @return
	 */
	boolean delelteSort(String sort);

}