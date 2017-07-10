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
<style type="text/css">
.demos-second-title {
    text-align: center;
    font-size: 24px;
    color: #3cc51f;
    font-weight: 400;
    margin: 0 15%;
}
</style>
<title>个人资产信息</title>
</head>
<body>
      <div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">资产名称</label>
          <em class="weui-form-preview__value">${fa.name}</em>
        </div>
      </div>
      <div class="weui-form-preview__bd">
      <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">资产编号</label>
          <span class="weui-form-preview__value">${fa.code}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">使用部门</label>
          <span class="weui-form-preview__value">${fa.dept.name}</span>
        </div>
          <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">使用者</label>
          <span class="weui-form-preview__value">${fa.user.name}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">引进时间</label>
          <span class="weui-form-preview__value">${fa.inTime}</span>
        </div>
          <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">单价</label>
          <span class="weui-form-preview__value">${fa.price}</span>
        </div>
                  <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">使用地点</label>
          <span class="weui-form-preview__value">${fa.place}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">资产类型</label>
          <span class="weui-form-preview__value">${fa.type1.name}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">状态</label>
          <span class="weui-form-preview__value" style="color:#586c94;">${fa.statusStr}</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">简介</label>
          <span class="weui-form-preview__value">${fa.info}</span>
        </div>
      </div>
      <div class="weui-form-preview__ft">
        <a id="transfer" data="3" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">转移</a>
        <a id="sequestration" data="2" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">封存</a>
        <a id="useing" data="0" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">启用</a>
        <a id="scrap" data="1" class="weui-form-preview__btn weui-form-preview__btn_warn" href="javascript:">报废</a>
      </div>
    </div>
    <br>
     <div class="weui-panel weui-panel_access">
    	<div class="weui-panel__hd">流转信息</div>
    	
    	
    	<c:forEach items="${flows}" var="item">
    		&darr;${item.statusStr}&sim;审核通过&sim;<fmt:formatDate value="${item.applyTime}" pattern="yyyy/MM/dd" /><br>
	     </c:forEach>
	    <%--  <c:if test='${audit==true}'>
	     	(未审核)
	     </c:if> --%>
	     
    </div>
    <br>
    <div class='demos-content-padded'>
      <a href="javascript:;" class="weui-btn weui-btn_primary open-popup" data-target="#full">查看二维码</a>
    </div>
    <div id="full" class='weui-popup__container'>
      <div class="weui-popup__overlay"></div>
      <div class="weui-popup__modal">
        <header class='demos-header'>
          <h2 class="demos-second-title">二维码</h2>
        </header>
        <article class="weui-article">
            <section>
              <h3>请自行保存二维码</h3>
              <p>
              </p>
              <p>
                <img src="${fa.codePath}" alt="">
              </p>
            </section>
        </article>
        <a href="javascript:;" class="weui-btn weui-btn_primary close-popup">关闭</a>
      </div>
    </div>
<script src="js/lib/jquery-2.1.4.js"></script>
<script src="js/lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script type="text/javascript">
$(function() {
  FastClick.attach(document.body);
});
</script>
<script type="text/javascript">
var employee_value;
$(function(){
	var status='${fa.status}';
	var id='${fa.id}';
	var audit='${audit}';
	if(audit=='true'||status==4){
		$(".weui-form-preview__ft").hide();
	}
	if(status==1){
		$(".weui-form-preview__ft").hide();
		var flowInfo=$("#flowInfo").html();
		$("#flowInfo").html(flowInfo+'报废时间：<fmt:formatDate value="${fa.scrapTime}" pattern="yyyy/MM/dd" />');
	}
	else{
		$.each($(".weui-form-preview__btn"),function(index,item){
			var dataStatus=$(item).attr("data");
			console.debug(dataStatus);
			if(status==dataStatus){
				$(item).hide();
			}
		});
	}
	 $("#transfer").on("click",function() {
		 $.ajax({
				dataType:"json",
				type:"post",
				data:{id:id},
				url:"assets/transfer/dept.do",
				success:function(data){
					if(data.success){
						var deptHtml='<div class="weui-cells__title">转移部门</div>'+
						     '<div class="weui-cells">'+
					     ' <div class="weui-cell weui-cell_select">'+
					        '<div class="weui-cell__bd">'+
					         ' <select class="weui-select" name="dept.id" id="dept_type" onchange="func()">'+
					           	'<option value="">请选择</option>#deptHtml'+
					         ' </select>'+
					        '</div>'+
					      '</div>'+
					    '</div>'+
					    				    
					    '<div class="weui-cells" id="employee_id">'+
					     '<div class="weui-cells__title">转移对象</div>'+
					      '<div class="weui-cell weui-cell_select">'+
					        '<div class="weui-cell__bd">'+
					          '<select class="weui-select" name="user.userid" id="employee_value">'+
					          		
					          '</select>'+
					        '</div>'+
					      '</div>'+
					    '</div>'+
					    
					    '<div class="weui-cells__title">转移地点</div>'+
					     '<div class="weui-cells">'+
				     ' <div class="weui-cell weui-cell_select">'+
				        '<div class="weui-cell__bd">'+
				        ' <select class="weui-select" name="place" id="place" >'+
			           	'<option value="">请选择</option>#deptHtml1'+
			         ' </select>'+
				        '</div>'+
				      '</div>'+
				    '</div>'+
					    
					    '<div class="weui-cells__title">转移原因</div>'+
					     '<div class="weui-cells">'+
					     
				         ' <form id="fom">'+
				         '<textarea class="weui-textarea" id="reason" name="reason" placeholder="请输入转移原因" rows="3" maxlength="200"></textarea>'+
				          /* '<input type="text" id="reason" value="" name="reason">'+ */
				          
					       '</form>'
					       
					     
					    '</div>';
					    var itemHtml='';
					    
					    var optionHtml='<option value="#deptid">#deptName</option>';
					    $.each(data.list,function(index,item){
					    	itemHtml += optionHtml.replace("#deptid", item.id)
							.replace("#deptName", item.name);
						});
					    
					    var itemHtml1='';
					    var optionHtml1='<option value="#placename">#name</option>';
					    $.each(data.place,function(index,item){
					    	itemHtml1 += optionHtml1.replace("#placename", item.name).replace("#name",item.name);
						});
					    
					    deptHtml=deptHtml.replace("#deptHtml", itemHtml);
					    deptHtml=deptHtml.replace("#deptHtml1", itemHtml1);
						$.modal({
					          title: "提示",
					          text: deptHtml,
					          buttons: [
					            { text: "确定", onClick: function(){
					            	var dept_type=$("#dept_type").val();
/* 					                var reason=$("reason").val();
					                reason="li"; */
					                var input = document.getElementById("reason");
					                var reason = input.value;
					                
					                var input1 = document.getElementById("place");
					                var place = input1.value;
					               
					            	employee_value=$("#employee_value").val();
					            	//alert(employee_value);
					            	//alert(dept_type);
					            	//window.location.href="assets/transfer.do?id="+id+"&userid="+employee_value+"&deptId="+dept_type+"&reason="+reason+"&place="+place;
									//window.location.reload(true);转移
					            	zhuanyi(id,dept_type,employee_value,place,reason,1);
					            } 

					            },
					            {
					            text: "取消", onClick: function(){
					            }
					            }
					           
					          ]
						
					        });
					}else{
						$.alert("操作失败,请重试", "提示");
					}
				}
			}); 
	 });
	 
	 function zhuanyi(id,deptId,userid,place,reason,audit){
			$.ajax({
				dataType:"json",
				type:"post",
				data:{id:id,deptId:deptId,userid:userid,place:place,reason:reason,audit:audit},
				url:"assets/transfer.do",
				success:function(data){
					if(data.success){
						$.modal({
					          title: "提示",
					          text: "已提交审核",
					          buttons: [
					            { text: "确定", onClick: function(){
									 window.location.reload(true);
					            } }
					          ]
					        });
					}else{
						$.alert("操作失败,请重试", "提示");
					}
				}
			}); 
		}
	 
	 $("#scrap").on("click",function() {
		  var deptHtml='<div class="weui-cells__title">报废原因</div>'+
	                  '<div class="weui-cells">'+		     
			            ' <form id="fom">'+
			            '<textarea class="weui-textarea" id="reason" name="reason" placeholder="请输入报废原因" rows="3" maxlength="200"></textarea>'+
			         /* '<input type="text" id="reason" value="" name="reason">'+	 */		         
			       '</form>'			     			     
			    '</div>';
			    $.modal({
			          title: "提示",
			          text: deptHtml,
			          buttons: [
			            { text: "确定", onClick: function(){
			                var input = document.getElementById("reason");
			                var reason = input.value;
			                update(1,reason,1);//报废
							 //window.location.reload(true);
			            } }
			            ,
			            {
			            text: "取消", onClick: function(){
			            }
			            }
			          ]
			        }); 
		 
	 });
	 $("#useing").on("click",function() {
		 var deptHtml='<div class="weui-cells__title">启用原因</div>'+
         '<div class="weui-cells">'+		     
           ' <form id="fom">'+
        /* '<input type="text" id="reason" value="" name="reason">'+	 */	
        '<textarea class="weui-textarea" id="reason" name="reason" placeholder="请输入启用原因" rows="3" maxlength="200"></textarea>'+
      '</form>'			     			     
   '</div>';
   $.modal({
         title: "提示",
         text: deptHtml,
         buttons: [
           { text: "确定", onClick: function(){
               var input = document.getElementById("reason");
               var reason = input.value;
               update(0,reason,1);//启用
				 //window.location.reload(true);
           } }
           ,
           {
           text: "取消", onClick: function(){
           }
           }
         ]
       }); 
	 });
	 $("#sequestration").on("click",function() {
		 var deptHtml='<div class="weui-cells__title">封存原因</div>'+
         '<div class="weui-cells">'+		     
           ' <form id="fom">'+
           '<textarea class="weui-textarea" id="reason" name="reason" placeholder="请输入封存原因" rows="3" maxlength="200"></textarea>'+
        /* '<input type="text" id="reason" value="" name="reason">'+ */			         
      '</form>'			     			     
   '</div>';
   $.modal({
         title: "提示",
         text: deptHtml,
         buttons: [
           { text: "确定", onClick: function(){
               var input = document.getElementById("reason");
               var reason = input.value;
               update(2,reason,1);//封存
				 //window.location.reload(true);
           } }
           ,
           {
           text: "取消", onClick: function(){
           }
           }
         ]
       }); 
	 });
function update(status,reason,audit){
	$.ajax({
		dataType:"json",
		type:"post",
		data:{status:status,id:id,reason:reason,audit:audit},
		url:"assets/updateStatus.do",
		success:function(data){
			if(data.success){
				$.modal({
			          title: "提示",
			          text: "已提交审核",
			          buttons: [
			            { text: "确定", onClick: function(){
							 window.location.reload(true);
			            } }
			          ]
			        });
			}else{
				$.alert("操作失败,请重试", "提示");
			}
		}
	}); 
}
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
</body>
</html>