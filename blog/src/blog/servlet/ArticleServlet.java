package blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Article;
import blog.service.ArticleService;
import blog.service.CommentService;
import blog.service.TagService;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//?ƒ³è¦è·?–?š„?–‡ç«? id
		
		String id= request.getParameter("id");
		
		ArticleService as =  ArticleService.getInstance();		
		//?–‡ç«?
		Article a = as.getArticle("blog_id",id).get(0);
		request.setAttribute("article",a);		
		
		//?–‡ç« çš„???œ‰? ‡ç­?
		TagService ts = TagService.getInstance();
		request.setAttribute("article_tags",ts.getTagById(id));
		//?·?–ä¸Šä?ç¯‡æ–‡ç«?
		request.setAttribute("article_pre", as.getPreviousArticle(a.getTime()));	
		//?·?–ä¸‹ä?ç¯‡æ–‡ç«?
		System.out.println("ArticleServlet ¸¦ÅëÇØ dbÁ¢¼Ó Á¤º¸ ¹Ş¾Æ¿À±â ¹Ş¾Æ¿Â °ª  : " + id + " / ±Û¾´ ½Ã°£ : " +a.getTime());
		request.setAttribute("article_next", as.getNextArticle(a.getTime()));		
		//?Š è½½æ–‡ç« è¯„è®?
		CommentService  cs = CommentService.getInstance();
		request.setAttribute("comment",cs.loadComment(a.getId()));
		
		request.getRequestDispatcher("/page/article.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
