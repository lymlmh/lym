<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
 <meta charset="utf-8"/>
 <meta name="apple-mobile-web-app-capable" content="yes"/>
 <meta name="format-detection" content="telephone=no" />
 <script type="text/javascript">
 	document.location = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=${corpid}&redirect_uri=http://www.liyuanming.me/lym/assets/index1.do&response_type=code&scope=snsapi_userinfo&agentid=0&state=${id}#wechat_redirect'
 </script>
<title></title>
</head>
<body>
</body>
</html>