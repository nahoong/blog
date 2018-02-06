package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blog.dao.CommentDao;
import blog.db.DBUtil;
import blog.model.Comment;
import blog.utils.DBUtils;

public class CommentDaoImpl implements CommentDao {

	private Connection conn;

	private CommentDaoImpl() throws SQLException {
		conn = DBUtil.getConnection();
	}

	private static CommentDao instance;

	public static CommentDao getInstance() {
		if (instance == null) {
			try {
				instance = new CommentDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;

	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.CommentDao#deleteComment(int)
	 */
	@Override
	public boolean deleteComment(int comment_id) {

		String sql = "DELETE FROM t_comment WHERE blog_id=" + comment_id;
		int result = 0;
		try {
			// ?–‡ç«?-1è¯„è??
			article_sub_comemnt(conn, comment_id);
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/**
	 * ?–‡ç« çš„è¯„è??-1
	 * 
	 * @param conn
	 * @param comment_id
	 */
	private void article_sub_comemnt(Connection conn, int comment_id) {

		String sql = "SELECT  article_id FROM t_comment WHERE blog_id =" + comment_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int article_id = 0;
			if (rs.next()) {
				article_id = rs.getInt("article_id");
			}
			sql = "UPDATE t_article SET blog_comment=blog_comment - 1 WHERE blog_id=" + article_id;
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.CommentDao#addComment(blog.model.Comment)
	 */
	@Override
	public boolean addComment(Comment comment) {

		
		String sql = "INSERT  INTO t_comment VALUES(comment_id.nextval,?,?,?,sysdate,?,?)";
		int result = 0;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getArticle_id());
			ps.setString(2, comment.getNickname());
			ps.setString(3, comment.getContent());
			//ps.setString(4, comment.getTime());
			ps.setInt(4, comment.getStar());
			ps.setInt(5, comment.getDiss());
			result = ps.executeUpdate();

			// ?–‡ç« åŠ 1è¯„è??
			sql = "UPDATE t_article SET blog_comment = blog_comment+1  WHERE blog_id=" + comment.getArticle_id();
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.executeUpdate();
			
			System.out.println("CommnetDaoImpl -> addComment success");

			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.CommentDao#getComment(int)
	 */
	@Override
	public List getComment(int article_id) {

		String sql = "SELECT * FROM t_comment WHERE article_id=? ORDER BY datetime";
		List list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, article_id);
			ResultSet rs = ps.executeQuery();
			Comment cm;
			list = new ArrayList();
			while (rs.next()) {
				cm = new Comment();
				cm.setId(rs.getInt("blog_id"));
				cm.setArticle_id(rs.getInt("article_id"));
				cm.setNickname(rs.getString("nickname"));
				cm.setTime(rs.getString("datetime"));
				cm.setStar(rs.getInt("blog_star"));
				cm.setContent(rs.getString("blog_content"));
				cm.setDiss(rs.getInt("blog_diss"));
				list.add(cm);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.CommentDao#star_diss(int, int)
	 */
	@Override
	public int star_diss(int id, int star_or_diss) {

		String sql;

		int result = -1;

		if (star_or_diss == Comment.STAR) {
			sql = "update t_comment set blog_star=blog_star+1 where blog_id=" + id;
		} else if (star_or_diss == Comment.DISS) {
			sql = "update t_comment set blog_diss=blog_diss-1 where blog_id=" + id;
		} else {
			return -1;
		}

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			// DBUtils.Close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (star_or_diss == Comment.STAR) {
			sql = "SELECT blog_star FROM t_comment WHERE blog_id = " + id;
		} else if (star_or_diss == Comment.DISS) {
			sql = "SELECT blog_diss FROM t_comment WHERE blog_id = " + id;
		}

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
