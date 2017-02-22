<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>吉首大学图书管理系统</title>

	<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css"/>
	<link rel="stylesheet" type="text/css" href="css/comm.css"/>
	<script type="text/javascript" src="extjs/bootstrap.js"></script>
	<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="app.js"></script>
	<script type="text/javascript" src="resource/FusionCharts.js"></script>
	<script type="text/javascript" src="resource/FusionChartsExportComponent.js"></script>
	<!--  
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onLoad="startclock()">
  <script type="text/javascript"> 
<!-- Hide 
var timerID = null ;
var timerRunning = false ;
function MakeArray(size) { 
	this.length = size; 
	for(var i = 1; i <= size; i++) { 
		this[i] = ""; 
	} 
	return this; 
} 
function stopclock (){ 
	if(timerRunning) 
		clearTimeout(timerID); 
	timerRunning = false ;
} 
function showtime () { 
	var now = new Date(); 
	var day = now.getDay(); 
	Day = new MakeArray(7); 
	Day[0]="星期日"; 
	Day[1]="星期一"; 
	Day[2]="星期二"; 
	Day[3]="星期三"; 
	Day[4]="星期四"; 
	Day[5]="星期五"; 
	Day[6]="星期六"; 
	document.getElementById("msg").innerHTML = "系统时间：  "+now.toLocaleDateString() + " "+(Day[day]) + " "+ now.toLocaleTimeString(); 
	timerID = setTimeout("showtime()",1000); 
	timerRunning = true ;
} 
function startclock () { 
	stopclock(); 
	showtime() ;
} 
//--> 

</script> 
    <div id="header" class="head">图书管理系统     <font size="2px">当前登录用户：${admin.realName}</font><span id="msg" class="msg"></span></div>
  </body>
</html>
