<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新生資料匯入</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
<link href="/eis/inc/bootstrap/plugin/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">

<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput_locale_zh-TW.js"></script>

</head>
<body>    
<div class="bs-callout bs-callout-info">

<h4>新生資料批次匯入</h4> <small>請先下載最新格式，依欄位貼上資料，儲存後點選檔案並按下匯入資料</small>

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
		<a class="btn btn-primary btn-lg btn-block" href="jsp/student/dumpStds.xlsx"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>下載格式</a>	<br>			    
		<input name="upload" type="file" data-show-upload="false"
		id="upload" class="file file-loading "/><br>
		<script>
		$("#upload").fileinput({
			multiple: false,
			
		    language: "zh-TW",
		    uploadUrl: "",
		    allowedFileExtensions: ["xls", "xlsx"]
		});
		</script>
					
				
				
		<button class="btn btn-primary btn-lg btn-block" id="saveTxtFile" name="method:upload" type="submit"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>匯入資料</button> 
				
		</td>
	</tr>
</table>




<c:if test="${!empty fail || !empty complete}">

<div class="row">
	<div class="col-md-6">

		<table class="table table-condensed">
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
		<div class="col-md-6">
		<table class="table table-condensed">
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
<script>  
$(document).ready(function() {	
	
	/*$('#funbtn').popover("show");
	setTimeout(function() {
		$('#funbtn').popover("hide");
	}, 5000);*/
	
	
});
</script>
</body>
</html>