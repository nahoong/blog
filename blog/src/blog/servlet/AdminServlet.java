package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.db.VisitorDB;
import blog.service.ArticleService;
import blog.service.TagService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminServlet Á¢¼Ó º¯¼ö¼³Á¤");
		//ä¼ æ??œ‰?š„?–‡ç«?
		ArticleService as =  ArticleService.getInstance();		
		request.setAttribute("articles",as.getArticle());
		//ä¼ æ??œ‰?š„?ˆ†ç±?
		request.setAttribute("sort", as.getAllSort());		
		//ä¼ æ??œ‰?š„? ‡ç­?
		TagService ts = TagService.getInstance();
		request.setAttribute("tags", ts.getAllTag());
		//ä¼ ç½‘ç«™çš„ç»Ÿè?¡æ•°?®
		request.setAttribute("visited", VisitorDB.totalVisit());
		request.setAttribute("member", VisitorDB.totalMember());
		
		//è½¬å‘
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
