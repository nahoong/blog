package blog.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.CommentService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CMStarServlet
 */
@WebServlet("/CMStarServlet")
public class CMStarServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String id = request.getParameter("id");
		System.out.println("CMStarServlet json에 사용될 변수 파싱");

		JSONObject jo = new JSONObject();		
		boolean repeat = false;

		Cookie[] cookies = request.getCookies();
		for( Cookie cookie: cookies){
			if(cookie.getName().equals("star_cm"+id)){
				
				 jo.put("msg", "failed");
				 repeat = true;
				 break;
				}			
		}
		if(! repeat){ //같으면 안됨						

			CommentService cs = CommentService.getInstance();;
			int new_star  = cs.star_diss(Integer.parseInt(id),Comment.STAR);
			 jo.put("msg", "success");
			 jo.put("new_star", new_star);
			 			 
			
			Cookie cookie = new Cookie("star_cm"+id, System.currentTimeMillis() + " ");
			
			cookie.setMaxAge(15*60);
			
			cookie.setPath("/blog");
			
			response.addCookie(cookie);
		}		
		
		response.getWriter().println(jo);
		
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
