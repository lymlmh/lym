<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>部门信息</title>

  <!--data table-->
  <link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />

  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  <script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript">
function dele(deptid){
	var id=deptid;
		if(id!='' && id!=null){
			$.ajax({
				type:"Post",
				url:"dept/delete.do",
				dataType:"json",
				data:{id:id},
				success:function(data){
					if(data.success){
						alert( "部门删除成功！");
					    window.location.href="index/deptindex.do";

				    }else{
						 alert(data.msg, "删除失败警告！");
					}
				}
			});
		}else{
			alert("请选择需要删除的部门", "提示！");
		}
}
function update(id){
	var id=id;
		if(id!='' && id!=null){
			window.location.href="index/deptupdate/index.do?id="+id;
		}else{
			alert("请选择需要修改的员工", "提示！");
		}
}

$(function(){
	$("#save").click(function(){
		window.location.href="index/deptsave/index.do";
	});
});
</script>
  
</head>

<body class="sticky-header">

<section>

    <div class="left-side sticky-left-side">

        

        <div class="left-side-inner">

            <!-- visible to small devices only -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media logged-user">
                    <img alt="" src="images/photos/user-avatar.png" class="media-object">
                    <div class="media-body">
                        <h4><a href="#">John Doe</a></h4>
                        <span>"Hello There..."</span>
                    </div>
                </div>

                <h5 class="left-nav-title">Account Information</h5>
                <ul class="nav nav-pills nav-stacked custom-nav">
                  <li><a href="#"><i class="fa fa-user"></i> <span>Profile</span></a></li>
                  <li><a href="#"><i class="fa fa-cog"></i> <span>Settings</span></a></li>
                  <li><a href="#"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
                </ul>
            </div>

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li class="active"><a href="index/main.do"><i class="fa fa-home"></i> <span>资产信息</span></a></li>
                <li class="active"><a href="index/empindex.do"><i class="fa fa-home"></i> <span>人员管理</span></a></li>
                <li class="active"><a href="index/deptindex.do"><i class="fa fa-home"></i> <span>部门管理</span></a></li>
                <li class="active"><a href="index/jueseindex.do"><i class="fa fa-home"></i> <span>角色管理</span></a></li>
                 <li class="active"><a href="index/quanxian.do"><i class="fa fa-home"></i> <span>权限管理</span></a></li>
            </ul>
            <!--sidebar nav end-->

        </div>
    </div>
  
    <!-- main content start-->
    <div class="main-content" >
    

        <!-- page heading start-->
        
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
             <div class="row">
                <div class="col-sm-12">
                <section class="panel">
                <header class="panel-heading">
                                       部门信息
                <button class="btn btn-success pull-right" type="button" id="save" >新增部门</button>
                </header>              
                <div class="panel-body">
                <div class="adv-table editable-table ">
                <div class="space15">
                </div>
                <table class="table table-striped table-hover table-bordered" id="editable-sample">
                <thead>
                <tr>
                    <th>部门编号</th>
                    <th>部门名称</th>
                    <th>操作</th>
                     <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${depts}" var="item">
                <tr class="">
                    <td>${item.id}</td>
                    <td>${item.name}</td>                    
                    <td><a id="id" onclick="dele(${item.id})" href="javascript:;">删除</a></td>
                    <td><a id="id" onclick="update(${item.id})" href="javascript:;">修改</a></td>
                </tr>
               </c:forEach>
                
                </tbody>
                </table>
                </div>
                </div>
                </section>
                </div>
                </div>
        </div>
        <!--body wrapper end-->

        <!--footer section start-->
        
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/jquery.nicescroll.js"></script>

<!--data table-->
<script type="text/javascript" src="js/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/data-tables/DT_bootstrap.js"></script>

<!--common scripts for all pages-->
<script src="js/scripts.js"></script>

<!--script for editable table-->
<script src="js/editable-table.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function() {
        EditableTable.init();
    });
</script>

</body>
</html>
