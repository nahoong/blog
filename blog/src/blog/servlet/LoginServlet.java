package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.ArticleDao;
import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.db.VisitorDB;
import blog.model.Article;
import blog.model.User;
import blog.service.ArticleService;
import blog.service.TagService;
import blog.utils.LoginUtils;

/**
 * Login->index.jsp->init data
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8386271655057731963L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//HttpSession session = request.getSession(true); //세션만들기
		LoginUtils.login(request);
		
		System.out.println("LoginServlet 에서 메인에서 사용될 변수 설정  ");
		ArticleService as =  ArticleService.getInstance();		
		request.setAttribute("sort_count_map", as.getSortAndCount());
		
		request.setAttribute("article_list", as.getArticle());
		
		TagService ts = TagService.getInstance();			
		request.setAttribute("tag_list", ts.getAllTag());	
		
		
		request.setAttribute("article_number", as.getCount(ArticleDao.SEARCH_ARTICLE));		
		request.setAttribute("sort_number", as.getCount(ArticleDao.SEARCH_SORT));		
		request.setAttribute("tags_number", ts.getTagCount());
		
		
		request.setAttribute("visit_rank", as.getVisitRank());
		
		
		request.setAttribute("visited", VisitorDB.totalVisit());
		request.setAttribute("member", VisitorDB.totalMember());
		
		request.getRequestDispatcher("/page/main.jsp").forward(request, response);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
