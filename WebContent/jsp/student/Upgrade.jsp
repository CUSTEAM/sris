<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js" type="text/javascript"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js" type="text/javascript"></script>
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
<strong>學生批次升級</strong> 
<div id="funbtn" rel="popover" title="說明" 
data-content="將範圍內學生批次升級, 請在成績匯入歷年後進行" data-placement="right" class="btn btn-warning">?</div>
</div>
<form action="Upgrade" method="post" class="form-horizontal" onSubmit="$.blockUI({message:null});">
<table class="table">
	<tr>
		<td class="text-info" nowrap>班級範圍</td>
		<td class="control-group info" nowrap>
			<%@ include file="/inc/jsp-kit/dhnSelector.jsp"%>			
			<button class="btn btn-danger" name="method:confirm" type="submit" 
			onClick="$.blockUI({message:null});">執行</button>
		</td>
		<td width="100%"></td>
	</tr>	
</table>
</form>
<table class="table">
	<tr>		
		<td nowrap>學年</td>
		<td nowrap>學期</td>
		<td nowrap>校區</td>
		<td nowrap>部制</td>
		<td nowrap>執行人員</td>
		<td nowrap>日期</td>
		<td width="100%"></td>
	</tr>
	<c:forEach items="${logs}" var="c">
	<tr>
		<td>${c.school_year}</td>
		<td>${c.school_term}</td>
		<td>				
		<select name="allCampus" style="font-size:18px;" disabled>
			<c:forEach items="${allCampus}" var="a">
			<option  <c:if test="${c.CampusNo==a.idno}">selected</c:if> value="${a.idno}">${a.name}</option>
			</c:forEach>
		</select>
		</td>
		<td>
		<select name="allSchool" style="font-size:18px;" disabled>
			<!--
			<c:forEach items="${allSchoolType}" var="t">
			<option <c:if test="${c.SchoolType==t.idno}">selected</c:if> value="${t.idno}">${t.name}</option>
			</c:forEach>-->
			<option <c:if test="${c.SchoolType eq 'D'}">selected</c:if> value="D">日間部</option>
			<option <c:if test="${c.SchoolType eq 'N'}">selected</c:if> value="N">進修部</option>
			<option <c:if test="${c.SchoolType eq 'H'}">selected</c:if> value="H">進修學院</option>
		</select>		
		</td>		
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.up_time}</td>
		<td width="100%"></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>