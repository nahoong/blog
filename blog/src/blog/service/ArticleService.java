package blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import blog.dao.ArticleDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.model.Article;
import blog.model.AxisArticle;
import blog.utils.ArticleUtils;
import blog.utils.StringUtils;

public class ArticleService {

	private ArticleDao dao;

	private static ArticleService instance;
	
	private  ArticleService() {
		dao =  ArticleDaoImpl.getInstance();
	}

	/**
	 *
	 * 
	 * @return
	 */
	public static final ArticleService getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public Article  getPreviousArticle(String time){		
		return dao.getANearArticle(time, dao.LESS);		
	}
	/**
	 * 
	 * @param time
	 * @return
	 */
	public Article  getNextArticle(String time){		
		return dao.getANearArticle(time, dao.MORE);		
	}
	
	/**
	 * 
	 * @param search_key
	 * @return
	 */
	public int getCount(String search_key) {
		return dao.getCount(search_key);
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List getAxisList() {
	
		List<Article> articles = dao.getAllArticle();
		
		List<AxisArticle> axis_list = new ArrayList();		
		//Article->AxisArticle
		for (Article a : articles) {
			AxisArticle at = ArticleUtils.getAxisArticle(a);			
			axis_list.add(at);
		}
		
		AxisArticle tmp = null;
		List result =  new LinkedList();			
		
		if( ! axis_list.isEmpty()) {
			tmp = new AxisArticle();
			tmp.setId(0);
			tmp.setYear( axis_list.get(0).getYear());
			result.add(tmp);	
			result.add(axis_list.get(0));
		}
				
		for( int i=1;i<axis_list.size();i++){
			int present_year = axis_list.get(i).getYear();
			int past_year = axis_list.get(i-1).getYear();
			
			if( present_year < past_year){				
				tmp = new AxisArticle();
				tmp.setId(0);
				tmp.setYear(present_year);
				result.add(tmp);		
			}
			result.add(axis_list.get(i));			
		}					
				
		return result;
	}

	/**
	 * 
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public List<Article> getArticle(String column, String value) {
		return dao.getArticleByColumn(column, value);
	}
	/**
	 * 
	 * 
	 * @return	 
	 */
	public Map getSortAndCount() {
		// TO DO
		// 
		return dao.getColumAndCount(dao.SEARCH_SORT);
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List getArticle() {
		List<Article> list = dao.getAllArticle();
		for (Article a : list) {
			ArticleUtils.cutContent(list);
			ArticleUtils.cutTime(list);
		}
		return list;
	}

	/**
	 * ?é∑?èñ?àÜÁ±ªÂíåÂÆÉÁöÑ?ñáÁ´?
	 * 
	 * @return
	 */
	public Map getSortAndAirticle(String sort_name) {

		Map map = new HashMap();
		List<Article> articleBySort = null;

		
		if (sort_name.equals("all") || StringUtils.isEmpty(sort_name)) {
			List<String> sorts = dao.getAllSort();

			for (int i = 0; i < sorts.size(); i++) {
				String sort = sorts.get(i);
				articleBySort = dao.getArticleByColumn("blog_sort", sort);
				ArticleUtils.cutTime(articleBySort);
				map.put(sort, articleBySort);
			}
		} else {
			
			articleBySort = dao.getArticleByColumn("blog_sort", sort_name);
			ArticleUtils.cutTime(articleBySort);
			map.put(sort_name, articleBySort);
		}
		return map;
	}
	
	public List getVisitRank(){
		List list = dao.getVisitRank();
		if(list.size()>10){
			for(int i = 10;i<list.size();i++){
				list.remove(i);
			}			
		}
		for(int y=0;y<list.size();y++){
			
			Article a = (Article) list.get(y);
			if(a.getTitle().length()>20){
				a.setTitle(StringUtils.cutString(a.getTitle(), 0, 19)+"...");
			}
			
		}
		
		return list;
		
	}
	public List getAllSort(){
		return dao.getAllSort();		
	}
	public int starArticle(int id){
		return dao.star_article(id);
	}
	public  void addVisit(int article_id){
		dao.addVisit(article_id);
	}

}
