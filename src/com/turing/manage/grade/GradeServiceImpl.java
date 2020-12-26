package com.turing.manage.grade;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.turing.dao.Dao;
import com.turing.dao.DaoImpl;

/**
 * @desc    考生成绩查询模块的M层的实现类
 * @author HYZ
 * @time  2020年12月16日
 */
public class GradeServiceImpl implements IGradeService{
	Dao dao = new DaoImpl();

	@Override
	public List<Map<String, Object>> queryGrade() throws ClassNotFoundException, SQLException {
		String sql = " select g.grade_id,e.examinee_id,c.course_name,g.grade_minute_use,"
				+ "g.grade_radio_mark,g.grade_check_mark,g.grade_sum from grade g,course c,"
				+ "examinee e  where g.course_id = c.course_id and g.examinee_id=e.examinee_id ";
		return dao.executeQueryForList(sql);
	}

	@Override
	public List<Map<String, Object>> queryByExaminee_id(String key) throws ClassNotFoundException, SQLException {
		String sql = " select * from (select g.grade_id,e.examinee_id,c.course_name,g.grade_minute_use,"
				+ "g.grade_radio_mark,g.grade_check_mark,g.grade_sum from grade g,examinee e,"
				+ "course c where g.course_id = c.course_id and g.examinee_id=e.examinee_id) s where s.examinee_id LIKE ? ";
		
		
		return dao.executeQueryForList(sql,new int[]{Types.VARCHAR}, new Object[]{"%"+key+"%"});
	}

	@Override
	public List<Map<String, Object>> queryByCourse_name(String key) throws ClassNotFoundException, SQLException {
		String sql = " select * from (select g.grade_id,e.examinee_id,c.course_name,g.grade_minute_use,"
				+ "g.grade_radio_mark,g.grade_check_mark,g.grade_sum from grade g,examinee e,"
				+ "course c where g.course_id = c.course_id)"
				+ " s where s.course_name like ? ";
		return dao.executeQueryForList(sql, new int[]{Types.VARCHAR}, new Object[]{"%"+key+"%"});
	}

	@Override
	public List<Map<String, Object>> queryByGrade_minute_use(String key) throws ClassNotFoundException, SQLException {
		String sql = " SELECT * FROM (SELECT g.grade_id,e.examinee_id,c.course_name,g.grade_minute_use,"
				+ "g.grade_radio_mark,g.grade_check_mark,g.grade_sum FROM grade g,examinee e,"
				+ "course c WHERE g.course_id = c.course_id) s WHERE s.grade_minute_use LIKE ? ";
		return dao.executeQueryForList(sql, new int[]{Types.VARCHAR}, new Object[]{"%"+key+"%"});
	}
	

}
