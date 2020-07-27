<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>期末成績整合併入歷年成績</title>
</head>
<body>
<div class="bs-callout bs-callout-info">
<h4>期末成績整合併入歷年成績</h4> 
<small>包含學科、平均、操行、全勤、評語、獎懲、體育、軍訓等獨立成績轉換為歷年成績記錄，<br>當學期可重複執行，以最後儲存的資料為準。</small>
</div>
<form action="ScoreFilder" method="post" class="form-horizontal form-inline" onSubmit="$.blockUI({message:null});">
<table class="table">
	<tr>
		<td nowrap>班級範圍</td>
		<td nowrap>
			<%@ include file="/inc/jsp-kit/dhnSelector.jsp"%>
			<select name="grade" class="selectpicker" data-width="auto">
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
		<select name="allCampus" disabled class="form-control">
			<c:forEach items="${allCampus}" var="a">
			<option  <c:if test="${c.CampuseNo==a.idno}">selected</c:if> value="${a.idno}">${a.name}</option>
			</c:forEach>
		</select>
		</td>
		<td>
		<select name="allSchool" disabled class="form-control">
			<c:forEach items="${allSchoolType}" var="t">
			<option <c:if test="${c.SchoolType==t.idno}">selected</c:if> value="${t.idno}">${t.name}</option>
			</c:forEach>
		</select>
		
		</td>
		
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.checkDate}</td>
		<td ></td>
	</tr>
	</c:forEach>
</table>
</c:if>


</form>
</body>
</html>