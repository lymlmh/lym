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
 	 $("#flows .weui-cell_access").click(function(){
		var id=$(this).attr("data");
		window.location.href="flow/item2.do?id="+id;
	});  
});
function audit(id,status){
	$.ajax({
		type:"Post",
		url:"assets/item.do",
		dataType:"json",
		data:{id:id,},
	});
}
</script>
<title>部门审核列表</title>
</head>
<body>
 <div class="weui-search-bar" id="searchBar">
      <form class="weui-search-bar__form">
       	
      </form>
</div>
	<div class="bd">
      <div class="page__bd">
	        <div class="weui-cells__title">部门审核列表</div>
	        <div class="weui-cells" id="flows">
	        <c:if test="${noPage==true}">
	        	<div class="weui-loadmore weui-loadmore_line">
		        <span class="weui-loadmore__tips">暂无数据</span>
		        </div>
	        </c:if>
	        <c:forEach items="${flows}" var="item">
	            <c:if test="${item.audit!=2}">
		          <div class="weui-cell">
		            <div class="weui-cell__bd">
		              <p>资产编号：${item.fa.code}</p>
		              <p>资产名称：${item.fa.name}</p>
		              <p>申请:${item.statusStr}</p>
		              <p>申请人:${item.applyername}</p>
		              <p>状态:${item.auditStr}</p>
		              <p>审核人:${item.auditer}</p>
		              <p>审核时间:<fmt:formatDate value="${item.auditTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></p>
		            </div>
		            <c:choose>
					   <c:when test="${item.auditStatus==1}"> 
					       <div class="weui-cell__ft" style="color:#00ff00">${item.auditStatusStr}</div>      
					   </c:when>
					   <c:otherwise>
					  	   <div class="weui-cell__ft" style="color:rgb(0,0,255)">${item.auditStatusStr}</div>
					   </c:otherwise>
					</c:choose>
		          </div>
	            </c:if>
	            <c:if test="${item.audit==2}">
		          <a class="weui-cell weui-cell_access" data="${item.id}" href="javascript:;">
		           <div class="weui-cell__bd">
		              <p>资产编号：${item.fa.code}</p>
		              <p>资产名称：${item.fa.name}</p>
		              <p>申请:${item.statusStr}</p>
		              <p>申请人:${item.applyername}</p>
		              <p>状态:${item.auditStr}--${item.auditStatusStr}</p>
		            </div>
		            <div class="weui-cell__ft">审核</div>
	         	 </a>
	            </c:if>
	        </c:forEach>
        </div>
      </div>
    </div>
</body>
</html>