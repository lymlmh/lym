<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>二维码管理</title>
</head>
<body>
	<div class='demos-content-padded'>
     <div class="weui-panel__bd">
            <img alt="" src="${codePath}">
        </div>
    </div>
      <%-- <p class="col-lg-6 col-md-6" style="text-align: left;" >
        <a id="saveQrCode" onclick="downloadImage" href="${codePath}">下载二维码</a>
    </p>  --%>
    <a href="${codePath}" download="${codePath}">
<img border="0"  alt="下载二维码">
</a>
<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script type="text/javascript">


$(function() {
	
});
</script>
</body>
</html>