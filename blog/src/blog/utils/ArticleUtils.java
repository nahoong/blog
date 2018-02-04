package blog.utils;

import java.util.List;

import blog.model.Article;
import blog.model.AxisArticle;

/**
 * ?–‡ç« å¤„?†å·¥å…·
 *	
 */
public class ArticleUtils {

	/**
	 * å¤„ç†ä¸‹æ—¶?—´
	 * 
	 * @param list
	 * @return
	 */
	public static List cutTime(List<Article> list) {

		for (Article a : list) {
			a.setTime(a.getTime().substring(0, 11));
		}

		return list;
	}

	
	/**
	 * å¤„ç†ä¸‹æ—¶?—´
	 * 
	 * @param Article
	 * @return
	 */
	public static Article cutTime(Article a) {	
		a.setTime(a.getTime().substring(0, 11));
		return a;
	}
	
	
	
	
	/**
	 * å¤„ç†ä¸‹æ–‡ç« å†…å®?
	 * 
	 * @param list
	 * @return
	 */
	public static List cutContent(List<Article> list) {
		
		for (Article a : list) {
			if (a.getContent() != null && a.getContent().length() > 351) {
				a.setContent(a.getContent().substring(0, 349) + "...");
			}
		}
		return list;
	}
	
	
	
	/**
	 * ?·?–?—¶?—´è½´æ–‡ç« ç±»?‹ Article?š„ä¸?ä¸ªç??Œ–?‰ˆç±?
	 * @param article
	 * @return
	 */
	public static AxisArticle getAxisArticle(Article article) {

		AxisArticle axisArticle = new AxisArticle();
		
		axisArticle.setTitle(article.getTitle());
		axisArticle.setId(article.getId());
		
		//2017-09-20 21:27:14
		String year=StringUtils.cutString(article.getTime(),0,4);
		String month=StringUtils.cutString(article.getTime(),5,7);
		String day=StringUtils.cutString(article.getTime(),8,10);
		
		
		
		axisArticle.setYear(Integer.valueOf(year)) ;
		axisArticle.setMonth(Integer.valueOf(month)) ;
		axisArticle.setDay(Integer.valueOf(day)) ;
		
		
		return axisArticle;
	}
}
