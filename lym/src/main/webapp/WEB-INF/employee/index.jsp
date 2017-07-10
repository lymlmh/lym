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
	var id='';
	$("#save").click(function(){
		window.location.href="Employee/save/index.do";
	});
	$("#chaxun").click(function(){
		window.location.href="Employee/index.do";
	});
	$("#searchInput").blur(function(){
		var search=$("#searchInput").val();
		$.ajax({
			type:"Post",
			url:"Employee/search.do",
			dataType:"json",
			data:{search:search},
			success:function(data){
				if(data){
					var employees='';
					if(data.length<=0){
						employees=' <div class="weui-loadmore weui-loadmore_line">'+
					        '<span class="weui-loadmore__tips">暂无数据</span>'+
					        '</div>';
					}else{
						var empls='<a class="weui-cell weui-cell_access" href="Employee/item.do?userid=#userid">'+
			            			'<div class="weui-cell__bd">'+
			              			'<p>#name</p>'+
			          				'</div>'+
			           				 '<div class="weui-cell__ft"></div>'+
			         				 '</a>';
						$.each(data,function(index,item){
							employees +=empls.replace("#userid", item.userid).replace("#name", item.name);
						});
					}
					$("#employee").html(employees);
			    }else{
					 $.alert(data.msg, "警告！查询失败");
				}
			}
		});
	});
});
</script>
<title>人员管理</title>
</head>
<body>
 <div class="weui-search-bar" id="searchBar">
      <form class="weui-search-bar__form">
        <div class="weui-search-bar__box">
          <i class="weui-icon-search"></i>
          <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
          <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
        </div>
        <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
          <i class="weui-icon-search"></i>
          <span>搜索</span>
        </label>
      </form>
      <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
</div>
 <div class="button_sp_area">
        <a href="javascript:;" id="save" class="weui-btn weui-btn_mini weui-btn_primary">新增成员</a>
        <a href="javascript:;" id="chaxun" class="weui-btn weui-btn_mini weui-btn_primary">查询所有</a>
       
  </div>
	<div class="bd">
      <div class="page__bd">
	        <div class="weui-cells__title">员工信息</div>
	        <div class="weui-cells" id="employee">
	        <c:forEach items="${employees}" var="item">
	          <a class="weui-cell weui-cell_access" href="Employee/item.do?userid=${item.userid}">
	            <div class="weui-cell__bd">
	              <p>${item.name}</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	          </a>
	        </c:forEach>
        </div>
      </div>
    </div>
</body>
</html>