package com.turing.manage.subject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;

public class SubjectServiceImpl implements ISubjectService {
	Dao dao =  new DaoImpl();

	@Override
	public List<Map<String, Object>> querySubject() throws ClassNotFoundException, SQLException {
		String sql = " select * from subject s left join course c on s.course_id=c.course_id ";
		return dao.executeQueryForList(sql);
	}

	@Override
	public List<Map<String, Object>> querySubjectBySelectType(String selectType, String key) throws ClassNotFoundException, SQLException {
		
		if (selectType==null) {
			selectType="";
		}
		if (key==null) {
			key="";
		}
		List<Map<String, Object>> list = null;
		if (selectType.equals("1")) {
			String sql = " select * from subject s left join course c on s.course_id=c.course_id where c.course_name like ? ";
			
			int [] types = new int [1];
			types[0] = Types.VARCHAR;
			
			
			Object [] values = new Object[1];
			values[0]="%"+key.trim()+"%";
			list = dao.executeQueryForList(sql, types, values);
		}else if (selectType.equals("2")) {
			String sql = " select * from subject s left join course c on s.course_id=c.course_id where s.subject_name like ? ";
			
			int [] types = new int [1];
			types[0] = Types.VARCHAR;
			
			Object [] values = new Object[1];
			values[0]="%"+key.trim()+"%";
			list = dao.executeQueryForList(sql, types, values);
		}else {
			String sql = " select * from subject s left join course c on s.course_id=c.course_id ";
			list = dao.executeQueryForList(sql);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryCourse() throws ClassNotFoundException, SQLException {
		return dao.executeQueryForList(" select * from course  ");
	}

	@Override
	public void save(String course_id, String subject_name, String subject_type, String subject_A, String subject_B,
			String subject_C, String subject_D, String subject_answer, String subject_remark) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = " insert into subject(subject_id,course_id,subject_name,subject_type,"
				+ "subject_A,subject_B,subject_C,subject_D,subject_answer,"
				+ "subject_remark) values('"+UUID.randomUUID().toString()+"','"+course_id+"'"
				+ ",'"+subject_name+"','"+subject_type+"'"
				+ ",'"+subject_A+"','"+subject_B+"','"+subject_C+"','"+subject_D+"'"
				+ ",'"+subject_answer+"','"+subject_remark+"') ";
		
		dao.executeUpdate(sql);
	}

	

	@Override
	public Map<String, Object> querySubject(String subject_id) throws ClassNotFoundException, SQLException {
		return dao.executeQueryForMap(" select * from subject where subject_id='"+subject_id+"' ");
	}

	@Override
	public void edit(String subject_id,String course_id, String subject_name, String subject_type, String subject_A, String subject_B,
			String subject_C, String subject_D, String subject_answer, String subject_remark) throws ClassNotFoundException, SQLException {
		
		String sql = " update subject set course_id='"+course_id+"',subject_name='"+subject_name+"',"
				+ "subject_type='"+subject_type+"',subject_A='"+subject_A+"',"
				+ "subject_B='"+subject_B+"',subject_C='"+subject_C+"',subject_D='"+subject_D+"',"
			    + "subject_answer='"+subject_answer+"',subject_remark='"+subject_remark+"' "
			    + "where subject_id='"+subject_id+"' ";
		dao.executeUpdate(sql);
	}

	@Override
	public void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		for(int i =0;i<delIdArray.length;i++){
			System.out.println(delIdArray[i]);
		    //先根据试卷id在试卷明细表testpaper_list中删除该试卷ID对应的成绩信息
			String sql1 = " delete from testpaper_list where subject_id=? ";
			//然后在试卷表中删除试卷信息
			String sql2 = " delete from subject where subject_id=? ";
			dao.executeUpdate(sql1, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
			dao.executeUpdate(sql2, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
			
		}
	}
	
}
