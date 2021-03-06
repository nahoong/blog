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
	 * ?ท?? ็ญพๅๅฎๆ?? ่ฎฐ็?็ซ?
	 * @return
	 */
	public Map getTagAndArticle(String tag_name){
		
		ArticleService as =  ArticleService.getInstance();		
		Map map =  new HashMap();		
		
		List<Tag>  tag_list ;
		if(tag_name.equals("all") || StringUtils.isEmpty(tag_name)){		
			//?ท????ไธ้ๅค็? ็ญ?
			tag_list = dao.getAllTag();			
		}else{
			//?ท?่ฟไธช? ็ญ?
			tag_list = dao.getTagByColumn("blog_tag", tag_name);
		}			
		
		//?่ฟไธช? ็ญพ็?็ซ?
		List<Article> article_list = null;		
		//?ท?่ฟไธช? ็ญพ็????็ซ? id
		for(Tag tag : tag_list){						
			List<Tag> list = dao.getTagByColumn("blog_tag",tag.getTag());				
			article_list = new ArrayList();						
			//?ฅ่ฏขๆ???็ซ? id ?พ?จarticle_list
			for( Tag tag_all : list){
				//่ฟไธช???ช?ไธ?ไธชๅ็ด?
				List<Article> result = as.getArticle("blog_id", String.valueOf(  tag_all.getId() )  ) ;				
				article_list.add( ArticleUtils.cutTime(result.get(0)) );
			}
			//่ฟๅ? ็ญพ็?ๅฎ?+? ็ญพๆ ่ฎฐ็????็ซ ้?
			map.put(tag.getTag(), article_list);		
		}			
		return map;
	}	
}
