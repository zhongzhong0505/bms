package bms.mysql.dao;

import java.util.List;

import bms.mysql.vo.MemoVO;

/**
 * 备忘管理接口
 * @author zhong
 *
 */
public interface IMemoDAO {
	/**
	 * 增加备忘信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	boolean addMemo(MemoVO memo) throws Exception;
	/**
	 * 删除备忘信息
	 * @param memo_id
	 * @return boolean
	 * @throws Exception
	 */
	boolean deleteMemo(int memo_id) throws Exception;
	/**
	 * 修改备忘信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	boolean updateMemo(MemoVO memo) throws Exception;
	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception
	 */
	int getCount() throws Exception;
	/**
	 * 分页
	 * @param start
	 * @param limit
	 * @return List<MemoVO>
	 * @throws Exception
	 */
	List<MemoVO> findPageMemo(int start,int limit) throws Exception;
	
	/**
	 * 根据关键字查询备忘信息
	 * @param key
	 * @return List<MemoVO>
	 * @throws Exception
	 */
	List<MemoVO> findMemosByKey(String key) throws Exception;
}
