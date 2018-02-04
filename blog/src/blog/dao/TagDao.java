package blog.dao;

import java.util.List;

public interface TagDao {


	// ?ˆ ?™¤? ‡ç­¾æ—¶(id,tag) ?‹¥ä»…æœ‰?Ÿä¸?ä¸ªå±??
	// ?¯ä»¥ä½¿?”¨è¿™äº›default?¼è¡¨ç¤ºåœ¨sqlè¯??¥ä¸?è·³è¿‡è¿™ä¸ªå±æ??
	// delete from t_tag where id=? or tag=?
	int DEFAULT_ID = 9;
	String DEFAULT_TAG = "Á¤½Â·æ2018";
	
	/**
	 * ?–°?š„? ‡ç­?
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean addTag(int id, String tag);

	/**
	 * ?ˆ ?™¤? ‡ç­?
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean deleteTag(int id, String tag);

	/**
	 * ?·?–???œ‰?š„? ‡ç­? ä¸å«?‡å¤?
	 * 
	 * @return
	 */
	List getAllTag();

	/**
	 * ?›´?–°? ‡ç­?
	 * 
	 * @param old_tag
	 * @param new_tag
	 * @return
	 */
	boolean updateTag(String old_tag, String new_tag);

	/**
	 * ?šè¿‡?ˆ—å±æ?§è·?–? ‡ç­?
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List getTagByColumn(String column, String value);

}