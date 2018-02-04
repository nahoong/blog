package blog.dao;

import java.util.List;

import blog.model.Comment;

public interface CommentDao {

	/**
	 * ?ˆ ?™¤è¯„è??
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * ?–°?š„è¯„è??
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * ?·?–?Ÿä¸ªè¯„è®?
	 * 
	 * @param article_id
	 * @return
	 */
	List getComment(int article_id);

	/**
	 * ?‚¹èµæˆ–?…é„™è§?
	 * 
	 * @param id
	 * @param star_or_diss
	 * @return
	 */
	int star_diss(int id, int star_or_diss);

}