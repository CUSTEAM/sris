<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班級管理</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script>  
$(document).ready(function() {	
	$('.help').popover("show");
	setTimeout(function() {
		$('.help').popover("hide");
	}, 5000);	
});
</script>


</head>
<body>    
<div class="bs-callout bs-callout-info">
<strong>班級管理</strong> 
</div>

<table class="table">
	<tr>
		<td>
		<select name="CampusNo" class="selectpicker" data-width="100%">
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option>建立新校區</option>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>
		<select name="CampusNo" class="selectpicker" data-width="auto">
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option>建立新校區</option>
		</select>
		</td>
	</tr>


</table>

</body>
</html>