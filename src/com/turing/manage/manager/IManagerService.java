package com.turing.manage.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @desc 登录模块的M层接口
 * @author HYZ
 * @time  2020年12月1日
 */
public interface IManagerService {

	List<Map<String, Object>> queryAll() throws ClassNotFoundException, SQLException;
	void save(String manager_name, String manager_pass, String virtualPath) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;
	void deleteByManagerId(String manager_id) throws ClassNotFoundException, SQLException;
	Map<String, Object> queryOneByManagerId(String manager_id) throws ClassNotFoundException, SQLException;
	void saveEdit(String manager_id,String manager_name, String manager_pass, String virtualPath) throws NumberFormatException, ClassNotFoundException, SQLException;
	int queryManagerByName(String manager_name) throws ClassNotFoundException, SQLException;

}
