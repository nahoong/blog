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
	 * æµè§äºæç«? å¢å ?ç« çæµè§æ¬¡æ°
	 * 
	 * @param article_id
	 */
	void addVisit(int article_id);

	/**
	 * ?·?ä¸ä??ç«? ? ä¸ä??ç«?
	 * 
	 * @param time
	 * @param less_or_more
	 * @return
	 */
	Article getANearArticle(String time, int less_or_more);

	/**
	 * ?ç»æä¸??å±æ?? è®¡ç®æ¯ä¸ªç»çå¤§å° è¿åMap
	 * 
	 * @param search_column
	 * @return
	 */
	Map getColumAndCount(String search_column);

	/**
	 * è¿å????ç±»å«
	 * 
	 * @return
	 */
	List getAllSort();

	/**
	 * ?°??ç«?
	 * 
	 * @param a
	 * @return
	 */
	Article addArticle(Article a);

	/**
	 * ? ?¤?ç«?
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteArticle(String id);

	/**
	 * ?·??????ç«?
	 * 
	 * @return
	 */
	List getAllArticle();

	/**
	 * ?·??è¯»æè¡æç« åè¡?
	 * 
	 * @return
	 */
	List getVisitRank();

	/**
	 * ?è¿?ä¸???¥è¯¢æç«?
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * ?·??ç« ç?°???ç±»?«??°?
	 * 
	 * @param search_key
	 * @return
	 */
	int getCount(String search_key);

	/**
	 * ?¹èµäº?ç«?
	 * 
	 * @param id
	 * @return
	 */
	int star_article(int id);

	/**
	 * ?´?°äºç±»?«
	 * 
	 * @param old_sort
	 * @param new_sort
	 * @return
	 */
	boolean updateSort(String old_sort, String new_sort);

	/**
	 * ? ?¤?ç±»å?ç«?
	 * 
	 * @param sort
	 * @return
	 */
	boolean delelteSort(String sort);

}