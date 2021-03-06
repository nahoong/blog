package blog.dao;

import java.util.List;

import blog.model.Comment;

public interface CommentDao {

	/**
	 * ? ?¤č¯č??
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * ?°?č¯č??
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * ?ˇ??ä¸Ēč¯čŽ?
	 * 
	 * @param article_id
	 * @return
	 */
	List getComment(int article_id);

	/**
	 * ?ščĩæ?éč§?
	 * 
	 * @param id
	 * @param star_or_diss
	 * @return
	 */
	int star_diss(int id, int star_or_diss);

}