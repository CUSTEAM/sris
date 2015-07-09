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
<strong>儲存歷年成績</strong> 
<div id="funbtn" rel="popover" title="說明" 
data-content="包含學科、平均、操行、全勤、評語、獎懲、體育、軍訓等獨立成績資料一併做歷年儲存，當學期可重複執行，以最後儲存的資料為準" data-placement="right" class="btn btn-warning">?</div>
</div>
<form action="ScoreFilder" method="post" class="form-horizontal" onSubmit="$.blockUI({message:null});">
<table class="table">
	<tr>
		<td class="text-info" nowrap>班級範圍</td>
		<td class="control-group info" nowrap>
			<%@ include file="/inc/jsp-kit/dhnSelector.jsp"%>
			<select name="grade">
				<option <c:if test="${grade eq ''}">selected</c:if> value="">全部</option>
				<option <c:if test="${grade eq '0'}">selected</c:if> value="0">非畢業班</option>
				<option <c:if test="${grade eq '1'}">selected</c:if> value="1">畢業班</option>
			</select>
			<button class="btn btn-danger" name="method:confirm" type="submit" 
			onClick="$.blockUI({message:null});">執行</button>
		</td>
		<td width="100%"></td>
	</tr>
	
</table>
<c:if test="${!empty checklist}">
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
	<c:forEach items="${checklist}" var="c">
	<tr>
		<td>${c.school_year}</td>
		<td>${c.school_term}</td>
		<td>				
		<select name="allCampus" style="font-size:18px;" disabled>
			<c:forEach items="${allCampus}" var="a">
			<option  <c:if test="${c.CampuseNo==a.idno}">selected</c:if> value="${a.idno}">${a.name}</option>
			</c:forEach>
		</select>
		</td>
		<td>
		<select name="allSchool" style="font-size:18px;" disabled>
			<c:forEach items="${allSchoolType}" var="t">
			<option <c:if test="${c.SchoolType==t.idno}">selected</c:if> value="${t.idno}">${t.name}</option>
			</c:forEach>
		</select>
		
		</td>
		
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.checkDate}</td>
		<td width="100%"></td>
	</tr>
	</c:forEach>
</table>
</c:if>


</form>
</body>
</html>