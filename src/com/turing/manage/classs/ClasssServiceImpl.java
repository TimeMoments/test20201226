package com.turing.manage.classs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;
import com.turing.utils.Dates;
/**
 * @desc 班級管理的M层的实现类
 * @author HYZ
 * @time  2020年12月7日
 */
public class ClasssServiceImpl implements IClasssService {
	Dao dao = new DaoImpl();
	@Override
	public List<Map<String, Object>> queryAll() throws ClassNotFoundException, SQLException {
		return dao.executeQueryForList(" select * from classs ");
	}
	@Override
	public void save(String classs_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = "insert into classs values(?,?,?)";
		
		int [] types = new int[3];
		types[0]=Types.VARCHAR;
		types[1]=Types.VARCHAR;
		types[2]=Types.VARCHAR;
		
		Object [] values = new Object[3];
		values[0] = UUID.randomUUID().toString();
		values[1] = classs_name;
		values[2] = Dates.CurrentYMDHSMTime();
		dao.executeUpdate(sql, types, values);
		
	}
	@Override
	public Map<String, Object> queryClasssById(String classs_id) throws ClassNotFoundException, SQLException {
		return dao.executeQueryForMap(" select * from classs where classs_id=? ",new int []{Types.VARCHAR},new Object[]{classs_id,});
	}
	@Override
	public void update(String classs_id, String classs_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		dao.executeUpdate("update classs set classs_name=? where classs_id=?", new int []{Types.VARCHAR,Types.VARCHAR},new Object[]{classs_name,classs_id});
		
	}
	@Override
	public void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		for(int i =0;i<delIdArray.length;i++){
		     //1.根据班级的id(classs_id)去考生信息表中去查询考生的准考证号，然后根据准考证号在成绩表中删除相关信息记录
				String sql1=" delete from grade where examinee_id in (select examinee_id from examinee where classs_id=? ) ";
			//2.删除考生信息
				String sql2="  delete from examinee where classs_id=?  ";
			//3.根据班级的id去试卷表中获取试卷的id，然后根据试卷的id在试卷明细表中删除相关信息
				String sql3=" delete from testpaper_list where testpaper_id in ( select testpaper_id from testpaper where classs_id=?  )";
			//4.删除试卷信息
				String  sql4=" delete from testpaper where classs_id=? ";
			//5.删除班级信息
				String sql5=" delete from classs  where classs_id=?  ";
				
				dao.executeUpdate(sql1, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
				dao.executeUpdate(sql2, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
				dao.executeUpdate(sql3, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
				dao.executeUpdate(sql4, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
				dao.executeUpdate(sql5, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});

			}
	}

}
