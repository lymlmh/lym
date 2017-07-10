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

	 $.post("quanxian/update.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   $.modal({
			          title: "提示",
			          text: "权限分配成功",
			          buttons: [
			            { text: "确定", onClick: function(){
			            	window.location.href="quanxian/power.do";
			            } }
			          ]
			        });			   
		    }else{
		    	$.alert("权限分配失败，请检查表单数据格式是否正常", "提示！");
		    }
		  });
	});
});
</script>
<title>权限管理</title>
</head>
<body>
     <header class='demos-header'>
      <h1 class="demos-title">权限分配</h1>
    </header>
	  <form id="search_form" method="post" >
    <div class="weui-cells weui-cells_form">
     	
      
        <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">角色</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="id">
         	 <c:forEach items="${quanxian}" var="item">
           		 <option value="${item.id}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
           </div>
           
            <div class="weui-cell weui-cell_select">
           <div class="weui-cell__hd">
          <label class="weui-label">部门管理</label>
        </div>
                <div class="weui-cell__bd">
          <select class="weui-select" name="ifbumen"">
           		 
           		 <option value="2">不允许</option> 
           		 <option value="1">允许</option> 
          </select>
        </div>
        </div>
        
        <div class="weui-cell weui-cell_select">
           <div class="weui-cell__hd">
          <label class="weui-label">人员管理</label>
        </div>
                <div class="weui-cell__bd">
          <select class="weui-select" name="ifrenyuan">
           		
           		 <option value="2">不允许</option> 
           		  <option value="1">允许</option> 
          </select>
        </div>
        </div>
        <div class="weui-cell weui-cell_select">
           <div class="weui-cell__hd">
          <label class="weui-label">地点管理</label>
        </div>
                <div class="weui-cell__bd">
          <select class="weui-select" name="ifdidian">
           		
           		 <option value="2">不允许</option> 
           		  <option value="1">允许</option> 
          </select>
        </div>
        </div>
        <div class="weui-cell weui-cell_select">
           <div class="weui-cell__hd">
          <label class="weui-label">权限管理</label>
        </div>
                <div class="weui-cell__bd">
          <select class="weui-select" name="ifquanxian">
           		
           		 <option value="2">不允许</option> 
           		  <option value="1">允许</option> 
          </select>
        </div>
        </div>
        <div class="weui-cell weui-cell_select">
           <div class="weui-cell__hd">
          <label class="weui-label">角色管理</label>
        </div>
                <div class="weui-cell__bd">
          <select class="weui-select" name="ifjuese">
           		
           		 <option value="2">不允许</option> 
           		  <option value="1">允许</option> 
          </select>
        </div>
        </div>
        
    </div>
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    <c:forEach items="${quanxian}" var="item">
		          <div class="weui-cell">
		            <div class="weui-cell__bd">
		              <p>角色名：${item.name}</p>
		              
		              <p>部门管理:${item.ifbumenStr}</p>
		              <p>人员管理:${item.ifrenyuanStr}</p>
		              <p>地点管理:${item.ifdidianStr}</p>
		              <p>权限管理:${item.ifquanxianStr}</p>
		              <p>角色管理:${item.ifjueseStr}</p>
		            </div>
		          </div>            
	        </c:forEach>
    
    </div>

    
    </form>
</body>
</html>