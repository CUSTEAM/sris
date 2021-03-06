<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>照片批次匯入</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
<link href="/eis/inc/bootstrap/plugin/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">

<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput_locale_zh-TW.js"></script>

</head>
<body>    
<div class="bs-callout bs-callout-info">

<h4>照片批次匯入</h4> <small>請將照片檔名改為學號，瀏覽資料夾中的照片用滑鼠圈選檔案批次上傳</small>

</div>
<form action="UploadStmdImage" method="post" class="form-horizontal" enctype="multipart/form-data">
<div class="wizard-steps">
  	<div><a href="#"><span>1</span> 準備照片</a></div>
  	<div><a href="#"><span>2</span> 將檔名改為學號</a></div>
  	<div><a href="#"><span>3</span> 圈選上傳</a></div>
</div>

<table class="table">
	<tr>
		<td>	
			
		<input name="fileUpload" multiple type="file" data-show-upload="false"
		id="fileUpload" class="file file-loading "/><br>
		<script>
		$("#fileUpload").fileinput({
			//multiple: false,
			
		    language: "zh-TW",
		    uploadUrl: "",
		    allowedFileExtensions: ["jpg", "jpeg", "png"]
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