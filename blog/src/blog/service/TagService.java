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
	 * ?��?��?��签和它�??��记的?���?
	 * @return
	 */
	public Map getTagAndArticle(String tag_name){
		
		ArticleService as =  ArticleService.getInstance();		
		Map map =  new HashMap();		
		
		List<Tag>  tag_list ;
		if(tag_name.equals("all") || StringUtils.isEmpty(tag_name)){		
			//?��?��???��不重复的?���?
			tag_list = dao.getAllTag();			
		}else{
			//?��?��这个?���?
			tag_list = dao.getTagByColumn("blog_tag", tag_name);
		}			
		
		//?��这个?��签的?���?
		List<Article> article_list = null;		
		//?��?��这个?��签的???��?���? id
		for(Tag tag : tag_list){						
			List<Tag> list = dao.getTagByColumn("blog_tag",tag.getTag());				
			article_list = new ArrayList();						
			//?��询�??��?���? id ?��?��article_list
			for( Tag tag_all : list){
				//这个?��?��?��?���?个元�?
				List<Article> result = as.getArticle("blog_id", String.valueOf(  tag_all.getId() )  ) ;				
				article_list.add( ArticleUtils.cutTime(result.get(0)) );
			}
			//返回?��签的?���?+?��签标记的???��?��章集?��
			map.put(tag.getTag(), article_list);		
		}			
		return map;
	}	
}
