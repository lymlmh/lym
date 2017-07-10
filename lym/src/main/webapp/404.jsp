<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>">
 <meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.">
<link rel="stylesheet" href="js/lib/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<style>
      .icon-box {
        margin-bottom: 25px;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center
      }

      .icon-box i {
        margin-right: 18px
      }

      .icon-box__ctn {
        -webkit-flex-shrink: 100;
        flex-shrink: 100
      }

      .icon-box__title {
        font-weight: 400
      }

      .icon-box__desc {
        margin-top: 6px;
        font-size: 12px;
        color: #888
      }

      .icon_sp_area {
        margin-top: 10px;
        text-align: left
      }

      .icon_sp_area i:before {
        margin-bottom: 5px
      }
    </style>

<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<title>找不到网页sf</title>
</head>
<body>
	<div class="container" align="center" style=" margin-top: 50px;margin-left: 50px;">
	<div class="icon-box">
        <i class="weui-icon-warn weui-icon_msg-primary"></i>
        <div class="icon-box__ctn">
          <h3 class="icon-box__title">温馨提示</h3>
          <p class="icon-box__desc">发现了未知区域</p>
        </div>
      </div>
</div>
</body>
</html>