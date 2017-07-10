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
	$("#part").click(function(){
		window.location.href="quanxian/part.do";
	});
	$("#update").click(function(){
		if(id!='' && id!=null){
		window.location.href="quanxian/jueseupdate/index.do?id="+id;
		}
    else{
		$.alert("请选择需要更新的角色", "提示！");
	}
	});
	$("#delete").click(function(){
		if(id!='' && id!=null){
			$.ajax({
				type:"Post",
				url:"quanxian/delete.do",
				dataType:"json",
				data:{id:id},
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "删除角色成功",
					          buttons: [
					            { text: "确定", onClick: function(){
					            	 window.location.href="quanxian/index.do";
					            } }
					          ]
					        });
				    }else{
						 $.alert(data.msg, "警告！");
					}
				}
			});
		}else{
			$.alert("请选择需要删除的角色", "提示！");
		}
	});
	
	$(function(){
		$("#showTooltips").click(function(){
		var name=$("#name").val();
		if(name=='' || name==null){
			$.alert("请输入角色名称", "提示！");
			return;
		}
			$.ajax({
				type:"Post",
				url:"quanxian/save.do",
				dataType:"json",
				data:{name:name},
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "新增角色成功",
					          buttons: [
					            { text: "确定", onClick: function(){
									 window.location.href="quanxian/index.do";
					            } }
					          ]
					        });
					}else{
						 $.alert(data.msg, "警告！");
					}
				}
			});
		});
	});
	
	
	$("input[name='radio1']").click(function(){
		id=$(this).val();
	});
});
</script>
<title>角色管理</title>
</head>
<body>
<div class="weui-cells__title">新增角色</div>
      <form action="#" method="get" >
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">角色名称</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" name="name" placeholder="请输入角色名称" id="name">
        </div>
      </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    </form>

	  <div class="button_sp_area">
        <a href="javascript:;" id="delete" class="weui-btn weui-btn_mini weui-btn_default">删除角色</a>
         <a href="javascript:;" id="part" class="weui-btn weui-btn_mini weui-btn_default">角色分配</a>
         <a href="javascript:;" id="update" class="weui-btn weui-btn_mini weui-btn_default">修改角色</a>
      </div>
	<div class="bd">
      <div class="page__bd">
	        <div class="weui-cells__title">角色信息</div>
	         <div class="weui-cells weui-cells_radio" id="role">
	        <c:forEach items="${quanxian}" var="item">
		        <label class="weui-cell weui-check__label" for="x${item.id}">
		          <div class="weui-cell__bd">
		            <p>角色名称：${item.name}</p>
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