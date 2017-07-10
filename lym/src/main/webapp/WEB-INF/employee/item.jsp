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
	var state='${employee.status}';
	if(state=='1'){
		document.getElementById("test").innerHTML="状态：  已关注";
	}
	else if(state=='4'){
		document.getElementById("test").innerHTML="状态：  未关注";
	}
	else if(state=='2'){
		document.getElementById("test").innerHTML="状态：  已冻结";
	}
	var userid='${employee.userid}';
	$("#update").click(function(){
		if(userid!='' && userid!=null){
	      	window.location.href="Employee/update/index.do?userid="+userid;
		}
	});
	$("#delete").click(function(){
		if(userid!='' && userid!=null){
			$.ajax({
				type:"Post",
				url:"Employee/delete.do",
				dataType:"json",
				data:{userid:userid},
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "删除成员成功",
					          buttons: [
					            { text: "确定", onClick: function(){
									 window.location.href="Employee/index.do";
					            } }
					          ]
					        });
				    }else{
						 $.alert(data.msg, "警告！删除失败");
					}
				}
			});
		}
	});
});
</script>
<title>人员信息</title>
</head>
<body>
	 <div class="weui-panel__bd">
          <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
            <div class="weui-media-box__hd">
              <img class="weui-media-box__thumb" src="${employee.avatar}" alt="">	</div>
            <div class="weui-media-box__bd">
              <h4 class="weui-media-box__title">${employee.name}</h4>
              <p class="weui-media-box__desc">${employee.genderStr}</p>
            </div>
          </a>
        </div>
        
      <div class="weui-panel weui-panel_access">
	       	<div class="weui-cell__bd">手机&nbsp;&nbsp;&nbsp;
	        <a  class="weui-cell__bd" href="tel:${employee.mobile}" style="color: #8b8b8b;text-decoration: none;">${employee.mobile}</a>
	    	 </div>
        </div>
        
         <%--  <div class="weui-panel weui-panel_access">
       <div class="weui-cell__bd">微信&nbsp;&nbsp;&nbsp; ${employee.weixinid}
     </div>
     </div> --%>
        
      <div class="weui-panel weui-panel_access">
       <div class="weui-cell__bd">邮箱&nbsp;&nbsp;&nbsp; ${employee.email}
     </div>
     </div>
     
      <div class="weui-panel weui-panel_access">
       <div class="weui-cell__bd">部门&nbsp;&nbsp;&nbsp; ${employee.deptName}
     </div>
      </div>
      <div class="weui-panel weui-panel_access">
       <div class="weui-cell__bd">职位&nbsp;&nbsp;&nbsp; ${employee.position}
     </div>
     </div>
        <div class="weui-panel weui-panel_access">
       <div class="weui-cell__bd" id="test">状态&nbsp;&nbsp;&nbsp; ${employee.status}
     </div>
     </div>
   <div class="button_sp_area">
   <a href="javascript:;" id="update" class="weui-btn weui-btn_mini weui-btn_primary">更新成员</a>
   <a href="javascript:;" id="delete" class="weui-btn weui-btn_mini weui-btn_primary">删除成员</a>
  </div>
</body>
</html>