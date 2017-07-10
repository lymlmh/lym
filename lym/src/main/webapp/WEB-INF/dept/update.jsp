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
	var id='${dept.id}';
	$("#showTooltips").click(function(){
	var parentid=$("#parentid").val();
	var name=$("#deptName").val();
	console.debug(name);
		$.ajax({
			type:"Post",
			url:"dept/update.do",
			dataType:"json",
			data:{name:name,id:id,parentid:parentid},
			success:function(data){
				if(data.success){
					$.modal({
				          title: "提示",
				          text: "更新部门成功",
				          buttons: [
				            { text: "确定", onClick: function(){
								 window.location.href="dept/index.do";
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
<title>更新部门</title>
</head>
<body>
	  <form action="#" method="get" >
    <div class="weui-cells weui-cells_form">
      <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">部门名称:</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" id="deptName" type="text" value="${dept.name}" name="name">
        </div>
      </div>
        <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
       <div class="weui-cell__hd">
          <label class="weui-label">父部门:</label>
        </div>
        <div class="weui-cell__bd">
          <select class="weui-select" name="parentid" id="parentid">
           <option selected="" value="1"></option>
         	 <c:forEach items="${depts}" var="item">
            <option value="${item.id}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
       <div class="weui-cell__hd">
          <label class="weui-label">部门ID：${dept.id}</label>
        </div>
    </div>
    </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
    </form>
</body>
</html>