package com.turing.manage.classs;

import java.util.List;
import java.util.Map;

public interface IClasssService02 {

	List<Map<String, Object>> queryAll();

	void save(String classs_name);

	Map<String, Object> queryClasssById(String classs_id);

	void update(String classs_id, String classs_name);

	void deleteByIdArray(String[] delIdArray);

}
