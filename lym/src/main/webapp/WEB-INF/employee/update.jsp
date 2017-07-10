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
	 $.post("Employee/update.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   window.location.href="Employee/index.do";
		    }else{
		    	$.alert("更新失败，请检查表单数据格式是否正常", "提示！");
		    }
		  });
	});
});
</script>
<title>资产管理系统-成员管理</title>
</head>
<body>
	  <form id="search_form" method="post" >
	  <input type="hidden" value="${employee.userid}" name="userid">
    <div class="weui-cells weui-cells_form">
     <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
	       <div class="weui-media-box__hd">
              <img class="weui-media-box__thumb" src="${employee.avatar}" alt="">	</div>
          </a>
	     <div class="weui-cell weui-cell_vcode">
	        <div class="weui-cell__hd">
	          <label class="weui-label">姓名:</label>
	        </div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" id="name" type="text" value="${employee.name}" name="name">
	        </div>
        </div>
      <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">账号:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" value="${employee.userid}" name="userid" disabled="disabled">
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
          <script type="text/javascript">
          	var gender='${employee.gender}';
          	$("#gender").val(gender);
          </script>
        </div>
      </div>  
        
        <%--  <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">微信号:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" value="${employee.weixinid}" name="weixinid">
        </div>
        </div> --%>
        
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">手机:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="number" value="${employee.mobile}" name="mobile">
        </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">邮箱:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="email" value="${employee.email}" name="email">
        </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">职位:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" value="${employee.position}" name="position">
        </div>
      </div>
      
        <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">所属部门:</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="department" id="departmen">
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