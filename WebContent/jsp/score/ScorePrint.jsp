<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成績列印</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js" type="text/javascript"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js" type="text/javascript"></script>
<script>  
$(document).ready(function() {	
	$('#funbtn').popover("show");
	setTimeout(function() {
		$('#funbtn').popover("hide");
	}, 15000);
	
	//$("#selectClass").load("/eis/inc/jsp-kit/classSelector.jsp?r="+Math.floor(Math.random()*11));	
});
</script>
</head>
<body>    
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>成績查詢列印</strong> 
<div id="funbtn" rel="popover" title="說明" 
data-content="選擇範圍點選列印，標題、統計等其它細節資料，於報表中的頁首/頁尾，請以預覽列印檢視" data-placement="right" class="btn btn-warning">?</div>
</div>
<form action="ScorePrint" method="post" class="form-horizontal">
<table class="table">
	<tr>
		<td class="text-info" nowrap>班級範圍</td>
		<td class="control-group info" nowrap>
			<%@ include file="/inc/jsp-kit/fullSelector.jsp"%>
			<select name="grade">
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
	
</table>

<table class="table">
	<tr>
		<td class="text-info" nowrap>期中列印</td>
		<td class="control-group info" nowrap width="100%">
		<div class="btn-group" style="float:left">		
		    <button class="btn" name="method:midHalf" type="submit">學生1/2不及格</button>
		    <button class="btn" name="method:midMoreHalf" type="submit">學生2/3不及格</button>
		</div>
		
		<div class="btn-group" style="float:left">    
		    <button class="btn" name="method:midCsHalf" type="submit">科目1/2不及格</button>
		    <button class="btn" name="method:midCsMoreHalf" type="submit">科目2/3不及格</button>		    
		</div>
		
		<div class="btn-group" style="float:left">
		    <button class="btn" name="method:midNotice" type="submit">班級1/2以上名單</button>
		    <button class="btn btn-danger" name="method:midScore" type="submit">成績總表</button>
		</div>
		
		</td>
	</tr>
	<tr>
		<td class="text-info" nowrap>期末列印</td>
		<td class="control-group info" nowrap width="100%">
		<div class="btn-group" style="float:left">
		    <button class="btn" name="method:endHalf" type="submit">學生1/2不及格</button>
		    <button class="btn" name="method:endMoreHalf" type="submit">學生2/3不及格</button>
		</div>
		<div class="btn-group" style="float:left">
		    <button class="btn" name="method:endCsHalf" type="submit">科目1/2不及格</button>
		    <button class="btn" name="method:endCsMoreHalf" type="submit">科目2/3不及格</button>		    
		</div>
		<div class="btn-group" style="float:left">
		    <button class="btn" name="method:endNotice" type="submit">班級1/2以上名單</button>
		    <button class="btn btn-danger" name="method:endScore" type="submit">成績總表</button>
		</div>		
		</td>
	</tr>
	<tr>
		<td class="text-info" nowrap>一般列印</td>
		<td class="control-group info" nowrap width="100%">
		<div class="btn-group" style="float:left">
		    <button class="btn" name="method:honors" type="submit">學優獎學金</button>
		    <button class="btn" name="method:avgScore" type="submit">歷年平均成績</button>
			<button class="btn" name="method:mail" type="submit">郵寄成績通知單</button>	
		    <button class="btn btn-danger" name="method:yeaReach" type="submit">連續1/2不及格</button>	    
		</div>
				
		</td>
	</tr>
</table>
</form>
</body>
</html>