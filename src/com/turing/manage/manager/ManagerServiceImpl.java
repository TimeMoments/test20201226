package com.turing.manage.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;

/**
 * @desc 登录模块的M层的实现类
 * @author HYZ
 * @time  2020年12月1日
 */
public class ManagerServiceImpl implements IManagerService{
	Dao dao=new DaoImpl();

//	@Override
//	public List<Map<String, Object>> queryAll() throws ClassNotFoundException, SQLException {
//		return dao.executeQueryForList("  select * from manager  ");
//	}
	
	

//	@Override
//	public void save(String manager_name, String manager_pass, String virtualPath) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
//		dao.executeUpdate(" insert into manager values(0,?,?,?) ", new int []{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}, new Object[]{manager_name,manager_pass,virtualPath});
//	}

//	@Override
//	public void deleteByManagerId(String manager_id) throws ClassNotFoundException, SQLException {
//		dao.executeUpdate(" delete from manager where manager_id='"+manager_id+"' ");
//	}

	@Override
	public List<Map<String, Object>> queryAll() throws ClassNotFoundException, SQLException {
		return dao.executeQueryForList(" select * from manager ");
	}

	@Override
	public void save(String manager_name, String manager_pass, String virtualPath)
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		dao.executeUpdate(" insert into manager values(0,?,?,?)", new int []{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR}, new Object[]{manager_name,manager_pass,virtualPath});
		
	}

	@Override
	public void deleteByManagerId(String manager_id) throws ClassNotFoundException, SQLException {
		dao.executeUpdate(" delete from manager where manager_id = '"+manager_id+"'");
	}

	@Override
	public Map<String, Object> queryOneByManagerId(String manager_id) throws ClassNotFoundException, SQLException {
		return dao.executeQueryForMap(" select * from manager where manager_id='"+manager_id+"' ");
		
	}

	@Override
	public void saveEdit(String manager_id,String manager_name, String manager_pass, String virtualPath) throws NumberFormatException, ClassNotFoundException, SQLException {
		dao.executeUpdate(" update manager set manager_name='"+manager_name+"',manager_pass='"+manager_pass+"',manager_imgpath='"+virtualPath+"' where manager_id="+Integer.parseInt(manager_id)+"");
		
	}

	@Override
	public int queryManagerByName(String manager_name) throws ClassNotFoundException, SQLException {
		String sql = " select count(*) from manager where manager_name=? ";
		return dao.executeQueryForInt(sql, new int []{Types.VARCHAR}, new Object[]{manager_name});
	}

	

}
