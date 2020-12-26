package com.turing.manage.login;

import java.sql.SQLException;
import java.util.Map;
/**
 * @desc 登录模块的M层接口
 * @author HYZ
 * @time  2020年12月1日
 */
public interface ILoginService {

//	Map<String, Object> queryManagerByNameAndPass(String name, String pass) throws ClassNotFoundException, SQLException;
//	Map<String, Object> queryManagerByNameAndPass(String name, String pass) throws ClassNotFoundException, SQLException;
	Map<String, Object> queryManagerByNameAndPass(String name, String pass) throws ClassNotFoundException, SQLException;
}
