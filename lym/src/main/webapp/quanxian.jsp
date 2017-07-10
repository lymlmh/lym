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

  <title>员工信息</title>

  <!--data table-->
  <link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />

  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  <script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript">
$(function(){
	$("#showTooltips").click(function(){

	 $.post("quanxian/update.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   alert("权限分配成功", "提示！");
			   window.location.href="index/quanxian.do";
		    }else{
		    	alert("权限分配失败，请检查表单数据格式是否正常", "提示！");
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
        <div class="wrapper">
                <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                                                         权限分配
                        </header>
                        <div class="panel-body">
                            <div class="form">
                                <form class="cmxform form-horizontal adminex-form" id="search_form" method="post" >
                                    <div class="form-group ">
                                        <label for="firstname" class="control-label col-lg-2">角色</label>
                                        <div class="col-lg-10">
                                            <select class="weui-select" name="id">
								         	 <c:forEach items="${quanxian}" var="item">
								           		 <option value="${item.id}">${item.name}</option>
								            </c:forEach>
								          </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">部门管理</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="ifbumen"">           		 
								           		 <option value="2">不允许</option> 
								           		 <option value="1">允许</option> 
								          </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">人员管理</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="ifrenyuan"">           		 
								           		 <option value="2">不允许</option> 
								           		 <option value="1">允许</option> 
								          </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">地点管理</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="ifdidian"">           		 
								           		 <option value="2">不允许</option> 
								           		 <option value="1">允许</option> 
								          </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">权限管理</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="ifquanxian"">           		 
								           		 <option value="2">不允许</option> 
								           		 <option value="1">允许</option> 
								          </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">角色管理</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="ifjuese"">           		 
								           		 <option value="2">不允许</option> 
								           		 <option value="1">允许</option> 
								          </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                            <a class="btn btn-primary" href="javascript:" id="showTooltips">确定</a>
                                            <!-- <a class="btn btn-default" href="javascript:" id="back">取消</a> -->
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    

        <!-- page heading start-->
        
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
             <div class="row">
                <div class="col-sm-12">
                <section class="panel">
                <header class="panel-heading">
                                      角色权限信息
                </header>              
                <div class="panel-body">
                <div class="adv-table editable-table ">
                <div class="space15"></div>
                <table class="table table-striped table-hover table-bordered" id="editable-sample">
                <thead>
                <tr>
                    <th>角色名称</th>
                    <th>部门管理</th>
                    <th>人员管理</th>
                    <th>地点管理</th>
                    <th>权限管理</th>
                    <th>角色管理</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${quanxian}" var="item">
                <tr class="">
                    <td>${item.name}</td>
                    <td>${item.ifbumenStr}</td>
                    <td>${item.ifrenyuanStr}</td>
                    <td >${item.ifdidianStr}</td>
                    <td >${item.ifquanxianStr}</td>
                    <td >${item.ifjueseStr}</td>                     
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
