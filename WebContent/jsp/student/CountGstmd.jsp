<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流失學生輔導記錄查詢</title>
</head>
<body>

<form action="CountGstmd" method="post" class="form-horizontal form-inline" enctype="multipart/form-data">
<div class="bs-callout bs-callout-info">
<h4>流失學生輔導記錄查詢</h4> 
<small>請選擇審核範圍後查詢</small>
</div>
<table class="table">
	<tr>
		<td>
		<div class="input-group">
			<span class="input-group-addon">學年</span>
			<input class="form-control" id="year" placeholder="學年" name="year" value="${year}" type="text" 
			style="ime-mode:disabled" onkeyup="return ValidateNumber($(this),value)"/>
		</div>
		
		<div class="input-group">
			<span class="input-group-addon">學期</span>
			<select name="term" class="form-control">
				<option value="1">第1學期</option>
				<option value="2">第2學期</option>
			</select>
		</div>
		
		</td>
		
	</tr>
	<tr>
		<td>
		<select name="cno" class="selectpicker" data-width="auto">
			<option value="">所有校區</option>
			<option value="1">台北</option>
			<option value="2">新竹</option>
		</select>
		<select name="tno" class="selectpicker" data-width="auto">
			<option value="">所有部制</option>
			<option value="D">日間部</option>
			<option value="H">日間部</option>
			<option value="N">進修學院</option>
		</select>
		<select name="gno" class="selectpicker" data-width="auto">
			<option value="">所有年級</option>
			<option value="1">1年級</option>
			<option value="2">2年級</option>
			<option value="3">3年級</option>
			<option value="4">4年級</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>
		<button name="method:print" class="btn btn-danger">列印</button>
		</td>
	</tr>
</table>


</form>



</body>
</html>