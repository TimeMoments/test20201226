package com.turing.manage.course;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @desc 课程信息模块的M层接口
 * @author HYZ
 * @time  2020年12月16日
 */
public interface ICourseService {

	List<Map<String, Object>> queryCourse() throws ClassNotFoundException, SQLException;

	void save(String course_name) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void edit(String course_id, String course_name) throws ClassNotFoundException, SQLException;

	Map<String, Object> queryCourse(String course_id) throws ClassNotFoundException, SQLException;

	void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void deleteByCourseId(String course_id) throws ClassNotFoundException, SQLException;





}
