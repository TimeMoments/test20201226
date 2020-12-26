package com.turing.manage.subject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @desc  试题查询模块的M层接口
 * @author HYZ
 * @time  2020年12月17日
 */
public interface ISubjectService {

	List<Map<String, Object>> querySubject() throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> querySubjectBySelectType(String selectType, String key) throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryCourse() throws ClassNotFoundException, SQLException;

	void save(String course_id, String subject_name, String subject_type, String subject_A, String subject_B,
			String subject_C, String subject_D, String subject_answer, String subject_remark) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	Map<String, Object> querySubject(String subject_id) throws ClassNotFoundException, SQLException;

	void edit(String subject_id,String course_id, String subject_name, String subject_type, String subject_A, String subject_B,
			String subject_C, String subject_D, String subject_answer, String subject_remark) throws ClassNotFoundException, SQLException;

	void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

}
