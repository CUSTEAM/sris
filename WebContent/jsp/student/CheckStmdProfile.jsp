<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生填報基本資料審核</title>
<script src="/eis/inc/js/plugin/bootstrap-fileupload.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
<link href="/eis/inc/css/bootstrap-fileupload.css" rel="stylesheet">
</head>
<body>
<div class="bs-callout bs-callout-info">
<h4>學生填報基本資料審核</h4> 
<small>請選擇審核範圍後查詢</small>
</div>
<form action="CheckStmdProfile" method="post" class="form-horizontal" enctype="multipart/form-data">
<div class="wizard-steps">
  	<div><a href="#"><span>1</span> 選擇範圍</a></div>
  	<div><a href="#"><span>2</span> 查詢</a></div>
  	<div><a href="#"><span>3</span> 匯出資料</a></div>
</div>

<table class="table">
	<tr>
		<td><%@ include file="/inc/jsp-kit/fullSelector.jsp"%></td>
	</tr>
	<tr>
		<td>
		<button class="btn btn-danger" name="method:search" type="submit">報表列印</button>
		<button class="btn btn-default" name="method:detail" type="submit">查核照片及基本資料</button>
		</td>
	</tr>
</table>



<c:if test="${!empty stds}">
<table class="table table-striped table-condensed">
	<c:forEach items="${stds}" var="s">	
	<tr>
		<td rowspan="3" width="70"><img width="70" class="img-circle" src="http://ap.cust.edu.tw/eis/getStdimage?myStdNo=${s.student_no}"/>
		<h6>${s.student_no}<br>${s.student_name}</h6>
		</td>
		<td nowrap>通訊地址</td>
		<td>${s.curr_addr}</td>
	</tr>
	<tr>
		<td nowrap>戶籍地址</td>		
		<td>${s.perm_addr}</td>
	</tr>
	<tr>
		<td nowrap>連絡</td>		
		<td>${s.telephone}, ${s.CellPhone}, ${s.Email}</td>
	</tr>
</c:forEach>
	
</table>
</c:if>

</form>
</body>
</html>