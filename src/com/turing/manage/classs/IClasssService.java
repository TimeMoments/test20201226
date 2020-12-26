package com.turing.manage.classs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @desc  班級管理模块的的M层接口
 * @author HYZ
 * @time  2020年12月7日
 */
public interface IClasssService {

	List<Map<String, Object>> queryAll() throws ClassNotFoundException, SQLException;

	void save(String classs_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	Map<String, Object> queryClasssById(String classs_id) throws ClassNotFoundException, SQLException;

	void update(String classs_id, String classs_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

}
