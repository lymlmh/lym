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

  <title>主页</title>

  <!--data table-->
  <link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />

  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
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
						alert( "生成二维码成功！");
				    }else{
						 alert(data.msg, "生成二维码失败警告！");
					}
				}
			});
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
                                       资产信息
                <button class="btn btn-success pull-right" type="button" id="create" >生成二维码</button>
                </header>              
                <div class="panel-body">
                <div class="adv-table editable-table ">
                <div class="space15"></div>
                <table class="table table-striped table-hover table-bordered" id="editable-sample">
                <thead>
                <tr>
                    <th>资产编号</th>
                    <th>资产名称</th>
                    <th>使用部门</th>
                    <th>使用人</th>
                    <th>使用地点</th>
                    <th>资产类型</th>
                     <th>状态</th>
                     <th>引进时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${assets}" var="item">
                <tr class="">
                    <td>${item.code}</td>
                    <td>${item.name}</td>
                    <td>${item.dept.name}</td>
                    <td>${item.user.name}</td>
                    <td >${item.place}</td>
                    <td >${item.type1.name}</td>
                    <td >${item.statusStr}</td>
                     <td >${item.inTime}</td>
                    <td><a  href="index/code.do?codePath=${item.codePath}">下载二维码</a></td>
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
