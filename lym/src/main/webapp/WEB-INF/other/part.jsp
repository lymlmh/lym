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

	 $.post("quanxian/updateemp.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   $.modal({
			          title: "提示",
			          text: "角色分配成功",
			          buttons: [
			            { text: "确定", onClick: function(){
			            	window.location.href="quanxian/part.do";
			            } }
			          ]
			        });  
		    }else{
		    	$.alert("角色分配失败，请检查表单数据格式是否正常", "提示！");
		    }
		  });
	});
});
</script>
<title>角色管理</title>
</head>
<body>
     <header class='demos-header'>
      <h1 class="demos-title">角色分配</h1>
    </header>
	  <form id="search_form" method="post" >
    <div class="weui-cells weui-cells_form">
     	
      
        <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">角色</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="partid">
         	 <c:forEach items="${quanxian}" var="item">
           		 <option value="${item.id}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
           </div>
           
          <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">员工</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="userid">
         	 <c:forEach items="${employee}" var="item">
           		 <option value="${item.userid}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
           </div>
        
    </div>
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    <c:forEach items="${employee}" var="item">
		          <div class="weui-cell">
		            <div class="weui-cell__bd">
		              <p>员工：${item.name}</p>		              
		              <p>角色:${item.quanxian.name}</p>
		            </div>
		          </div>            
	        </c:forEach>
    
    </div>

    
    </form>
</body>
</html>