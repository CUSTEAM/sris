<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<base href="<%=basePath%>">
<meta name="JOHN HSIAO" content="http://blog.xuite.net/hsiao/blog" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="-1">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta name="ROBOTS" content="none">
<title><decorator:title default="Welcome!" /> - 中華科大教職員資訊系統</title>
<link href="/eis/inc/css/bootstrap.css" rel="stylesheet"/>
<link href="/eis/inc/css/bootstrap-responsive.css" rel="stylesheet"/>
<link href="/eis/inc/css/advance.css" rel="stylesheet"/>
<script src="/eis/inc/js/jquery.js"></script>
<script src="/eis/inc/js/bootstrap.js"></script>
<script src="/eis/inc/js/plugin/jquery.blockUI.js"></script>
<script>
$.ajaxSetup ({ 
	cache: false 
});

$(document).ready(function() {	
	/* window.onbeforeunload = function() {
		$.unblockUI();
	};
	$("#ActLink").click(function() {
		$.blockUI({
			message : null,
			onOverlayClick : $.unblockUI
		});
	}); */
	
	$("#mainmenu").load("/eis/jsp/decorators/menu.jsp?r="+Math.floor(Math.random()*11));

});
</script>
<decorator:head />
</head>
<body
	<decorator:getProperty property="body.onload" writeEntireProperty="true" />>
	<c:if test="${!empty msg}">
		<script>
			$(window).load(function() {
				$('#webdialog').modal('show');
			});
		</script>
		<div id="webdialog" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="webdialog" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3>系統資訊</h3>
			</div>
			<div class="modal-body">
				<p class="lead muted">${msg.msg}</p>
				<p class="lead text-warning">${msg.warning}</p>
				<p class="lead text-error">${msg.error}</p>
				<p class="lead text-info">${msg.info}</p>
				<p class="lead text-success">${msg.success}</p>
				<p>點畫面任意處繼續..</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">關閉
				</button>
			</div>
		</div>
	</c:if>	
	<div id="mainmenu"></div>	
	<div class="content-page">
		<decorator:body />
	</div>
</body>
</html>