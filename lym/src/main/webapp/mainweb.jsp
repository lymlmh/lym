<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
 <meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.">
<link rel="stylesheet" href="js/lib/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript">
$(function(){
	$("#save").click(function(){
		window.location.href="assets/save/index.do";
	});
	$("#renyuan").click(function(){
		window.location.href="Employee/index.do";
	});
	$("#quanxian").click(function(){
		window.location.href="role/index.do";
	});
	$("#juese").click(function(){
		window.location.href="role/test.do";
	});
	$("#geren").click(function(){
		//window.location.href="assets/personal.do";
		window.location.href="other/didian.do";
		//window.location.href="role/quanxian.do";
	});
	$("#quanxian").click(function(){
		
		window.location.href="role/quanxian.do";
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
	  <div class="button_sp_area">
        <a href="javascript:;" id="save" class="weui-btn weui-btn_mini weui-btn_primary">部门管理</a>
        <a href="javascript:;" id="renyuan" class="weui-btn weui-btn_mini weui-btn_default">人员管理</a>
        <a href="javascript:;" id="quanxian" class="weui-btn weui-btn_mini weui-btn_default">角色管理</a>
        <a href="javascript:;" id="juese" class="weui-btn weui-btn_mini weui-btn_default">角色管理test</a>
        <a href="javascript:;" id="geren" class="weui-btn weui-btn_mini weui-btn_default">个人资产</a>
        <a href="javascript:;" id="quanxian" class="weui-btn weui-btn_mini weui-btn_default">地点管理</a>
      </div>
</body>
</html>