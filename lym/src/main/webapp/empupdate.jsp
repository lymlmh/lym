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
	 $.post("Employee/update.do",$("#search_form").serialize(),function(data){
		 	var data=JSON.parse(data);
		   if(data.success){
			   alert( "修改员工成功！");
			   window.location.href="index/empindex.do";
		    }else{
		    	alert("新增失败，请检查表单数据格式是否正常", "提示！");
		    }
		  });
	});
	$("#back").click(function(){
		window.location.href="index/empindex.do";
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
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                           修改人员
                        </header>
                        <div class="panel-body">
                            <div class="form">
                                <form class="cmxform form-horizontal adminex-form" id="search_form" method="post" >
                                <input type="hidden" value="${employee.userid}" name="userid">
                                    <div class="form-group ">
                                        <label for="firstname" class="control-label col-lg-2">姓名</label>
                                        <div class="col-lg-10">
                                            <input class=" form-control" id="name" value="${employee.name}" name="name" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="lastname" class="control-label col-lg-2">账号</label>
                                        <div class="col-lg-10">
                                            <input class=" form-control" id="userid" value="${employee.userid}" disabled="disabled" name="userid" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="username" class="control-label col-lg-2">性别</label>
                                        <div class="col-lg-10">
                                           <select class="weui-select" name="gender" id="gender">
									           <option selected="" value="0">请选择性别</option>
									           <option value="1">男</option>
									           <option value="2">女</option>
									        </select>
									        <script type="text/javascript">
									          	var gender='${employee.gender}';
									          	$("#gender").val(gender);
									          </script>
                                        </div>
                                    </div>
                                    <%-- <div class="form-group ">
                                        <label for="password" class="control-label col-lg-2">微信号</label>
                                        <div class="col-lg-10">
                                            <input class="form-control " value="${employee.weixinid}" id="weixinid" name="weixinid" type="text" />
                                        </div>
                                    </div> --%>
                                    <div class="form-group ">
                                        <label for="confirm_password" class="control-label col-lg-2">手机号</label>
                                        <div class="col-lg-10">
                                            <input class="form-control " id="mobile" name="mobile" value="${employee.mobile}" type="number" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="email" class="control-label col-lg-2">邮箱</label>
                                        <div class="col-lg-10">
                                            <input class="form-control " id="email" name="email" value="${employee.email}" type="email" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="agree" class="control-label col-lg-2 col-sm-3">职位</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <input  type="text"  class="form-control" id="position" value="${employee.position}" name="position" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="newsletter" class="control-label col-lg-2 col-sm-3">所属部门</label>
                                        <div class="col-lg-10 col-sm-9">
                                            <select class="weui-select" name="department" id="department">
									         	 <c:forEach items="${depts}" var="item">
									           		 <option value="${item.id}">${item.name}</option>
									            </c:forEach>
									          </select>
									            <script type="text/javascript">
										          	var departmen='${employee.deptId}';
										          	$("#department").val(departmen);
										          </script>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                            <a class="btn btn-primary" href="javascript:" id="showTooltips">保存</a>
                                            <a class="btn btn-default" href="javascript:" id="back">取消</a>
                                        </div>
                                    </div>
                                </form>
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
