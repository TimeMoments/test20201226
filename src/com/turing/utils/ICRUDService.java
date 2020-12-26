package com.turing.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICRUDService {
	/**
	 * @desc  删除（批量删除）
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException;
	/**
	 * @desc  将修改页面数据更新数据库
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException ;
	/**
	 * @desc  进入修改页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException ;
	/**
	 * @desc 保存添加页面的内容
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException ;
	/**
	 * @desc  进入添加页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException ;
	/**
	 * @desc 查询
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException;
}
