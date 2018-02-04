package blog.dao;

import java.util.List;

public interface TagDao {


	// ?��?��?��签时(id,tag) ?��仅有?���?个属??
	// ?��以使?��这些default?�表示在sql�??���?跳过这个属�??
	// delete from t_tag where id=? or tag=?
	int DEFAULT_ID = 9;
	String DEFAULT_TAG = "���·�2018";
	
	/**
	 * ?��?��?���?
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean addTag(int id, String tag);

	/**
	 * ?��?��?���?
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean deleteTag(int id, String tag);

	/**
	 * ?��?��???��?��?���? 不含?���?
	 * 
	 * @return
	 */
	List getAllTag();

	/**
	 * ?��?��?���?
	 * 
	 * @param old_tag
	 * @param new_tag
	 * @return
	 */
	boolean updateTag(String old_tag, String new_tag);

	/**
	 * ?�过?��属�?�获?��?���?
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List getTagByColumn(String column, String value);

}