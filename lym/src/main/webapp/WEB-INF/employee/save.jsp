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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.">
<link rel="stylesheet" href="js/lib/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script src="js/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
	$("#showTooltips").click(function(){
/* 	var mobile=$("#mobile").val();
	if(mobile!='' || mobile!=null){
		if(mobile.length!=11)
		{
			$.alert("手机格式不对", "提示！");
			return;
		}
	} */
	 $.post("Employee/save.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   window.location.href="Employee/index.do";
		    }else{
		    	$.alert("新增失败，请检查表单数据格式是否正常", "提示！");
		    }
		  });
	});
});
</script>
<title>资产管理系统-成员管理</title>
</head>
<body>
     <header class='demos-header'>
      <h1 class="demos-title">新增成员</h1>
    </header>
	  <form id="search_form" method="post" >
    <div class="weui-cells weui-cells_form">
     	
	     <div class="weui-cell weui-cell_vcode">
	        <div class="weui-cell__hd">
	          <label class="weui-label">姓名:</label>
	        </div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" id="name" type="text" value="" name="name">
	        </div>
        </div>
	     <div class="weui-cell weui-cell_vcode">
	        <div class="weui-cell__hd">
	          <label class="weui-label">帐号:</label>
	        </div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" id="userid" type="text" value="" name="userid" placeholder="成员唯一标识，不支持中文">
	        </div>
        </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">性别:</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="gender" id="gender">
           <option selected="" value="0">请选择性别</option>
           <option value="1">男</option>
           <option value="2">女</option>
          </select>
        </div>
      </div>  
        
       <!--   <div class="weui-cells__title">身份验证信息（以下三种信息不可同时为空）</div> -->
        <!--  <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">微信号:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" value="" name="weixinid" >
        </div>
        </div> -->
        
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">手机:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="number" value="" name="mobile" placeholder="允许手机验证关注，国际手机号需加区号“+”">
        </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">邮箱:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="email" value="" name="email" >
        </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">职位:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" value="" name="position">
        </div>
      </div>
      
        <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">所在部门</label>
        </div>
        <div class="weui-cell__bd">
         <select class="weui-select" name="department">
			<c:forEach items="${depts}" var="item">
			<option value="${item.id}">${item.name}</option>
			 </c:forEach>
		 </select>
        </div>
      </div>
    </div>
    </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    </form>
</body>
</html>