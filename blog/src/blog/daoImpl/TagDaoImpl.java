package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.ArticleDao;
import blog.dao.TagDao;
import blog.db.DBUtil;
import blog.model.Tag;
import blog.utils.DBUtils;

/**
 * ?–‡ç« çš„? ‡ç­¾ç±»
 */
public class TagDaoImpl implements TagDao {

	private Connection conn;

	private static TagDao instance;

	private TagDaoImpl() throws SQLException {
		conn = DBUtil.getConnection();
	}

	public static final TagDao getInstance() {
		if (instance == null) {
			try {
				instance = new TagDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#addTag(int, java.lang.String)
	 */
	@Override
	public boolean addTag(int id, String tag) {

		String sql = "insert into t_tag values(?,?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, tag);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#deleteTag(int, java.lang.String)
	 */
	@Override
	public boolean deleteTag(int id, String tag) {

		String sql = "delete from t_tag where blog_id=? or blog_tag=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, tag);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#getAllTag()
	 */
	@Override
	public List getAllTag() {

		String sql = "SELECT  distinct(blog_tag)  FROM t_tag";
		List<Tag> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Tag>();
			Tag tag;
			while (rs.next()) {
				tag = new Tag();
				tag.setTag(rs.getString(1));
				// ç¼ºçœ? ‡ç­¾å?¹è±¡?š„id
				tag.setId(0);
				list.add(tag);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#updateTag(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateTag(String old_tag, String new_tag) {

		String sql = "update t_tag set blog_tag=? where blog_tag=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, new_tag);
			ps.setString(2, old_tag);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#getTagByColumn(java.lang.String, java.lang.String)
	 */
	@Override
	public List getTagByColumn(String column, String value) {

		System.out.println("TagDaoimpl -> detTagByColumn ÅÂ±×°¡Á®¿À±â Ä®·³°ª = " + column);
		String sql = "select * from t_tag where " + column + "=?";
		List list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList();
			Tag tag;
			while (rs.next()) {
				tag = new Tag();
				tag.setId(rs.getInt("blog_id"));
				tag.setTag(rs.getString("blog_tag"));
				list.add(tag);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
