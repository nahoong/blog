package blog.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.ArticleService;
import blog.service.CommentService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/AriStarServlet")
public class AriStarServlet extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// ä¸å¡?ä½? ?·?è¯è?ºid
				String id = request.getParameter("id");
				// è¿å??°?®
				JSONObject jo = new JSONObject();
				boolean repeat = false;
				// ?ä¸?ä¸ªcookie?²æ­??å¤æäº?
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("star_arti" + id)) {
						// ?å¤æäº¤äº?°?®
						jo.put("msg", "failed");
						repeat = true;
						break;
					}
				}
				if (!repeat) {

					ArticleService as =  ArticleService.getInstance();
					int  new_star= as.starArticle(Integer.parseInt(id));		
					
					jo.put("msg", "success");
					jo.put("new_star", new_star);
					// ??æ°?cookie
					Cookie cookie = new Cookie("star_arti" + id, System.currentTimeMillis() + " ");
					// è®¾ç½®??? 15??
					cookie.setMaxAge(15*60);
					// è®¾ç½®???®å½?
					cookie.setPath("/blog");
					// ?ä¼æµè§å¨
					response.addCookie(cookie);
				}
				// ??ajax
				response.getWriter().println(jo);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
