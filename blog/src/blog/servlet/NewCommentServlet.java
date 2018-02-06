package blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.CommentService;
import blog.utils.DateUtils;
import blog.utils.FailException;
import blog.utils.Form2Bean;

/**
 * Servlet implementation class NewCommentServlet
 */
@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cookie_name = "comment_cookie"+request.getParameter("id");
		
		
		
		
		
		//?��?��?��?��?��?��?���?
		boolean isRpeat = false;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookie_name)) {
					isRpeat = true;
					break;
				}
			}
		}

		
		
		
		
		//返回?��信息		
		String info;		
		if( !isRpeat ){	
		Comment cm ;
		//?��?��对象
		try {
			System.out.println("NewCommentServlet -> doget / ��Ű id = " + cookie_name + " / isRpeat(��Ű�ߺ�)" + isRpeat);
			cm = Form2Bean.commentForm2Bean(request);
			CommentService cs = CommentService.getInstance();
			boolean result = cs.addComment(cm);
			if(!result){
				info="comment failed!";
			}else{
				info="comment success!";
			}
		} catch (FailException e) {	
			e.printStackTrace();
			info="comment failed!";			
		}
		}else{
			System.out.println("NewCommentServlet -> doget / ��Ű�����߻�" + cookie_name + " / isRpeat(��Ű�ߺ�)" + isRpeat);
			info ="repeat submit comment!";
		}
				
		//?��?�新?��cookie 
		Cookie c = new Cookie(cookie_name,DateUtils.getFormatDate(new Date()));
		c.setMaxAge(60 * 60);
		c.setPath("/blog");
		response.addCookie(c);
		
		request.setAttribute("info", info);
		
		request.getRequestDispatcher("/ArticleServlet").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
