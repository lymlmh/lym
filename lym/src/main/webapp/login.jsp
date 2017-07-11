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
    <title>首页</title>
	
	<link rel="stylesheet" href="common/layui/css/layui.css">
	<link rel="stylesheet" href="common/css/sccl.css">
	<link rel="stylesheet" href="js/lib/weui.min.css">
    <link rel="stylesheet" href="css/jquery-weui.css">
	<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script src="js/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
$("#login").click(function(){
	var name=$("#userName").val();
	var pass=$("#password").val();

	if(name=='' || name==null){
		$.alert("请输入用户名", "提示！");
		return;
	}
	if(pass=='' || pass==null){
		$.alert("请输入密码", "提示！");
		return;
	}
	
	$.ajax({
		type:"Post",
		url:"index/login.do",
		dataType:"json",
		data:{name:name,pass:pass},
		success:function(data){
			if(data.success){
				//alert("登录成功");
				$.modal({
			          title: "提示",
			          text: "登录成功",
			          buttons: [
			            { text: "确定", onClick: function(){
			            	window.location.href="index/main.do";
			            } }
			          ]
			        });
			}else{
				 $.alert("密码或者用户名错误", "警告！");
				 document.getElementById("userName").value="";
				 document.getElementById("password").value="";
				 //window.location.href="login.jsp";
			}
		}
	});

});
});
</script>
    
  </head>
  
  <body class="login-bg">
    <div class="login-box">
        <header>
            <h1>后台管理</h1>
        </header>
        <div class="login-main">
			<form  method="post" id="user">
				               
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" id="userName" name="userName"  autocomplete="off" placeholder="请输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="password" id="password" name="password"  autocomplete="off" placeholder="请输入密码" class="layui-input">
				</div>
				
			</form>  
			<div class="layui-form-item">
			<a  id="login"  href="javascript:" class="pull-right layui-btn layui-btn-primary" >登录</a>

				</div>      
		</div>     
    </div>
</body>
</html>
