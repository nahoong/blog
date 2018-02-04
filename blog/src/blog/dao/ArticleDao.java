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
	 * æµè§ˆäº†æ–‡ç«? å¢åŠ ?–‡ç« çš„æµè§ˆæ¬¡æ•°
	 * 
	 * @param article_id
	 */
	void addVisit(int article_id);

	/**
	 * ?·?–ä¸Šä??–‡ç«? ?ˆ– ä¸‹ä??–‡ç«?
	 * 
	 * @param time
	 * @param less_or_more
	 * @return
	 */
	Article getANearArticle(String time, int less_or_more);

	/**
	 * ?ˆ†ç»„æŸä¸??ˆ—å±æ?? è®¡ç®—æ¯ä¸ªç»„çš„å¤§å° è¿”å›Map
	 * 
	 * @param search_column
	 * @return
	 */
	Map getColumAndCount(String search_column);

	/**
	 * è¿”å›???œ‰?š„ç±»åˆ«
	 * 
	 * @return
	 */
	List getAllSort();

	/**
	 * ?–°?š„?–‡ç«?
	 * 
	 * @param a
	 * @return
	 */
	Article addArticle(Article a);

	/**
	 * ?ˆ ?™¤?–‡ç«?
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteArticle(String id);

	/**
	 * ?·?–???œ‰?š„?–‡ç«?
	 * 
	 * @return
	 */
	List getAllArticle();

	/**
	 * ?·?–?˜…è¯»æ’è¡Œæ–‡ç« åˆ—è¡?
	 * 
	 * @return
	 */
	List getVisitRank();

	/**
	 * ?šè¿‡?Ÿä¸??ˆ—?Ÿ¥è¯¢æ–‡ç«?
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * ?·?–?–‡ç« çš„?•°?‡?ˆ–?…ç±»?ˆ«?š„?•°?‡
	 * 
	 * @param search_key
	 * @return
	 */
	int getCount(String search_key);

	/**
	 * ?‚¹èµäº†?–‡ç«?
	 * 
	 * @param id
	 * @return
	 */
	int star_article(int id);

	/**
	 * ?›´?–°äº†ç±»?ˆ«
	 * 
	 * @param old_sort
	 * @param new_sort
	 * @return
	 */
	boolean updateSort(String old_sort, String new_sort);

	/**
	 * ?ˆ ?™¤?ˆ†ç±»å’Œ?–‡ç«?
	 * 
	 * @param sort
	 * @return
	 */
	boolean delelteSort(String sort);

}