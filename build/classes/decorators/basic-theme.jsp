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
<meta content='text/html; charset=UTF-8' http-equiv='Content-Type'/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="-1">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta name="ROBOTS" content="none">
<title><decorator:title default="Welcome!" /> - 中華科大教職員資訊系統</title>
<link rel="stylesheet" href="/eis/inc/bootstrap/css/bootstrap.css" />
<!--link rel="stylesheet" href="/eis/inc/bootstrap/css/bootstrap-theme.css" /-->
<link rel="stylesheet" href="/eis/inc/css/advance.css" />
<link href="/eis/inc/bootstrap/css/docs.min.css" rel="stylesheet"/>
<link href="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet"/>
<script src="/eis/inc/js/jquery.js"></script>
<script src="/eis/inc/bootstrap/js/bootstrap.js"></script>
<script src="/eis/inc/js/plugin/jquery.blockUI.js"></script>
<link rel="stylesheet" href="/eis/inc/bootstrap/plugin/bootstrap-submenu/css/bootstrap-submenu.min.css">
<script src="/eis/inc/bootstrap/plugin/bootstrap-submenu/js/bootstrap-submenu.min.js" defer></script>
<script src="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/js/i18n/defaults-zh_TW.min.js"></script>
<decorator:head />
</head>
<body <decorator:getProperty property="body.onload" writeEntireProperty="true" />>	
	<div id="mainmenu" style="height:40px;"></div>
	<div class="content-page-3">
		<decorator:body />
	</div>
	
	<c:if test="${!empty msg}">
		<script>
			$(window).load(function() {
				$('#webdialog').modal('show');
			});
		</script>
		<div class="modal fade" id="webdialog" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">訊息</h4>
					</div>
					<div class="modal-body">
					<p class="lead">${msg.msg}</p>
					<p class="lead text-warning">${msg.warning}</p>
					<p class="lead text-error">${msg.error}</p>
					<p class="lead text-info">${msg.info}</p>
					<p class="lead text-success">${msg.success}</p>
					<p>點畫面任意處繼續...</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-lg btn-primary" data-dismiss="modal" aria-hidden="true">關閉</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>	

</body>
</html>