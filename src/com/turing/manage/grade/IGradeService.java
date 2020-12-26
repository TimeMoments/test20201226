package com.turing.manage.grade;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @desc 考生成绩查询模块的M层接口
 * @author HYZ
 * @time  2020年12月16日
 */
public interface IGradeService {

	List<Map<String, Object>> queryGrade() throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryByExaminee_id(String key) throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryByCourse_name(String key) throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryByGrade_minute_use(String key) throws ClassNotFoundException, SQLException;



}
