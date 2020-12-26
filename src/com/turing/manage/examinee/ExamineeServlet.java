package com.turing.manage.examinee;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jdt.internal.compiler.codegen.StackMapFrameCodeStream;

import com.mysql.jdbc.Field;
import com.turing.utils.ExcelUtils;
import com.turing.utils.ICRUDServlet;
/**
 * @desc 考生管理模块的C层
 * @author HYZ
 * @time  2020年12月7日
 */
@SuppressWarnings("serial")
public class ExamineeServlet extends HttpServlet implements ICRUDServlet{
	IExamineeService examineeService = new ExamineeServiceImpl();
	ExcelUtils excel = new ExcelUtils();
	private int i = 1;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ExamineeServlet--->service()");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		try {
			if ("query".equals(method)) {
				this.query(request,response);
			}else if ("addPage".equals(method)) {
				this.addPage(request,response);
			}else if ("add".equals(method)) {
				this.add(request,response);
			}else if ("editPage".equals(method)) {
				this.editPage(request,response);
			}else if ("edit".equals(method)) {
				this.edit(request,response);
			}else if ("delete".equals(method)) {
				this.delete(request,response);
			}else if ("doSy".equals(method)) {
				this.doSy(request,response);	
			}else if (method.equals("doExportExcel")){
			  this.doExportExcel(request,response);	
			}else if (method.equals("doImportExcel")){
			  this.doImportExcel(request,response);	
			}
		} catch (ClassNotFoundException e) {
			System.out.println("异常信息为:"+e.getMessage());
			System.out.println("异常发生的内存地址:"+e.getStackTrace());
			request.setAttribute("error", "类没有找到异常");
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println("异常的信息为:"+e.getMessage());
			System.out.println("异常发生的内存地址:"+e.getStackTrace());
			request.setAttribute("error", "SQL语句异常");
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
/**
 * @desc  导入excel
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void doImportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
//		System.out.println("examineeServlet--->doImportExcel()");
		System.out.println("examineeServlet-->doImportExcel()");
		//0.定义/引入workbook.sheet.row,cell等接口对象，目的是为了适配不同版本的excel
//		Workbook wb = null;
		Workbook wb = null;
//		Sheet sheet;
		Sheet sheet;
//		Row row;
		Row row;
		//1.接值
//		String fed=request.getParameter("fed");
		String fed = request.getParameter("fed");
//		System.out.println("fed:"+fed);
		System.out.println("fed:"+fed);
		//2.将本次导入的excel转变为file对象
//		File file=new File(fed);
		File file = new File(fed);
//		FileInputStream fileInputStream=new FileInputStream(file);
		FileInputStream fileInputStream = new FileInputStream(file);
		//3.判断->适配不同版本的excel
//		if ((file.isFile())&&(file.exists())){
		if ((file.isFile())&&(file.exists())) {
//		  String filename = file.getName();
		  String filename = file.getName();
//		  System.out.println("本次上传的文件的名字为："+filename);
		  System.out.println("本次上传的文件的名字为:"+filename);
//		  String suffix = filename.substring(filename.lastIndexOf(".")+1);
		  String suffix = filename.substring(filename.lastIndexOf(".")+1);
//		  System.out.println("本次上传的文件的后缀为："+suffix);
		  System.out.println("本次上传的文件的后缀为:"+suffix);
//		  if ("xls".equals(suffix)){
		  if("xls".equals(suffix)){
//		  	  System.out.println("走excel2003解析");	
			  System.out.println("走excel2003解析");
//			  wb=new HSSFWorkbook(fileInputStream);
			  wb = new HSSFWorkbook(fileInputStream);
//	 	  }else if ("".equals(suffix)) {
		  }else if ("xlsx".equals(suffix)) {
//			 System.out.println("走excel2007解析");
			 System.out.println("走excel2007解析");
//			 wb=new XSSFWorkbook(fileInputStream);
			 wb=new XSSFWorkbook(fileInputStream);
//		  }else{
		  }else{
//			 System.out.println("您本次上传的文件不是excel，退出程序");
			 System.out.println("您本次上传的文件不是excel，退出程序！");
//			  System.exit(0);
			 System.exit(0);
		  }
		}
		
		//4.读取excel中的数据
//		 sheet=wb.getSheetAt(0);//读取第一页
		sheet=wb.getSheetAt(0);
//		 int totalRowNums = sheet.getPhysicalNumberOfRows();//获取当前sheet中一共有多少行
		int totalRowNums = sheet.getPhysicalNumberOfRows();
//		 for(int i =1;i<totalRowNums;i++){
		for(int i = 1;i<totalRowNums;i++){
//			  row= sheet.getRow(i);//获取当前sheet中的每一行(不含表头)
			row = sheet.getRow(i);
//			  int totalColumnNums = row.getPhysicalNumberOfCells();//获取当前行中有多少列
			int totalCoumnNums = row .getPhysicalNumberOfCells();
//			  List<String> columnValuesList=new ArrayList<String>();//创建一个容器用来装读取出来的数据的
			List<String> columnValuesList = new ArrayList<String>();
//			  for(int k=0;k<totalColumnNums;k++){
			for(int k = 0;k<totalCoumnNums;k++){
//				 Cell cell = row.getCell(k);
				 Cell cell = row.getCell(k);
//				 String cellValue = ExcelUtils.getFormatValue(cell);
				 String cellValue = ExcelUtils.getFormatValue(cell);
//				 System.out.println(cellValue);
				 System.out.println(cellValue);
//				 columnValuesList.add(k, cellValue);
				 columnValuesList.add(k,cellValue);
			  }
//			  examineeService.saveByImportExcel(columnValuesList);
			  examineeService.saveByImportExcel(columnValuesList);
//			  System.out.println("开始读取新的一行数据"); 
			  System.out.println("开始读取新的一行数据");
		 }
		//3.重定向
//		response.sendRedirect(request.getContextPath()+"/manage/examinee.do?method=query");	 
		response.sendRedirect(request.getContextPath()+"/manage/examinee.do?method=query");
}
/**
 * @desc  导出excel
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 */
private void doExportExcel(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
//		System.out.println("ExamineeServlet--->doExportExcel()");
		System.out.println("ExamineeServlet-->doExportExcel()");
		//0.接值：接list.jsp传递过来的查询条件的值
//		String classs_id=request.getParameter("clasId");
		String classs_id=request.getParameter("clasId");
//		String key=request.getParameter("ky");
		String key=request.getParameter("ky");
//	    System.out.println("classs_id:"+classs_id);
	    System.out.println("classs_id:"+classs_id);
//	    System.out.println("key:"+key);
	    System.out.println("key:"+key);
	   
//	    String filename="考生信息.xls";
	    String filename="考生信息.xls";
//	    excel.settings(request, response, filename);
	    excel.settings(request, response, filename);
		
		//1.获取数据
//		List<Map<String, Object>> list_examinee = examineeService.queryExaminee(classs_id, key);
		List<Map<String, Object>> list_examinee = examineeService.queryExaminee(classs_id, key);
		//2.写入excel中
		//2.1初始化poi的核心类，产生一个工作簿，并创建一个sheet，且命名
//		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFWorkbook workbook = new HSSFWorkbook();
//		String sheetname="考生的详细信息";
		String sheetname = "考生的详细信息";
//		HSSFSheet sheet = workbook.createSheet(sheetname);
		HSSFSheet sheet = workbook.createSheet(sheetname);
		//2.2设定表头
//		String[] tableTop = { "考生编号", "班级编号", "考生姓名", "登陆密码","性别","提问","答案","专业","身份证号","日期" };
//		String[] columnName = { "examinee_id","classs_id","examinee_name","examinee_pass","examinee_sex","examinee_question","examinee_answer","examinee_specialty","examinee_identity","examinee_time"};
	    String[] tableTop = {"考生编号","班级编号","考生姓名","登录密码","性别","提问","答案","专业","身份证号","日期"};
	    String[] columnName = {"examinee_id","classs_id","examinee_name","examinee_pass","examinee_sex","examinee_question","examinee_answer","examinee_specialty","examinee_identity","examinee_time"};
		//创建表头
//		HSSFRow row = sheet.createRow(0);//创建第一行
		HSSFRow row = sheet.createRow(0);
//		for(int i =0;i<tableTop.length;i++){
		for (int i = 0; i < tableTop.length; i++) {
	     //row.createCell(i).setCellValue(tableTop[i]);		
	     row.createCell(i).setCellValue(tableTop[i]);
		}
		
		//2.3从第二行开始向表格中添加数据
//		for(int i =0;i<list_examinee.size();i++){
		for(int i = 0;i<list_examinee.size();i++){
//	     HSSFRow row2 = sheet.createRow(i+1);
		 HSSFRow row2 = sheet.createRow(i+1);
//	 	 sheet.autoSizeColumn(i, true);//poi自带的解决表格中的数据自动适配宽度（对中文不太好使）
		 sheet.autoSizeColumn(i,true);
//		 Map<String, Object> map = list_examinee.get(i);//取出list_examinee中的map,其实就是数据库表中的一行数据	
		 Map<String, Object> map = list_examinee.get(i);
//		  for(int k=0;k<columnName.length;k++){
		 for(int k = 0;k<columnName.length;k++){
//			 row2.createCell(k).setCellValue((String)map.get(columnName[k]));
			 row2.createCell(k).setCellValue((String)map.get(columnName[k]));
		 }
		}
//		excel.setColumnAutoAdapter(sheet, list_examinee.size());
        excel.setColumnAutoAdapter(sheet, list_examinee.size());
		//通过流写入到工作簿中
//        OutputStream out = response.getOutputStream();
        OutputStream out = response.getOutputStream();
//        workbook.write(out);
        workbook.write(out);
//        out.close();
        out.close();
}

private void doSy(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {
	response.setContentType("text/html;charset=utf-8");
	synchronized (ExamineeServlet.class) {
		response.getWriter().write("这是第"+i+"次访问...");
		Thread.sleep(5000);
		i++;
	}
}

@Override
public void delete(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ClassNotFoundException, SQLException {
	System.out.println("ExamineeServlet--->delete()");
	//接值:多行的准考证号
	String[] delIdArray = request.getParameterValues("delIdArray");
	//调用M层实现批量删除
	examineeService.deleteByIdArray(delIdArray);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/examinee.do?method=query");
}

@Override
public void edit(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ClassNotFoundException, SQLException {
	//接值
	String examinee_id = request.getParameter("examinee_id");
	System.out.println("examinee_id:"+examinee_id);
	String examinee_name = request.getParameter("examinee_name");
	System.out.println("examinee_name:"+examinee_name);
	String examinee_sex = request.getParameter("examinee_sex");
	System.out.println("examinee_sex:"+examinee_sex);
	String examinee_time = request.getParameter("examinee_time");
	System.out.println("examinee_time:"+examinee_time);
	String examinee_question = request.getParameter("examinee_question");
	System.out.println("examinee_question:"+examinee_question);
	String examinee_identity = request.getParameter("examinee_identity");
	System.out.println("examinee_identity:"+examinee_identity);
	synchronized (this) {
		//调用M层中的方法实现更新
		examineeService.update(examinee_id, examinee_name,examinee_sex,examinee_time,examinee_question,examinee_identity);
	}
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/examinee.do?method=query");
}

@Override
public void editPage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, ClassNotFoundException, SQLException {
	//接值
	String examinee_id = request.getParameter("examinee_id");
	System.out.println("examinee_id: "+examinee_id);
	//查询该条数据信息
	Map<String, Object> map_examinee = examineeService.queryClasssById(examinee_id);
	//存值
	request.setAttribute("map_examinee", map_examinee);
	//转向
	request.getRequestDispatcher("/manage/examinee/edit.jsp").forward(request, response);
}

@Override
public void add(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ClassNotFoundException, SQLException {
	//接值
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	String examinee_id = request.getParameter("examinee_id");
	System.out.println("examinee_id:"+examinee_id);
	String examinee_name = request.getParameter("examinee_name");
	System.out.println("examinee_name:"+examinee_name);
	String examinee_sex = request.getParameter("examinee_sex");
	System.out.println("examinee_sex:"+examinee_sex);
	String examinee_time = request.getParameter("examinee_time");
	System.out.println("examinee_time:"+examinee_time);
	String examinee_question = request.getParameter("examinee_question");
	System.out.println("examinee_question:"+examinee_question);
	String examinee_identity = request.getParameter("examinee_identity");
	System.out.println("examinee_identity:"+examinee_identity);
	//调用M层中的方法实现保存
	examineeService.save(examinee_id,classs_id,examinee_name,examinee_sex,examinee_time,examinee_question,examinee_identity);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/examinee.do?method=query");
}

@Override
public void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	String classs_name = request.getParameter("classs_name");
	System.out.println("classs_name:"+classs_name);
	List<Map<String, Object>> list_classs = examineeService.queryClasss(classs_id,classs_name);
	request.setAttribute("list_classs", list_classs);	
	request.getRequestDispatcher("/manage/examinee/add.jsp").forward(request, response);
}

@Override
public void query(HttpServletRequest request, HttpServletResponse response)
		throws ClassNotFoundException, SQLException, ServletException, IOException {
	System.out.println("ExamineeServlet-->query()");
	//接值:目的是为了实现条件查询
	String classs_id = request.getParameter("classs_id");
	String key = request.getParameter("key");
	System.out.println("classs_id-->"+classs_id);
	System.out.println("key-->"+key);
	//调用M层查询数据库获取到数据
	 List<Map<String, Object>> list_examinee = examineeService.queryExaminee(classs_id,key);
	 List<Map<String, Object>>	list_classs = examineeService.queryClasss();
	//存值
	 request.setAttribute("list_examinee", list_examinee);
	 request.setAttribute("list_classs", list_classs);
	 request.setAttribute("classs_id", classs_id);
	 request.setAttribute("key", key);
	 //转向
	 request.getRequestDispatcher("/manage/examinee/list.jsp").forward(request, response);
	 
	 
}

	
}
