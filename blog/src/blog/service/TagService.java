package blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import blog.dao.TagDao;
import blog.daoImpl.TagDaoImpl;
import blog.model.Article;
import blog.model.Tag;
import blog.utils.ArticleUtils;
import blog.utils.StringUtils;

/**
 * TO web
 */
public class TagService {

	
	private TagDao dao ;
	
	private static TagService instance;
	
	
	private TagService(){
		dao = TagDaoImpl.getInstance();	
	}
	
	public static final TagService getInstance(){
		if (instance == null) {
			try {
				instance = new TagService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	public List getTagById(String id){
			return dao.getTagByColumn("blog_id",id);		
	}	
	
	public List getAllTag(){
		return dao.getAllTag();
	}

	public int getTagCount(){			
		return dao.getAllTag().size();
	}
	/**
	 * ?·?–? ‡ç­¾å’Œå®ƒæ?? ‡è®°çš„?–‡ç«?
	 * @return
	 */
	public Map getTagAndArticle(String tag_name){
		
		ArticleService as =  ArticleService.getInstance();		
		Map map =  new HashMap();		
		
		List<Tag>  tag_list ;
		if(tag_name.equals("all") || StringUtils.isEmpty(tag_name)){		
			//?·?–???œ‰ä¸é‡å¤çš„? ‡ç­?
			tag_list = dao.getAllTag();			
		}else{
			//?·?–è¿™ä¸ª? ‡ç­?
			tag_list = dao.getTagByColumn("blog_tag", tag_name);
		}			
		
		//?œ‰è¿™ä¸ª? ‡ç­¾çš„?–‡ç«?
		List<Article> article_list = null;		
		//?·?–è¿™ä¸ª? ‡ç­¾çš„???œ‰?–‡ç«? id
		for(Tag tag : tag_list){						
			List<Tag> list = dao.getTagByColumn("blog_tag",tag.getTag());				
			article_list = new ArrayList();						
			//?Ÿ¥è¯¢æ??œ‰?–‡ç«? id ?”¾?œ¨article_list
			for( Tag tag_all : list){
				//è¿™ä¸ª?›†?ˆ?ª?œ‰ä¸?ä¸ªå…ƒç´?
				List<Article> result = as.getArticle("blog_id", String.valueOf(  tag_all.getId() )  ) ;				
				article_list.add( ArticleUtils.cutTime(result.get(0)) );
			}
			//è¿”å›? ‡ç­¾çš„?†…å®?+? ‡ç­¾æ ‡è®°çš„???œ‰?–‡ç« é›†?ˆ
			map.put(tag.getTag(), article_list);		
		}			
		return map;
	}	
}
