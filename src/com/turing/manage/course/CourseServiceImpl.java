package com.turing.manage.course;



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
 * @desc    课程信息模块的M层的实现类
 * @author HYZ
 * @time  2020年12月16日
 */
public class CourseServiceImpl implements ICourseService{
	Dao dao = new DaoImpl();

	@Override
	public List<Map<String, Object>> queryCourse() throws ClassNotFoundException, SQLException {
		
		return dao.executeQueryForList("select * from course");
	}

	@Override
	public void save(String course_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String sql = "insert into course values(?,?,?)";
		
		int [] types = new int[3];
		types[0]=Types.VARCHAR;
		types[1]=Types.VARCHAR;
		types[2]=Types.VARCHAR;
		
		Object [] values = new Object[3];
		values[0] = UUID.randomUUID().toString();
		values[1] = course_name;
		values[2] = Dates.CurrentYMDHSMTime();
		dao.executeUpdate(sql, types, values);
	}

	@Override
	public void edit(String course_id, String course_name) throws ClassNotFoundException, SQLException {
		String sql =" update course set course_name='"+course_name+"' where course_id='"+course_id+"'  ";
		dao.executeUpdate(sql);
	}

	@Override
	public Map<String, Object> queryCourse(String course_id) throws ClassNotFoundException, SQLException {
		return dao.executeQueryForMap(" select * from course where course_id='"+course_id+"' ");
	}

	@Override
	public void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		for(int i =0;i<delIdArray.length;i++){
			System.out.println(delIdArray[i]);
		    //先根据课程id在成绩明细表grade中删除该课程ID对应的成绩信息
			String sql1 = " delete from grade where course_id=? ";
			//根据试卷id在试卷表中删除课程试卷信息
			String sql2 = " delete from testpaper where course_id=? ";
			//最后根据课程id删除课程
			String sql3 = " delete from course where course_id=? ";
			
			dao.executeUpdate(sql1, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
			dao.executeUpdate(sql2, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});
			dao.executeUpdate(sql3, new int []{Types.VARCHAR}, new Object[]{delIdArray[i]});

		}
	}

	@Override
	public void deleteByCourseId(String course_id) throws ClassNotFoundException, SQLException {
		//先根据课程id在成绩明细表grade中删除该课程ID对应的成绩信息
		String sql1 = " delete from grade where course_id='"+course_id+"' ";
		//根据试卷id在试卷表中删除课程试卷信息
		String sql2 = " delete from testpaper where course_id='"+course_id+"' ";
		//最后根据课程id删除课程
		String sql3 = " delete from course where course_id='"+course_id+"' ";
		dao.executeUpdate(sql1);
		dao.executeUpdate(sql2);
		dao.executeUpdate(sql3);
		
	}

	

	

}
