package blog.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import blog.dao.ArticleDao;
import blog.dao.TagDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.daoImpl.TagDaoImpl;
import blog.db.DBUtil;
import blog.model.Article;
import blog.utils.FailException;
import blog.utils.Form2Bean;

public class AdminService {

	private ArticleDao adao;
	private TagDao tdao;
	private static AdminService instance;

	private AdminService() {
		adao = ArticleDaoImpl.getInstance();
		tdao = TagDaoImpl.getInstance();	
	}

	/**
	 * ?é∑?èñÂÆû‰æã
	 * 
	 * @return
	 */
	public static final AdminService getInstance() {
		if (instance == null) {
			try {
				instance = new AdminService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public Article addArticle(HttpServletRequest request){
		//?ñ∞Âª∫ÊñáÁ´?
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}		
		Article article=null ;
		try {
			article = Form2Bean.articleForm2Bean(request);
		} catch (FailException e) {
			e.printStackTrace();
		}		
		if(article==null) 
			return null;
		Article a = adao.addArticle(article);			
		if(a==null) return null;
		//Â¢ûÂä†?†áÁ≠?
		String str = request.getParameter("tags").trim();				
		String [] tags = str.split(" ");
		for(String tag:tags){
			tdao.addTag(a.getId(), tag);			
		}				
		return a;			
	}
	
	public Article updateArticle(HttpServletRequest request){
		//?é∑?èñ?óß?öÑ?ñáÁ´†id
		String old_id = request.getParameter("id");
		//?à†?ô§?óß?öÑ?ñáÁ´?
		this.delteArticle(old_id);		
				
		return this.addArticle(request);		
	}
	
	
	public Article getArticle(String article_id){
		 List<Article> list = adao.getArticleByColumn("id", article_id);
		 if(list.size()!=0){
			 return list.get(0);
		 }
		return null;
	}

	public boolean delteArticle(String id){
		return adao.deleteArticle(id);
	}
	
	public boolean updateSort(String old_sort,String new_sort){
		return adao.updateSort(old_sort, new_sort);		
	}	
	public boolean deleteSort(String sort){
		return adao.delelteSort(sort);		
	}
	
	
	public boolean updateTag(String old_tag,String new_tag){
		boolean result = tdao.updateTag(old_tag, new_tag);
		return result;
	}	
	public boolean deleteTag(String tag){
		//?à†?ô§???úâ?öÑ?†áÁ≠?
		boolean result = tdao.deleteTag(TagDao.DEFAULT_ID ,tag);
		return result;
	}


}
