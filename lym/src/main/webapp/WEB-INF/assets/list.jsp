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
	$("#create").click(function(){
			$.ajax({
				type:"Post",
				url:"assets/create.do",
				dataType:"json",
				data:{},
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "生成二维码成功",
					          buttons: [
					            { text: "确定", onClick: function(){
					            	 window.location.href="assets/qiye/list.do?state=0";
					            } }
					          ]
					        });
				    }else{
						 $.alert(data.msg, "生成二维码失败警告！");
					}
				}
			});
	});
	$("#save").click(function(){
		window.location.href="assets/save/index.do";
	});
	$("#pandian").click(function(){
		window.location.href="assets/clean.do";
	});
	$("#searchInput").blur(function(){
		var search=$("#searchInput").val();
		$.ajax({
			type:"Post",
			url:"assets/search.do",
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
						var empls='<a class="weui-cell weui-cell_access" href="assets/assets.do?id=#id">'+
			            			'<div class="weui-cell__bd">'+
			              			'<p>资产名称：#name</p>'+
			              			'<p>使用部门：#dept</p>'+
			              			'<p>使用人：#user</p>'+
			              			'<p>盘点情况：#pandian</p>'+
			          				'</div>'+
			           				 '<div class="weui-cell__ft">#statusStr</div>'+
			         				 '</a>';
						$.each(data,function(index,item){
							employees +=empls.replace("#id", item.id).replace("#name", item.name)
							.replace("#dept", item.dept.name).replace("#user", item.user.name).replace("#pandian", item.pandianStr).replace("#statusStr", item.statusStr);
						});
					}
					$("#employee").html(employees);
			    }else{
					 $.alert(data.msg, "警告！");
				}
			}
		});
	});
});
</script>
<title>公司资产信息管理</title>
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
    <a href="javascript:;" id="save" class="weui-btn weui-btn_mini weui-btn_primary">新增资产</a>
    <a href="javascript:;" id="pandian" class="weui-btn weui-btn_mini weui-btn_primary">重新对资产盘点</a>
    <a href="javascript:;" id="create" class="weui-btn weui-btn_mini weui-btn_primary">重新生成二维码</a>
  </div>
	<div class="bd">
      <div class="page__bd">
	        <div class="weui-cells__title">资产信息</div>
	        <div class="weui-cells" id="employee">
	        <c:forEach items="${fa}" var="item">
	          <a class="weui-cell weui-cell_access" href="assets/assets.do?id=${item.id}">
	            <div class="weui-cell__bd">
	              <p>资产名称：${item.name}</p>
	              <p>使用部门：${item.dept.name}</p>
	              <p>使用人：${item.user.name}</p>
	              <p>盘点情况：${item.pandianStr}</p>
	            </div>
	            <div class="weui-cell__ft">${item.statusStr}</div>
	          </a>
	        </c:forEach>
        </div>
      </div>
    </div>
</body>
</html>