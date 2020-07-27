<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>單筆資格審查</title>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet" />
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet" />
</head>
<body>
	<form action="CheckStmdGraduate" method="post" class="form-inline" enctype="multipart/form-data">
<input type="hidden" name="editStmd" value="${editStmd}" />
		<div class="bs-callout bs-callout-info">
			<h4>單筆資格審查</h4>
			<small>...</small>
		</div>
		<c:if test="${!empty score1}">
		
		
		
		
		
<div class="panel panel-primary">
<div class="panel-heading">畢業資格</div>
<display:table name="${stmd}" requestURI="CheckStmdGraduate?method=multSearch" export="false" id="row" sort="list" class="table table-hover" >
	<display:column title="班級" style="white-space:nowrap;" class="left">${row.ClassName}</display:column>
  	<display:column title="學號" style="white-space:nowrap;" property="stdNo" sortable="true" class="left" />
  	<display:column title="姓名" style="white-space:nowrap;" property="student_name" sortable="false" class="left"/>
  	
  	<display:column title="必修" property="hist_credit1" sortable="true" class="left"/>
  	<display:column title="選修" property="hist_credit2" sortable="true" class="left"/>
  	<display:column title="通識" property="hist_credit3" sortable="true" class="left"/>
  	<display:column title="合計" property="cnt" sortable="true" class="left"/>
  	<display:column title="語言" sortable="true" style="width:100px;">
  	<input type="hidden" name="students_no" value="${row.stdNo}" />
  	<input type="hidden" name="updates" id="updates${row.stdNo}" />
  	<select data-width="100px" class="selectpicker" name="language" onChange="$('#updates${row.stdNo}').val('Y')">
  	
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.language eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>
  	<display:column title="專業" sortable="true" style="width:100px;">
  	<select data-width="100px" class="selectpicker" name="certificate" onChange="$('#updates${row.stdNo}').val('Y')">
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.certificate eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>
  	<display:column title="實習" sortable="true" style="width:100px;">
  	<select data-width="100px" class="selectpicker" name="practice" onChange="$('#updates${row.stdNo}').val('Y')">
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.practice eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
	
	
  	</display:column>
  	
  	
  	<display:column title="資格" sortable="true" style="width:100px;">
  	<select data-width="90px" class="selectpicker" disabled>
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.pass eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>  	
  	<display:column title="">
  	<button name="method:justSave" class="btn btn-danger">儲存資格審查</button>
  	<a href="CheckStmdGraduate" type="button" class="btn btn-default">離開</a>
  	</display:column>
  	</display:table>
		</div>
		
		
		
		
		
		
		
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">必修課程應修學分數: -, 已修學分數: - </div>
				<table class="table">
				
					<tr>
						<th>必修課程</th>
						<th>已修畢的必修</th>
						<th>必修課程</th>
						<th>已修畢的必修</th>
						<th>必修課程</th>
						<th>已修畢的必修</th>
					</tr>
					<c:forEach items="${score1}" var="g" varStatus="v">
					
					<c:if test="${v.index%3==0}"><tr></c:if>
						<td>${g.chi_name}${v.index}</td>
						<td>-</td>						
					
					
									
					
					</c:forEach>					
				</table>
		</div>
		
		
		<div class="panel panel-primary">
			<div class="panel-heading">通識課程應修學分數: -, 已修學分: - </div>
				<table class="table">
				
					<c:forEach items="${score3}" var="g" varStatus="v">
					
					<c:if test="${v.index%4==0}"><tr></c:if>
						<td>${g.chi_name}${v.index}</td>
											
					
					
								
					
					</c:forEach>					
				</table>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<c:if test="${!empty hist}">
		<div class="panel panel-primary">
			<div class="panel-heading">記錄</div>
			<display:table name="${hist}"
				requestURI="CheckStmdGraduate?method=multSearch" export="false" id="row" sort="list" class="table table-hover">
				<display:column title="時間" style="white-space:nowrap;" property="editime" />
				<display:column title="備註" property="note" style="width:100%;" class="left" />
			</display:table>
		</div>
		</c:if>
		<center>
			<button type="button" class="btn btn-danger" disabled>儲存學分審查</button>
			<a href="CheckStmdGraduate" type="button" class="btn btn-default">離開</a>
			<div class="btn-group" role="group" aria-label="...">

				<button type="button" class="btn btn-default" disabled>上一筆</button>

				<button type="button" class="btn btn-default" disabled>下一筆</button>
			</div>
		</center>

		</c:if>
	</form>

</body>
</html>