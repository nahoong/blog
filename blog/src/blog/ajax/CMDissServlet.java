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
 * Servlet implementation class CMDissServlet
 */
@WebServlet("/CMDissServlet")
public class CMDissServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 业务?���? ?��?��评�?�id
		System.out.println("CMDissServlet json�� ���� ���� �Ľ�");
		String id = request.getParameter("id");
		// 返回?��?��?��
		JSONObject jo = new JSONObject();
		boolean repeat = false;
		// ?���?个cookie?���??��复提�?
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("diss_cm" + id)) {
				// ?��复提交了?��?��
				jo.put("msg", "failed");
				repeat = true;
				break;
			}
		}
		if (!repeat) {

			CommentService cs = CommentService.getInstance();
			int new_diss = cs.star_diss(Integer.parseInt(id), Comment.DISS);
			jo.put("msg", "success");
			jo.put("new_diss", new_diss);

			// ?��?�新?��cookie
			Cookie cookie = new Cookie("diss_cm" + id, System.currentTimeMillis() + " ");
			// 设置?��?��?�� 15?��?��
			cookie.setMaxAge(15*60);
			// 设置?��?��?���?
			cookie.setPath("/blog");
			// ?��会浏览器
			response.addCookie(cookie);
		}
		// ?��?��ajax
		response.getWriter().println(jo);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
