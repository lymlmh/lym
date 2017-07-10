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
<script type="text/javascript">
$(function(){
	var id='${juese.id}';
	$("#showTooltips").click(function(){
	var name=$("#jueseName").val();
	console.debug(name);
		$.ajax({
			type:"Post",
			url:"quanxian/jueseupdate.do",
			dataType:"json",
			data:{name:name,id:id},
			success:function(data){
				if(data.success){
					$.modal({
				          title: "提示",
				          text: "更新角色成功",
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
</script>
<title>更新角色</title>
</head>
<body>
     <header class='demos-header'>
      <h1 class="demos-title">角色更新</h1>
    </header>
	  <form action="#" method="get" >
    <div class="weui-cells weui-cells_form">
      <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">角色名称:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="jueseName" type="text" value="${juese.name}" name="name">
        </div>
      </div>
    </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    </form>
</body>
</html>