package blog.dao;

import java.util.List;

import blog.model.Comment;

public interface CommentDao {

	/**
	 * ?��?��评�??
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * ?��?��评�??
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * ?��?��?��个评�?
	 * 
	 * @param article_id
	 * @return
	 */
	List getComment(int article_id);

	/**
	 * ?��赞或?�鄙�?
	 * 
	 * @param id
	 * @param star_or_diss
	 * @return
	 */
	int star_diss(int id, int star_or_diss);

}