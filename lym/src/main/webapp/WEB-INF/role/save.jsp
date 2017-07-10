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
	$("#showTooltips").click(function(){
	var name=$("#name").val();
	var employeeValue=$("#employee_value").val();
	if(name=='' || name==null){
		$.alert("请输入名称", "提示！");
		return;
	}
	if(employeeValue=='' || employeeValue==null){
		$.alert("请选择管理部门成员", "提示！");
		return;
	}
	 $.post("role/save.do",$("#saveForm").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   window.location.href="role/test.do";
		    }else{
		    	$.alert("新增失败，请检查表单数据格式是否正常", "提示！");
		    }
		 });
	}); 
});
function func(){
	var vs = $('#dept_type option:selected').val();
	//alert(deptId);
	$.ajax({
		dataType:"json",
		type:"POST",
		data:{deptId:vs},
		url:"Employee/getUserSimpleList.do",
		success:function(data){
				var employeeHtml='';
			if(data.success){
				var emploHtml='<option value="#userid">#name</option>';
				var emplos=data.employees;
				console.debug(emplos);
				if(emplos.length<=0){
					employeeHtml='<option value="">暂无成员</option>';
				}else{
					$.each(emplos,function(index,item){
						employeeHtml+=emploHtml.replace("#userid", item.userid)
						.replace("#name", item.name);
					});
				}
				$("#employee_value").html(employeeHtml);
				$("#employee_id").show();
			}
		}
	});
}
</script>
<title>新增资产管理员</title>
</head>
<body ontouchstart>
     <header class='demos-header'>
      <h1 class="demos-title">新增</h1>
    </header>
    <div class="weui-cells weui-cells_form">
    	<form action="assets/save.do" method="post" id="saveForm">
      
      <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
          <label class="weui-label">名称</label>
        </div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="text" name="name" placeholder="请输入名称" id="name">
        </div>
      </div>
     <br>
      <div class="weui-cells__title">角色管理的部门</div>
     <div class="weui-cells">
      <div class="weui-cell weui-cell_select">
        <div class="weui-cell__bd">
          <select class="weui-select" name="dept.id" id="dept_type" onchange="func()">
           	<option value="">请选择</option>
          	 <c:forEach items="${depts}" var="item">
           		 <option value="${item.id}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
    
     <div class="weui-cells" style="display: none" id="employee_id">
     <div class="weui-cells__title">管理者</div>
      <div class="weui-cell weui-cell_select">
        <div class="weui-cell__bd">
          <select class="weui-select" name="user.userid" id="employee_value">
          		
          </select>
        </div>
      </div>
    </div>
    </form>
    </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">保存</a>
    </div>
</body>
</html>