package com.turing.manage.testpaper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @desc  试卷管理模块的M层接口
 * @author HYZ
 * @time  2020年12月16日
 */
public interface ITestpaperService {

	List<Map<String, Object>> queryTestpaper() throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryClasss() throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryCourse() throws ClassNotFoundException, SQLException;

	void save(String testpaper_name, String testpaper_radio_num, String testpaper_check_num, String testpaper_time_use,
			String classs_id, String course_id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void createTestPaperById(String id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	Map<String, Object> queryTestPaperById(String id) throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryTestPaperRadioInfo(String id) throws ClassNotFoundException, SQLException;

	List<Map<String, Object>> queryTestPaperCheckInfo(String id) throws ClassNotFoundException, SQLException;

	void updateByCanTest(String id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void updateByFinishTest(String id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void edit(String testpaper_id, String testpaper_name, String testpaper_radio_num, String testpaper_check_num,
			String testpaper_time_use, String classs_id, String course_id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	void deleteByIdArray(String[] delIdArray) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	
	
}
