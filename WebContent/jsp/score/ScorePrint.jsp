<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>成績列印</title>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js" type="text/javascript"></script>
</head>
<body>    


<div class="bs-callout bs-callout-info">
<h4>成績查詢列印</h4> 
<small>選擇範圍點選列印，標題、統計等其它細節資料，於報表中的頁首/頁尾，請以預覽列印檢視</small>
</div>


<form action="ScorePrint" method="post" class="form-horizontal">
<table class="table">
	<tr>
		<td nowrap>班級範圍</td>
		<td nowrap>
			<%@ include file="/inc/jsp-kit/fullSelector.jsp"%>
			<select name="grade" class="selectpicker" data-width="auto">
				<option <c:if test="${grade eq ''}">selected</c:if> value="">全部</option>
				<option <c:if test="${grade eq '0'}">selected</c:if> value="0">非畢業班</option>
				<option <c:if test="${grade eq '1'}">selected</c:if> value="1">畢業班</option>
				<option <c:if test="${grade eq '2'}">selected</c:if> value="2">延修班</option>
				<option <c:if test="${grade eq '2'}">selected</c:if> value="3">跨校班</option>
				<option <c:if test="${grade eq '2'}">selected</c:if> value="4">學分班</option>
			</select>
		</td>
		<td width="100%"></td>
	</tr>
	<tr>
		<td nowrap>期中列印</td>
		<td nowrap width="100%">
		<div class="btn-group" style="float:left">		
		    <button class="btn btn-default" name="method:midHalf" type="submit">學生1/2不及格</button>
		    <button class="btn btn-default" name="method:midMoreHalf" type="submit">學生2/3不及格</button>
		   
		    <button class="btn btn-default" name="method:midCsHalf" type="submit">科目1/2不及格</button>
		    <button class="btn btn-default" name="method:midCsMoreHalf" type="submit">科目2/3不及格</button>		    
		
		    <button class="btn btn-default" name="method:midNotice" type="submit">班級1/2以上名單</button>
		    <button class="btn btn-danger" name="method:midScore" type="submit">成績總表</button>
		</div>
		
		</td>
	</tr>
	<tr>
		<td nowrap>期末列印</td>
		<td nowrap width="100%">
		<div class="btn-group" style="float:left">
		    <button class="btn btn-default" name="method:endHalf" type="submit">學生1/2不及格</button>
		    <button class="btn btn-default" name="method:endMoreHalf" type="submit">學生2/3不及格</button>
		
		    <button class="btn btn-default" name="method:endCsHalf" type="submit">科目1/2不及格</button>
		    <button class="btn btn-default" name="method:endCsMoreHalf" type="submit">科目2/3不及格</button>		    
		
		    <button class="btn btn-default" name="method:endNotice" type="submit">班級1/2以上名單</button>
		    <button class="btn btn-danger" name="method:endScore" type="submit">成績總表</button>
		</div>		
		</td>
	</tr>
	<tr>
		<td nowrap>一般列印</td>
		<td nowrap width="100%">
		<div class="btn-group" style="float:left">
		    <button class="btn btn-default" name="method:honors" type="submit">學優獎學金</button>
		    <button class="btn btn-default" name="method:avgScore" type="submit">歷年平均成績</button>
			<button class="btn btn-default" name="method:mail" type="submit">郵寄成績通知單</button>	
		    <button class="btn btn-danger" name="method:yeaReach" type="submit">連續1/2不及格</button>	    
		</div>
		</td>
	</tr>
	<tr>
		<td nowrap>統計列印</td>
		<td nowrap width="100%">
		<div class="btn-group" style="float:left">
		    <button class="btn btn-default" name="method:gradCredit" type="submit">畢業學分數</button>    
		</div>
		</td>
	</tr>
</table>
</form>
</body>
</html>