package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.dao.ArticleDao;
import blog.db.DBUtil;
import blog.model.Article;
import blog.model.Comment;
import blog.utils.DBUtils;

/*
 * 
 *
 */
public class ArticleDaoImpl implements ArticleDao {

	private Connection conn;	
	private static ArticleDao instance;

	private ArticleDaoImpl() throws SQLException {
		conn = DBUtil.getConnection();
	}

	public static final ArticleDao getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;

	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#addVisit(int)
	 */
	@Override
	public void addVisit(int article_id) {

		String sql = "update t_article set visit = visit+1 where blog_id = " + article_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getANearArticle(java.lang.String, int)
	 */
	@Override
	public Article getANearArticle(String time, int less_or_more) {

		Article article = null;
		String sql = null;
		if (less_or_more == this.LESS) {
			sql = " SELECT  * FROM t_article WHERE datetime < to_date('" + time + "','YYYY-MM-DD hh24:mi:ss')  ORDER BY datetime DESC ";
		} else if (less_or_more == this.MORE) {
			sql = " SELECT  * FROM t_article WHERE datetime > to_date('" + time + "','YYYY-MM-DD hh24:mi:ss')  ORDER BY datetime ";
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				article = new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"),
						rs.getString("blog_sort"), rs.getString("datetime"), rs.getInt("blog_star"), rs.getInt("blog_comment"),
						rs.getInt("visit"), rs.getString("blog_content"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getColumAndCount(java.lang.String)
	 */
	@Override
	public Map getColumAndCount(String search_column) {

		String sql = " select " + search_column + " ,count(" + search_column + ") as counts  from t_article  group by "
				+ search_column;
		Map map = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			map = new HashMap();
			while (rs.next()) {
				map.put(rs.getString(search_column), rs.getInt("counts"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getAllSort()
	 */
	@Override
	public List getAllSort() {

		String sql = " select distinct(blog_sort) from t_article order by blog_sort";
		List list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#addArticle(blog.model.Article)
	 */
	@Override
	public Article addArticle(Article a) {

		String sql = "insert into t_article values(seq_id.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getAuthor());
			ps.setString(3, a.getSort());
			ps.setString(4, a.getTime());
			ps.setInt(5, a.getStar());
			ps.setInt(6, a.getComment());
			ps.setInt(7, a.getVisit());
			ps.setString(8, a.getContent());
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.getLastArticle();

	}

	/**
	 * Â∞ÜÊñáÁ´†Âä†?à∞delteË°?
	 * 
	 * @param a
	 * @return
	 */
	private boolean addArticle_delet(Article a) {

		String sql = "insert into t_article_delet values(delet.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getAuthor());
			ps.setString(3, a.getSort());
			ps.setString(4, a.getTime());
			ps.setInt(5, a.getStar());
			ps.setInt(6, a.getComment());
			ps.setInt(7, a.getVisit());
			ps.setString(8, a.getContent());
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;

	}

	/**
	 * ?é∑?èñ???ñ∞?öÑ?ñáÁ´?
	 * 
	 * @return
	 */
	private Article getLastArticle() {

		String sql = "SELECT * FROM t_article ORDER BY TIME DESC LIMIT 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Article article =  new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"), rs.getString("blog_sort"),
						rs.getString("datetime"), rs.getInt("blog_star"), rs.getInt("blog_comment"), rs.getInt("visit"),
						rs.getString("blog_content"));
				DBUtils.Close(ps, rs);
				return article;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#deleteArticle(java.lang.String)
	 */
	@Override
	public boolean deleteArticle(String id) {

		String sql = "delete from t_article where id=?";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
			// ?Ö≥?ó≠ËøûÊé•
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getAllArticle()
	 */
	@Override
	public List getAllArticle() {
		List<Article> list = new ArrayList();

		String sql = "select * from t_article";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// beanÂÆû‰æã?åñ
			while (rs.next()) {
				Article article =  new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"), rs.getString("blog_sort"),
						rs.getString("datetime"), rs.getInt("blog_star"), rs.getInt("blog_comment"), rs.getInt("visit"),
						rs.getString("blog_content"));
				list.add(article);
			}
			// ?Ö≥?ó≠ËøûÊé•
			DBUtils.Close(ps, rs);
			// ?éíÂ∫? article compareTo();
			Collections.sort(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getVisitRank()
	 */
	@Override
	public List getVisitRank() {
		List<Article> list = new ArrayList();

		String sql = "SELECT * FROM t_article ORDER BY visit DESC";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// beanÂÆû‰æã?åñ
			while (rs.next()) {
				Article article =  new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"), rs.getString("blog_sort"),
						rs.getString("datetime"), rs.getInt("blog_star"), rs.getInt("blog_comment"), rs.getInt("visit"),
						rs.getString("blog_content"));
				list.add(article);
			}
			// ?Ö≥?ó≠ËøûÊé•
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getArticleByColumn(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Article> getArticleByColumn(String column, String value) {
		List<Article> list = null;
		Article at = null;
		String sql = "select * from t_article where " + column + " = ?";
		System.out.println("ArticleDaoimpl->getArticleByColumn column = "+ column);
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();
			// beanÂÆû‰æã?åñ
			list = new ArrayList();
			while (rs.next()) {
				at = new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"), rs.getString("blog_sort"),
						rs.getString("datetime"), rs.getInt("blog_star"), rs.getInt("blog_comment"), rs.getInt("visit"),
						rs.getString("blog_content"));
				list.add(at);
			}
			// ?Ö≥?ó≠ËøûÊé•
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String search_key) {

		String sql;
		if (search_key.equals(SEARCH_ARTICLE)) {
			sql = "SELECT COUNT(blog_id) FROM t_article";
		} else {// SEARCH_SORT
			sql = "SELECT COUNT(DISTINCT(blog_sort)) FROM t_article";
		}
		int result = 0;
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#star_article(int)
	 */
	@Override
	public int star_article(int id) {

		String sql = "update t_article set blog_star=blog_star+1 where blog_id=" + id;
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			// DBUtils.Close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select blog_star from t_article where blog_id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#updateSort(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateSort(String old_sort, String new_sort) {

		String sql = "UPDATE t_article SET blog_sort=? WHERE blog_sort=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, new_sort);
			ps.setString(2, old_sort);
			ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#delelteSort(java.lang.String)
	 */
	@Override
	public boolean delelteSort(String sort) {
		// ?âæ?à∞Ëøô‰∏™?àÜÁ±ª‰∏ã?öÑ?ñáÁ´? ÁßªÂä®?à∞t_article_delet
		String sql = "SELECT * FROM t_article where blog_sort = ?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sort);
			ResultSet rs = ps.executeQuery();

			List<Article> list = new ArrayList();
			while (rs.next()) {
				Article article =  new Article(rs.getInt("blog_id"), rs.getString("title"), rs.getString("author"), rs.getString("blog_sort"),
						rs.getString("datetime"), rs.getInt("blogstar"), rs.getInt("blog_comment"), rs.getInt("visit"),
						rs.getString("blog_content"));
				list.add(article);
			}
			System.out.println(list.size());
			if (list.size() > 0) {
				for (Article a : list) {
					this.addArticle_delet(a);
				}
			}

			sql = "delete from t_article where blog_sort =?";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setString(1, sort);
			result = ps2.executeUpdate();
			System.out.println(result);
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

}
