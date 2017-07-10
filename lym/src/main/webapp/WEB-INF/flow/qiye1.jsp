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
<style type="text/css">
.demos-second-title {
    text-align: center;
    font-size: 24px;
    color: #3cc51f;
    font-weight: 400;
    margin: 0 15%;
}
</style>
<script type="text/javascript">
$(function(){
	var id=${assetsFlow.id};
	$("#0").click(function(){
		window.location.href="flow/audit.do?id="+id+"&aduitAdvice=0";
	});
	
	$("#1").click(function(){
		window.location.href="flow/audit.do?id="+id+"&aduitAdvice=1";
	});

});
</script>
<title>公司资产审批</title>
</head>
<body>
      <div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">资产名称</label>
          <em class="weui-form-preview__value">${fixedAssets.name}</em>
        </div>
      </div>
      <div class="weui-form-preview__bd">
             <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">申请人</label>
          <span class="weui-form-preview__value">${applyer}</span>
        </div>
                  <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">申请资产</label>
          <span class="weui-form-preview__value">${assetsFlow.statusStr}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">使用部门</label>
          <span class="weui-form-preview__value">${fixedAssets.dept.name}</span>
        </div>
          <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">使用人</label>
          <span class="weui-form-preview__value">${fixedAssets.user.name}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">申请时间</label>
          <span class="weui-form-preview__value"><fmt:formatDate value="${assetsFlow.applyTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span>
        </div>
     
          <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">状态</label>
          <span class="weui-form-preview__value" >${assetsFlow.auditStr}--${assetsFlow.auditStatusStr}</span>
        </div>
           </div>
                  <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">申请原因</label>
          <span class="weui-form-preview__value">${assetsFlow.reason}</span>
        </div>
      </div>
    </div>
    
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="0">通过</a>
    </div>
        <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:" id="1">不通过</a>
    </div>
<!--  	  <div class="button_sp_area">
        <a href="javascript:;" id="1" class="weui-btn weui-btn_mini weui-btn_primary">通过</a>
        <a href="javascript:;" id="2" class="weui-btn weui-btn_mini weui-btn_default">不通过</a>

      </div> -->
</body>
</html>