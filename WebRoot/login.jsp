<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>吉首大学图书管理系统-登录</title>
    <script type = "text/javascript">
	function validate(){
		if(loginform.adminName.value==""){
			alert("请输入用户名！");
			return false;
		}
		if(loginform.password.value==""){
			alert("请输入密码！");
			return false;
		}
		if(loginform.code.value==""){
			alert("请输入验证码！");
			return false;
		}
		if(loginform.adminName.value==""&&loginform.password.value==""&&loginform.code.value==""){
			alert("请输入");
			return false;
		}
		return true;
	}

</script>
    
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    </head>
  
  <body>
	  <div id = center>
		<div id = login>
	    	<div id = login-left>
	    		<form action = "login" method = "post" name="loginform" onsubmit="return validate()">
	    			<table class = "tab">
	    				<tr>
	    					<td colspan = "2" align="center">${sessionScope.error}</td>
	    				</tr>
	    				
	    				<tr class = "tr">
	    					<td align="right" width="100">用户名：</td>
	    					<td colspan = "2" width="220"><input type = "text" name = "adminName"  ></td>
	    				</tr>
	    				
	    				<tr class = "tr">
	    					<td align="right" width="100">密&nbsp;&nbsp;码：</td>
	    					<td colspan = "2" width="220"><input type = "password" name = "password"></td>
	    				</tr>
	    				
	    				<tr class = "tr">
	    					<td align="right" width="100">验证码：</td>
	    					<td width = "60"><input type = "text" name = "code" style = "width:60px;"></td>
	    					<td align = "left" width = "100"><img style = "vertical-align:middle;" src="images/image.jsp"></td>
	    				</tr>
	    				
	    				<tr class = "tr">
	    					<td width="100" align = "right"><a href = "#">忘记密码?</a></td>
	    				</tr>
	    				
	    				<tr>
	    					<td colspan = "3"><input type = "submit" value = "登&nbsp;&nbsp;录" style = "width:80px;height:25px;float: right;"></td>
	    				</tr>
	    			</table>
	    		</form>
	    	</div>
	    
	    	<div id = login-right>
	    		<table>
	    			<tr>
	    				<td><embed width="330" height="152" src="images/banner.swf" /></td>
	    			</tr>
	    		</table>
	    	</div>
	    </div>
	 </div>
 </body>
 </html>