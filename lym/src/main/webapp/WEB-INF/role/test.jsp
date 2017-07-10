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
		window.location.href="role/save/index.do";
	});
	$("#chaxun").click(function(){
		window.location.href="role/test.do";
	});
	$("#update").click(function(){
		if(userid!='' && userid!=null){
	      	window.location.href="role/update/index.do?id="+id;
		}
	});
	
	$("#delete").click(function(){
		if(id!='' && id!=null){
			$.ajax({
				type:"Post",
				url:"role/delete.do",
				dataType:"json",
				data:{id:id},
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "删除成功",
					          buttons: [
					            { text: "确定", onClick: function(){
					            	 window.location.href="role/test.do";
					            } }
					          ]
					        });
				    }else{
						 $.alert(data.msg, "警告！");
					}
				}
			});
		}else{
			$.alert("请选择需要删除的资产管理员", "提示！");
		}
	});
	
	$("#searchInput").blur(function(){
		var search=$("#searchInput").val();
		$.ajax({
			type:"Post",
			url:"role/search.do",
			dataType:"json",
			data:{search:search},
			success:function(data){
				if(data){
					var roles='';
					if(data.length<=0){
						roles=' <div class="weui-loadmore weui-loadmore_line">'+
					        '<span class="weui-loadmore__tips">暂无数据</span>'+
					        '</div>';
					}else{
						var empls='<label class="weui-cell weui-check__label" for="x#userid">'+
					      '<div class="weui-cell__bd">'+
			              '<p>名称：#name</p>'+
				            '<p>管理部门：#dept</p>'+
				            '<p>管理员：#user</p>'+
			              '</div>'+
			              '<div class="weui-cell__ft">'+
			              '<input type="radio" class="weui-check" name="radio1" value="#userid" id="x#userid">'+
			              '<span class="weui-icon-checked"></span>'+
			              '</div>'+
			              '</label>';
						$.each(data,function(index,item){
							roles +=empls.replace("#userid", item.id).replace("#name", item.name).replace("#dept", item.dept.name).replace("#user", item.user.name);
						});
					}
					$("#role").html(roles);
			    }else{
					 $.alert(data.msg, "警告！查询失败");
				}
			}
		});
	});
	
	$("input[name='radio1']").click(function(){
		id=$(this).val();
	});
});
</script>
<title>资产管理员设置</title>
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
        <a href="javascript:;" id="save" class="weui-btn weui-btn_mini weui-btn_primary">新增</a>
        <!-- <a href="javascript:;" id="update" class="weui-btn weui-btn_mini weui-btn_default">更新角色</a> -->
        <a href="javascript:;" id="delete" class="weui-btn weui-btn_mini weui-btn_default">删除</a>
        <a href="javascript:;" id="chaxun" class="weui-btn weui-btn_mini weui-btn_default">查询所有</a>
      </div>
	<div class="bd">
      <div class="page__bd">
	        <div class="weui-cells__title">资产管理员信息</div>
	         <div class="weui-cells weui-cells_radio" id="role">
	        <c:forEach items="${roles}" var="item">
		        <label class="weui-cell weui-check__label" for="x${item.id}">
		          <div class="weui-cell__bd">
		            <p>名称：${item.name}</p>
		            <p>管理部门：${item.dept.name}</p>
		            <p>管理员：${item.user.name}</p>
		          </div>
		          <div class="weui-cell__ft">
		            <input type="radio" class="weui-check" name="radio1" value="${item.id}" id="x${item.id}">
		            <span class="weui-icon-checked"></span>
		          </div>
		        </label>
	        </c:forEach>
      </div>
        </div>
      </div>
</body>
</html>