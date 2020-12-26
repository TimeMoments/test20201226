package com.turing.manage.examinee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * @desc  考生管理模块的的M层接口
 * @author HYZ
 * @time  2020年12月7日
 */
public interface IExamineeService {

	List<Map<String, Object>> queryExaminee(String classs_id,String key) throws ClassNotFoundException, SQLException;

	void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	List<Map<String, Object>> queryClasss() throws ClassNotFoundException, SQLException;

	Map<String, Object> queryClasssById(String examinee_id) throws ClassNotFoundException, SQLException;

	void update(String examinee_id, String examinee_name, String examinee_sex, String examinee_time,
			String examinee_question, String examinee_identity) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void save(String examinee_id,String classs_id,String examinee_name, String examinee_sex, String examinee_time,
			String examinee_question, String examinee_identity) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	List<Map<String, Object>> queryClasss(String classs_id, String classs_name) throws ClassNotFoundException, SQLException;

	void saveByImportExcel(List<String> columnValuesList) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;




}
