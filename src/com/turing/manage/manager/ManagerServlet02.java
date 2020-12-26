package com.turing.manage.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;


@SuppressWarnings("serial")
public class ManagerServlet02 extends HttpServlet{
	IManagerService managerService = new ManagerServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ManagerServlet-->service()");
		String method = request.getParameter("method");
		try {
			if ("query".equals(method)) {
				this.query(request,response);
			}else if ("upload".equals(method)) {
				this.upload(request,response);
			}else if ("download".equals(method)) {
				this.download(request,response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类没找到异常...");
		} catch (SQLException e) {
			System.out.println("SQL异常...");
		} catch (FileUploadException e) {
			System.out.println("文件上传异常...");
		}
	}
private void downloadOutsider(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String imgUrl = "https://www.baidu.com/img/bd_logo1.png";
	URL url = new URL(imgUrl);
	URLConnection connection = url.openConnection();
	InputStream inputStream = connection.getInputStream();
	response.setContentType("image/jpg");
	response.setHeader("Access-Control-Allow-Origin", "*");
	IOUtils.copy(inputStream, response.getOutputStream());
}

private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String manager_imgpath = request.getParameter("manager_imgpath");
	try {
		String path = this.getServletContext().getRealPath("/")+"WEB-INF/image/";
		File file = new File(path+manager_imgpath);
		FileInputStream fileInputStream = new FileInputStream(file);
		System.out.println("fileInputStream--->"+fileInputStream);
		response.setContentType("image/jpg");
		response.setHeader("Access-Control-Allow-Origin", "*");
		IOUtils.copy(fileInputStream, response.getOutputStream());
	} catch (Exception e) {
		downloadOutsider(request, response);
	}
	
}

private void upload(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ClassNotFoundException, SQLException {
	response.setContentType("text/html;charset=utf-8");
	String fileSaveName = null;
	List<FileItem> formItemList;
	String path = this.getServletContext().getRealPath("/")+"WEB-INF/image";
	System.out.println("图片存储路径: "+ path);
	File file = new File(path);
	if (!file.exists()) {
		file.mkdir();
	}
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("utf-8");
	formItemList = upload.parseRequest(request);
	if ((formItemList != null)&&(formItemList.size() > 0)) {
		for (FileItem Item : formItemList) {
			if (!Item.isFormField()) {
				String fileName = Item.getName();
				System.out.println("上传文件的名字: "+ fileName);
				String prifix = fileName.substring(fileName.lastIndexOf(".")+1);
				System.out.println("上传文件的后缀: "+ prifix);
				String id = UUID.randomUUID().toString();
				fileSaveName = id + "." + prifix;
				System.out.println("保存文件的名字: "+ fileSaveName);
				FileUtils.copyInputStreamToFile(Item.getInputStream(), new File(path+"/"+fileSaveName));
			}
		}
	}
	String virtualPath = fileSaveName;
	String manager_name = formItemList.get(0).getString("utf-8");
	System.out.println("manager_name: "+ manager_name);
	String manager_pass = formItemList.get(1).getString("utf-8");
	System.out.println("manager_pass: "+ manager_pass);
	managerService.save(manager_name, manager_pass, virtualPath);
}

private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	List<Map<String,Object>> list = managerService.queryAll();
	request.setAttribute("list", list);
	request.getRequestDispatcher("/manage/manager/list.jsp").forward(request, response);
}
	
}
