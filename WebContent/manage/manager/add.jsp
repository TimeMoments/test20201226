<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>网络在线考试――后台管理</title>
		<link href="<%=request.getContextPath() %>/CSS/style.css" rel="stylesheet"></link>
		<script type="text/javascript" src="<%=request.getContextPath() %>/jquery/jquery.js"></script>
		<script type="text/javascript">
		  function imgPreview(fileDom){
			  //alert("开始实现预览。。。。");
	           if(window.FileReader){
	           	var fileReader=new FileReader();
	           }else{
	           	 alert("您当前使用的设备不支持，请升级....");
	           	 return false;
	           }
	          // alert("继续");
	           
	       	//2.通过js的组合选择器获取到文件域
	       	var file=fileDom.files[0];
	       	//alert("file:"+file);
	       	
	    	//3.设定一个教验的正则表达式
	    	var imageType=/^image\//;
	    	if(!imageType.test(file.type)){
	    		alert("请选择图片");
	    		document.getElementById("spid").innerHTML="<input type=\"file\" name=\"image\"  onchange=\"imgPreview(this)\"   id=\"imgFile\"/>";
	    		alert("文件选择清空了.....");
	    		return false;
	    	}
	       	
	    	fileReader.onload = function(){ //当文件域中读取完成之后才触发图片标签
		        //获取图片dom
		        var img = document.getElementById("preview");
		        //图片路径设置为读取的图片
		        //alert("this--->"+this);//从弹框可以看出是FileReader对象
		        //alert("this.result--->"+this.result);//this.result可以看出是一个data开头的字符串，这段字符串的实质就是 Data URL
		        img.src = this.result;//相当于是为图片标签设置了src的url值
		    };
		    fileReader.readAsDataURL(file);//将文件域标签中的内容显示在图片标签的位置
		  }
		  
		  function checkForm(){
			//alert("1.文件域的非空验证.....");
			var imgFileValue=document.getElementById("imgFile").value;
			//alert("imgFileValue-->"+imgFileValue);
			if(imgFileValue==""){
				alert("请选择用户头像");
				return false;
			}  
			  
			//alert("2.图片的大小验证");
		    var	imgFileElement=document.getElementById("imgFile");
		    var fileData=imgFileElement.files[0];
		    //alert("fileData:"+fileData);
		    var size=fileData.size;
			//alert("size:"+size);
			if(size>1024*1024){
				alert("您本次上传的图片超过1MB了,请选择一个小点的再上传.....");
				return false;
			} 
		  }
		  
		  function doAjax() {
				//1.获取到管理员的姓名
				var gname=$("#gname").val();
				var url = "<%=request.getContextPath()%>/manage/manager.do?method=queryAjax";
				$.post(url,{manager_name:gname},function(data){
					tianxie(data);
				})
			}
			
			function tianxie(flag){
				if("no"==flag){
					$("#gname").html(alert("该管理员名称已存在，请更改名称！"));
					return false;
				}
				
			}
			function retianxie(){
				
				//$("#gname").html("");
				//$("#gname").val("");
				//document.getElementById("gname").value="";
				
			}
			
			
		</script>
	</head>
	<body>
	
		<%@ include file="/manage/top.jsp"%>
		
		<table width="960" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr>
				<td width="176" align="center" valign="top" bgcolor="#FFFFFF">
				
					<%@ include file="/manage/left.jsp"%>
					
				</td>
				<td align="right" valign="top" bgcolor="#FFFFFF">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td height="30" bgcolor="#EEEEEE" class="tableBorder_thin">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="78%" class="word_grey" align="left">
											&nbsp;当前位置：<span class="word_darkGrey">管理员信息管理 &gt;&gt;添加管理员&gt;&gt;</span>
										</td>
										<td align="right">
											<img src="<%=request.getContextPath() %>/Images/m_ico1.gif" width="5" height="9">&nbsp;
											当前管理员：<%=((Map)session.getAttribute("user")).get("manager_name") %>&nbsp;
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<table width="100%"  border="0" cellspacing="0" cellpadding="0">
									<tr>
								   		<td width="84%">&nbsp;      </td>
									</tr>
								</table> 
								
								<form name="form1" method="post" action="<%=request.getContextPath()%>/manage/manager.do?method=upload"  onsubmit="return checkForm()" enctype="multipart/form-data">
									 <table border="2px" bordercolor="green"  align="center">
							         <tr>
							           <td>管理员名字：</td>
							           <td>
							           	<input type="text"  name="manager_name" id="gname" value=""  onblur="doAjax()" onfocus="retianxie()" />
							           </td>
							           <td rowspan="3"><img id="preview"   style="width:130px;height:130px"  src="<%=request.getContextPath()%>/Images/dp.png"  /></td>
							         </tr>
							         
							          <tr>
							           <td>管理员密码：</td>
							           <td><input type="text"  name="manager_pass"  value=""/></td>
							         </tr>
							         
							         <tr>
							         <td>管理员头像：</td>
							          <td> <span id="spid"><input   type="file"   name="image"  onchange="imgPreview(this)" accept="image/*"   id="imgFile"/></span></td>
							         </tr>
							         <tr>
							           <td colspan="3"><input  style="float: right" type="submit" value="提交"  onclick="return doAjax()" /></td>
							         </tr>
							        </table>
								</form>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<%@ include file="/manage/copyright.jsp"%>
	</body>
</html>
