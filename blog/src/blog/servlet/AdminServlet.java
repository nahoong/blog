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
		System.out.println("AdminServlet 접속 변수설정");
		//鴉졿??쐣?쉪?뻼塋?
		ArticleService as =  ArticleService.getInstance();		
		request.setAttribute("articles",as.getArticle());
		//鴉졿??쐣?쉪?늽映?
		request.setAttribute("sort", as.getAllSort());		
		//鴉졿??쐣?쉪?젃嶺?
		TagService ts = TagService.getInstance();
		request.setAttribute("tags", ts.getAllTag());
		//鴉좂퐨塋숂쉪瀯잒?→빊?뜮
		request.setAttribute("visited", VisitorDB.totalVisit());
		request.setAttribute("member", VisitorDB.totalMember());
		
		//饔у룕
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
