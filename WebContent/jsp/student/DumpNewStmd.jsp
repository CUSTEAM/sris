<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-fileupload.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
<link href="/eis/inc/css/bootstrap-fileupload.css" rel="stylesheet">
<script>  
$(document).ready(function() {	
	$('#funbtn').popover("show");
	setTimeout(function() {
		$('#funbtn').popover("hide");
	}, 5000);
	
	//$("#selectClass").load("/eis/inc/jsp-kit/classSelector.jsp?r="+Math.floor(Math.random()*11));	
});
</script>
</head>
<body>    
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>新生資料批次匯入</strong> 
<div id="funbtn" rel="popover" title="說明" 
data-content="請先下載最新格式，依欄位貼上資料，編後後點選該檔案並按下匯入資料" data-placement="right" class="btn btn-warning">?</div>
</div>
<form action="DumpNewStmd" method="post" class="form-horizontal" enctype="multipart/form-data">
<div class="wizard-steps">
  	<div><a href="#"><span>1</span> 下載格式</a></div>
  	<div><a href="#"><span>2</span> 依欄位貼上資料</a></div>
  	<div><a href="#"><span>3</span> 匯入資料</a></div>
</div>


<table class="table">
	<tr>
		<td>
		<a class="btn" style="float:left;" href="jsp/student/dumpStds.xlsx"><i class="icon-file"></i>下載格式</a>
		<div style="float:left;">&nbsp;</div>
		<div class="fileupload fileupload-new" data-provides="fileupload" style="float:left;">    		    
			<div class="input-append">			
				<div class="uneditable-input">
					<i class="icon-file fileupload-exists"></i> 
					<span class="fileupload-preview"></span>
				</div>	
				<span class="btn btn-file btn-info">					
					<span class="fileupload-new">選擇檔案</span>
					<span class="fileupload-exists">重選檔案 </span>
					<input type="file" name="upload"/>
				</span>				
				<a href="#" class="btn fileupload-exists btn-info" data-dismiss="fileupload">取消</a>
			</div>
		</div>
		&nbsp;
		<button class="btn btn-danger" name="method:upload" type="submit">匯入資料</button>
		</td>
	</tr>

</table>

<c:if test="${!empty fail || !empty complete}">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span6">

		<table class="table">
			<tr>
				<td colspan="2">已匯入</td>
			</tr>
			<c:forEach items="${complete}" var="c">
			<tr>
				<td>${c.stdNo}</td>
				<td>${c.msg}</td>
			</tr>
			</c:forEach>
		</table>



		</div>
		<div class="span6">
		<table class="table">
			<tr>
				<td colspan="2">未匯入</td>
			</tr>
			<c:forEach items="${fail}" var="c">
			<tr>
				<td>${c.stdNo}</td>
				<td>${c.msg}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</div>




</c:if>


</form>
</body>
</html>