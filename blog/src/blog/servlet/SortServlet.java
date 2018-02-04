package blog.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.ArticleService;
import blog.utils.StringUtils;


@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String get = StringUtils.pareCode(request.getParameter("get"));				
	
		ArticleService as = ArticleService.getInstance();		
		request.setAttribute("sort_article_map",as.getSortAndAirticle(get));					
		
		request.getRequestDispatcher("/page/sort.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
